package com.kxl.union.captain.watcher.pubsub;

import com.kxl.union.captain.common.pubsub.PSConfig;
import com.kxl.union.captain.common.pubsub.Publisher;
import com.kxl.union.captain.watcher.model.WatcherRecord;

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
