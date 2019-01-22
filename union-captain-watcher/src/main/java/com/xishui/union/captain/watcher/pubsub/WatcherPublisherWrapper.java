package com.xishui.union.captain.watcher.pubsub;

import com.xishui.union.captain.common.pubsub.PSConfig;
import com.xishui.union.captain.common.pubsub.Publisher;
import com.xishui.union.captain.watcher.model.WatcherRecord;

public enum WatcherPublisherWrapper {
    WRAPPER;

    private final Publisher<WatcherRecord> publisher = new WatcherPublisher();

    public void init(PSConfig psConfig) {
        //todo
    }

    public void pub(WatcherRecord watcherRecord) {
        try {
            WatcherPublisherWrapper.WRAPPER.publisher.publish(watcherRecord);
        } catch (Exception e) {
            e.printStackTrace();
            //todo
        }
    }
}
