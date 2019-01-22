package com.xishui.union.captain.watcher.bytebuddy.intercept;

import com.xishui.union.captain.watcher.bytebuddy.AsyncEnhance;
import com.xishui.union.captain.watcher.context.AsyncContext;
import com.xishui.union.captain.watcher.context.WatcherContext;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;

//构造方法的after，set相关值
public class AsyncConstructorInterceptor {

    @RuntimeType
    public void intercept(@Origin Class<?> clazz, @This Object obj) {
        AsyncContext asyncContext = WatcherContext.asyncContext();
        if (null != asyncContext) {
            AsyncEnhance enhance = (AsyncEnhance) obj;
            enhance.setAsyncContext(asyncContext);
        }
    }
}
