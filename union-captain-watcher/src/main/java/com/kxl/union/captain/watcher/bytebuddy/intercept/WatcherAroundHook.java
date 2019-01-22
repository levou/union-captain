package com.kxl.union.captain.watcher.bytebuddy.intercept;

import java.lang.reflect.Method;

public interface WatcherAroundHook {

    void beforeMethod(Object obj,Class<?> clazz, Object[] allArguments, Method method);

    Object afterMethod(Object obj,Class<?> clazz, Object[] allArguments, Method method, Object result);

    void handlerException(Object obj,Class<?> clazz, Object[] allArguments, Method method, Throwable throwable);
}
