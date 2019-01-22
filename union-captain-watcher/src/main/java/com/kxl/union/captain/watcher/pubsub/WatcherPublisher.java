package com.kxl.union.captain.watcher.pubsub;

import com.kxl.union.captain.common.pubsub.AbstractPublisher;
import com.kxl.union.captain.watcher.model.WatcherRecord;

public class WatcherPublisher extends AbstractPublisher<WatcherRecord> {
    @Override
    protected boolean processorParam(WatcherRecord param) {
        //not doing
        return true;
    }
}
