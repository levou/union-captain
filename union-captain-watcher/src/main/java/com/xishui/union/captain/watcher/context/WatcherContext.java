package com.xishui.union.captain.watcher.context;

import com.xishui.union.captain.watcher.model.WatcherRecord;
import com.xishui.union.captain.watcher.model.WatcherSequence;
import com.xishui.union.captain.watcher.pubsub.WatcherPublisherWrapper;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

/**
 * 携带上下文信息
 * 每调用一次，创建一条记录信息
 */
public class WatcherContext {
    @Getter
    private String watcherId;

    public WatcherContext(String watcherId) {
        this.watcherId = watcherId;
    }

    public WatcherContext(AsyncContext asyncContext) {
        this.watcherId = asyncContext.getWatcherId();
        watcherSequence.set(WatcherSequence.continueSequence(asyncContext.getSequence()));
    }

    private static ThreadLocal<WatcherContext> watcherContext = new ThreadLocal<>();
    private static ThreadLocal<WatcherSequence> watcherSequence = new ThreadLocal<>();


    public static WatcherContext context() {
        synchronized (WatcherContext.class) {
            if (null == watcherContext.get()) {
                watcherContext.set(new WatcherContext(UUID.randomUUID().toString() + "-" + System.currentTimeMillis()));
            }
            if (null == watcherSequence.get()) {
                watcherSequence.set(new WatcherSequence());
            }
        }
        return watcherContext.get();
    }

    public static WatcherContext get(){
        return watcherContext.get();
    }
    public static WatcherContext continueContext(AsyncContext asyncContext) {
        final WatcherContext asyncWatcherContext = new WatcherContext(asyncContext);
        watcherContext.set(asyncWatcherContext);
        return asyncWatcherContext;
    }

    public static AsyncContext asyncContext() {
        final WatcherContext watcherContext = WatcherContext.context();
        return AsyncContext.async().watcherId(watcherContext.getWatcherId()).sequence(watcherContext.currentSequence());
    }

    public Integer currentSequence() {
        return watcherSequence.get().currentSequence();
    }

    public Integer nextSequence() {
        return watcherSequence.get().nextSequence();
    }


    public static void publishWatcher(String extendMessage) {
        WatcherContext.publishWatcher(null, null, extendMessage, null);
    }

    public static void publishWatcher(String extendMessage, WatcherRecord.WatcherRecordType watcherRecordType) {
        WatcherContext.publishWatcher(null, null, extendMessage, watcherRecordType);
    }

    public static void publishWatcher(String moduleName, String operatorType, String extendMessage) {
        WatcherContext.publishWatcher(moduleName, operatorType, extendMessage, null);
    }

    public static void publishWatcher(String moduleName, String operatorType, String extendMessage,
                                      WatcherRecord.WatcherRecordType watcherRecordType) {
        try {
            WatcherContext context = WatcherContext.context();
            context.publish(WatcherRecord.newRecord(context.getWatcherId())
                    .extendValue(extendMessage)
                    .moduleName(moduleName)
                    .operatorType(operatorType)
                    .sequence(context.nextSequence())
                    .watcherRecordType((null == watcherRecordType) ? WatcherRecord.WatcherRecordType.OPERATOR.getType()
                            : watcherRecordType.getType())
                    .timestamp(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
            //todo
        }

    }

    //真正pub的地方
    public void publish(final WatcherRecord watcherRecord) {
        WatcherPublisherWrapper.WRAPPER.pub(watcherRecord);
    }
}
