package com.kxl.union.captain.watcher.bytebuddy.intercept;

import com.kxl.union.captain.common.logger.CaptainLogger;
import com.kxl.union.captain.watcher.bytebuddy.loader.InterceptorLoader;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class WatcherInterceptor {

    private final Class<? extends WatcherAroundHook> interceptorClazz;

    public WatcherInterceptor(Class<? extends WatcherAroundHook> interceptorClazz) {
        this.interceptorClazz = interceptorClazz;
    }

    @RuntimeType
    public Object intercept(@This Object obj, @Origin Class<?> clazz, @AllArguments Object[] allArguments, @Origin Method method,
                            @SuperCall Callable<?> callable) throws Throwable {
//        CaptainLogger.info(this.getClass(), "intercept for class:" + obj.getClass().getName());
        //执行体
        final WatcherAroundHook watcherExecutor = InterceptorLoader.LOADER.get(interceptorClazz);
        try {
            watcherExecutor.beforeMethod(obj, clazz, allArguments, method);
        } catch (Throwable e) {
            CaptainLogger.error(this.getClass(), "Watcher Intercept beforeMethod Err:" + e.getMessage());
        }
        Object result = null;
        try {
            result = callable.call();
        } catch (Throwable e) {
            try {
                watcherExecutor.handlerException(obj, clazz, allArguments, method, e);
            } catch (Throwable e1) {
                CaptainLogger.error(this.getClass(), "Watcher Intercept handlerException Err:" + e.getMessage());
            }
            throw e;
        } finally {
            try {
                watcherExecutor.afterMethod(obj, clazz, allArguments, method, result);
            } catch (Throwable e) {
                CaptainLogger.error(this.getClass(), "Watcher Intercept afterMethod Err:" + e.getMessage());
            }
        }
        return result;
    }
}
