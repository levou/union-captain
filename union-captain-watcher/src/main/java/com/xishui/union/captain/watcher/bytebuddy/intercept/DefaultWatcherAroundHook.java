package com.xishui.union.captain.watcher.bytebuddy.intercept;

import com.alibaba.fastjson.JSON;
import com.xishui.union.captain.watcher.context.WatcherContext;
import com.xishui.union.captain.watcher.model.WatcherRecord;

import java.lang.reflect.Method;
import java.util.Date;

public class DefaultWatcherAroundHook implements WatcherAroundHook {
    @Override
    public void beforeMethod(Object obj,Class<?> clazz, Object[] allArguments, Method method) {
        final WatcherContext context = WatcherContext.context();
        final WatcherRecord watcherRecord = buildNormal(context);
        watcherRecord.watcherRecordType(WatcherRecord.WatcherRecordType.IN.getType())
                .setWatcherRecordRequest(watcherRecord.newRecordRequest().clazzName(clazz.getName())
                        .jsonParameter(JSON.toJSONString(allArguments)).methodName(method.getName()));
        context.publish(watcherRecord);
    }

    @Override
    public Object afterMethod(Object obj,Class<?> clazz, Object[] allArguments, Method method, Object result) {
        final WatcherContext context = WatcherContext.context();
        final WatcherRecord watcherRecord = buildNormal(context);
        watcherRecord.watcherRecordType(WatcherRecord.WatcherRecordType.OUT.getType())
                .setWatcherRecordResponse(watcherRecord.newRecordResponse().isError(false)
                        .clazzName(clazz.getName())
                        .methodName(method.getName())
                        .response(null == result ? null : JSON.toJSONString(result)));
        context.publish(watcherRecord);
        return result;
    }

    @Override
    public void handlerException(Object obj,Class<?> clazz, Object[] allArguments, Method method, Throwable throwable) {
        final WatcherContext context = WatcherContext.context();
        final WatcherRecord watcherRecord = buildNormal(context);
        watcherRecord.watcherRecordType(WatcherRecord.WatcherRecordType.EXCEPTION.getType())
                .setWatcherRecordResponse(watcherRecord.newRecordResponse().isError(true)
                        .exceptionClazzName(throwable.getClass().getName()).exceptionMessage(throwable.getMessage()));
        context.publish(watcherRecord);
    }

    private WatcherRecord buildNormal(WatcherContext context) {
        return WatcherRecord.newRecord(context.getWatcherId()).sequence(context.nextSequence())
                .timestamp(new Date());
    }
}
