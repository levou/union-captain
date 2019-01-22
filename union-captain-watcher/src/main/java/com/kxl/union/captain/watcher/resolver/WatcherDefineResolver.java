package com.kxl.union.captain.watcher.resolver;

import com.kxl.union.captain.common.logger.CaptainLogger;
import com.kxl.union.captain.common.wrapper.CallableWrapper;
import com.kxl.union.captain.common.wrapper.CrossThread;
import com.kxl.union.captain.common.wrapper.RunnableWrapper;
import com.kxl.union.captain.watcher.annotation.Watcher;
import com.kxl.union.captain.watcher.common.ExcludeMethods;
import com.kxl.union.captain.watcher.bytebuddy.define.WatcherDefine;
import com.kxl.union.captain.watcher.exception.WatcherException;
import com.kxl.union.captain.watcher.holder.WatcherHolder;
import com.kxl.union.captain.watcher.holder.WatcherKey;
import com.kxl.union.captain.watcher.bytebuddy.loader.WatcherClassLoader;
import com.kxl.union.captain.watcher.model.WatcherDefinition;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * 加载并扫描类，找出符合要求的类-方法
 */
public enum WatcherDefineResolver {
    RESOLVER;

    public void defineResolver(final Set<URL> defineUrls) throws WatcherException {
        if (null == defineUrls || defineUrls.size() <= 0) {
            throw new WatcherException("Not Found Watcher Define URLs");
        }
        final URL[] urls = new URL[defineUrls.size()];
        int index = 0;
        Iterator<URL> it = defineUrls.iterator();
        while (it.hasNext()) {
            urls[index] = it.next();
            index++;
        }
        final Set<WatcherDefinition> watcherDefines = new HashSet<>();
        for (final URL url : defineUrls) {
            try {
                Class<?> defineCls = WatcherClassLoader.getClassLoader().loadClass(url.getPath());
                int modifiers = defineCls.getModifiers();
                if (Modifier.isInterface(modifiers) || Modifier.isAbstract(modifiers)
                        || Modifier.isFinal(modifiers) || Modifier.isNative(modifiers)
                        || Modifier.isStatic(modifiers) || defineCls.isAnnotation()
                        || defineCls.isEnum() || defineCls.isMemberClass()
                        || !Modifier.isPublic(modifiers)) {
                    continue;
                }

                //normal
                WatcherDefinition normalDefine = watcherDefineNormal(defineCls);
                if (null != normalDefine) {
                    watcherDefines.add(normalDefine);
                    continue;
                }
                //async
                WatcherDefinition asyncDefine = watcherDefineAsync(defineCls);
                if (null != asyncDefine) {
                    watcherDefines.add(asyncDefine);
                }
            } catch (Exception e) {
                CaptainLogger.error(this.getClass(), e);
            }
        }
        //sys define async
        watcherDefines.addAll(loadSystemAsyncDefine());
        final Set<WatcherDefine> watcherDefineSet = new HashSet<>();
        if (watcherDefines.size() > 0) {
            watcherDefines.forEach(watcherDefinition -> {
                watcherDefineSet.add(WatcherDefine.newDefine(watcherDefinition));
            });
        }
        WatcherHolder.HOLDER.add(WatcherKey.WATCHER_DEFINE_KEY, watcherDefineSet);
    }


