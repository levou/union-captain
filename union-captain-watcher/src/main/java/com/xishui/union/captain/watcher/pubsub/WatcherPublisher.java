package com.xishui.union.captain.watcher.pubsub;

import com.xishui.union.captain.common.pubsub.AbstractPublisher;
import com.xishui.union.captain.watcher.model.WatcherRecord;

public class WatcherPublisher extends AbstractPublisher<WatcherRecord> {
    @Override
    protected boolean processorParam(WatcherRecord param) {
        //not doing
        return true;
    }
}
