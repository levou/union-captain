package com.xishui.union.captain.watcher.bytebuddy.define;

import com.xishui.union.captain.watcher.bytebuddy.intercept.AsyncWatcherAroundHook;
import com.xishui.union.captain.watcher.bytebuddy.intercept.DefaultWatcherAroundHook;
import com.xishui.union.captain.watcher.bytebuddy.intercept.WatcherAroundHook;
import com.xishui.union.captain.watcher.bytebuddy.matcher.ClazzMatcher;
import com.xishui.union.captain.watcher.bytebuddy.matcher.Matcher;
import com.xishui.union.captain.watcher.model.WatcherDefinition;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Constructor;

public class DefaultWatcherDefine extends AbstractWatcherDefine {

    private WatcherDefinition watcherDefinition;


    public DefaultWatcherDefine(WatcherDefinition watcherDefinition) {
        this.watcherDefinition = watcherDefinition;
    }

    @Override
    protected ElementMatcher<MethodDescription> methodMatcher() {
        final ElementMatcher.Junction<MethodDescription> methodDescriptorJunction = ElementMatchers.any();
        if (null == watcherDefinition.getMethods() || watcherDefinition.getMethods().size() <= 0) {
            return methodDescriptorJunction;
        }
        watcherDefinition.getMethods().forEach(m -> methodDescriptorJunction.or(ElementMatchers.named(m.getMethodName())));
        return methodDescriptorJunction;
    }


    @Override
    public Matcher matcher() {
        return ClazzMatcher.of(watcherDefinition.getClassName());
    }

    //先默认这里吧
    @Override
    protected Class<? extends WatcherAroundHook> interceptor() {
        if (watcherDefinition.isAsync()) {
            return AsyncWatcherAroundHook.class;
        }
        return DefaultWatcherAroundHook.class;
    }

    @Override
    protected boolean isAsync() {
        return watcherDefinition.isAsync();
    }

    @Override
    protected ElementMatcher<MethodDescription> constructorMatcher() {
        final ElementMatcher.Junction<MethodDescription> constructorMatcher = ElementMatchers.any();
        for (Constructor constructor : watcherDefinition.getClazz().getConstructors()) {
            constructorMatcher.or(ElementMatchers.isConstructor().and(ElementMatchers.named(constructor.getName())
                    .and(ElementMatchers.not(ElementMatchers.isStatic()))));
        }
        return constructorMatcher;
    }
}