    private WatcherDefinition watcherDefineNormal(Class<?> defineCls) {
        Watcher classWatcherAnnotation = defineCls.getAnnotation(Watcher.class);
        boolean classTag = (null != classWatcherAnnotation);
        if (classTag) {
            if (!classWatcherAnnotation.active()) {
                return null;
            }
        }
        WatcherDefinition watcherDefine = new WatcherDefinition();
        watcherDefine.setClassName(defineCls.getName());
        watcherDefine.setClazz(defineCls);
        watcherDefine.setWatcherName((!classTag || "".equals(classWatcherAnnotation.name())) ? "default-" + defineCls.getName()
                : classWatcherAnnotation.name());
        final Set<WatcherDefinition.MethodDefine> methodDefines = new HashSet<>();
        for (final Method method : defineCls.getMethods()) {
            int methodModifiers = method.getModifiers();
            if (!Modifier.isPublic(methodModifiers) || Modifier.isStatic(methodModifiers) || Modifier.isAbstract(methodModifiers)
                    || Modifier.isNative(methodModifiers)) {
                continue;
            }
            if (ExcludeMethods.include(method.getName())) {
                continue;
            }
            Watcher methodWatcherAnnotation = method.getAnnotation(Watcher.class);
            if (null == methodWatcherAnnotation) {
                //如果方法注解为空，但是类已注解，则加入
                if (classTag) {
                    WatcherDefinition.MethodDefine methodDefine = watcherDefine.new MethodDefine();
                    methodDefine.setMethodName(method.getName());
                    methodDefine.setParameterTypes(method.getParameterTypes());
                    if (null != method.getReturnType()) {
                        methodDefine.setReturnTypes(method.getReturnType());
                    }
                    methodDefine.setWatcherName("default-" + defineCls.getName() + "-" + method.getName());
                    methodDefines.add(methodDefine);
                }
            } else {
                //如果方法有注解，则以方法注解为主
                if (methodWatcherAnnotation.active()) {
                    WatcherDefinition.MethodDefine methodDefine = watcherDefine.new MethodDefine();
                    methodDefine.setMethodName(method.getName());
                    methodDefine.setParameterTypes(method.getParameterTypes());
                    if (null != method.getReturnType()) {
                        methodDefine.setReturnTypes(method.getReturnType());
                    }
                    methodDefine.setWatcherName("".equals(methodWatcherAnnotation.name()) ?
                            "default-" + defineCls.getName() + "-" + method.getName() :
                            methodWatcherAnnotation.name());
                    methodDefines.add(methodDefine);
                }
            }
        }
        //如果没有方法，则返回null
        if (methodDefines.isEmpty()) {
            return null;
        }
        watcherDefine.setMethods(methodDefines);
        return watcherDefine;
    }


    private WatcherDefinition watcherDefineAsync(Class<?> defineCls) {
        CrossThread crossThread = defineCls.getAnnotation(CrossThread.class);
        if (null == crossThread) {
            return null;
        }
        try {
            WatcherDefinition watcherDefinition = new WatcherDefinition();
            watcherDefinition.setClassName(defineCls.getName());
            watcherDefinition.setDefineId("Async-" + defineCls.getName());
            watcherDefinition.setClazz(defineCls);
            watcherDefinition.setAsync(true);
            final Set<WatcherDefinition.MethodDefine> methodDefines = new HashSet<>();
            WatcherDefinition.MethodDefine methodDefine = watcherDefinition.new MethodDefine();
            Method method = null;
            if (Runnable.class.isAssignableFrom(defineCls)) {
                method = defineCls.getMethod("run", new Class[]{});
            }
            if (Callable.class.isAssignableFrom(defineCls)) {
                method = defineCls.getMethod("call", new Class[]{});
            }
            if (null == method) {
                return null;
            }
            methodDefine.setReturnTypes(method.getReturnType());
            methodDefine.setMethodName(method.getName());
            methodDefine.setWatcherName(watcherDefinition.getWatcherName() + "-" + methodDefine.getMethodName());
            methodDefines.add(methodDefine);
            watcherDefinition.setMethods(methodDefines);
            return watcherDefinition;
        } catch (Exception e) {
            CaptainLogger.error(this.getClass(), "Async Watcher Define By User err:" + e.getMessage());
            return null;
        }
    }

    private Set<WatcherDefinition> loadSystemAsyncDefine() {
        final Set<WatcherDefinition> sysDefines = new HashSet<>();
        try {
            WatcherDefinition runnableDefine = watcherDefineAsync(RunnableWrapper.class);
            if (null == runnableDefine) {
                CaptainLogger.error(this.getClass(), "can't load Async Watcher Define By RunnableWrapper");
            } else {
                sysDefines.add(runnableDefine);
            }
            WatcherDefinition callableDefine = watcherDefineAsync(CallableWrapper.class);
            if (null == callableDefine) {
                CaptainLogger.error(this.getClass(), "can't load Async Watcher Define By callableWrapper");
            } else {
                sysDefines.add(callableDefine);
            }
        } catch (Exception e) {
            CaptainLogger.error(this.getClass(), "Async Watcher Define By Wrapper err:" + e.getMessage());
        }
        return sysDefines;
    }
}
