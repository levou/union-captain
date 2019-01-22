package com.kxl.union.captain.watcher.bytebuddy.intercept;

import com.kxl.union.captain.watcher.bytebuddy.AsyncEnhance;
import com.kxl.union.captain.watcher.context.AsyncContext;
import com.kxl.union.captain.watcher.context.WatcherContext;

import java.lang.reflect.Method;

public class AsyncWatcherAroundHook implements WatcherAroundHook {
    @Override
    public void beforeMethod(Object obj, Class<?> clazz, Object[] allArguments, Method method) {
        //add to new ThreadLocal
        if(obj instanceof AsyncEnhance){
            AsyncEnhance asyncEnhance = (AsyncEnhance)obj;
            AsyncContext context = asyncEnhance.getAsyncContext();
            WatcherContext.continueContext(context);
        }
    }

    @Override
    public Object afterMethod(Object obj, Class<?> clazz, Object[] allArguments, Method method, Object result) {

        return result;
    }

    @Override
    public void handlerException(Object obj, Class<?> clazz, Object[] allArguments, Method method, Throwable throwable) {
        //nothing
    }
}
