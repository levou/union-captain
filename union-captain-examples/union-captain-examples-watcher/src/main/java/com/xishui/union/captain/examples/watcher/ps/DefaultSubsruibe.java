package com.xishui.union.captain.examples.watcher.ps;

import com.alibaba.fastjson.JSON;
import com.xishui.union.captain.common.logger.CaptainLogger;
import com.xishui.union.captain.common.pubsub.Subscriber;
import com.xishui.union.captain.watcher.model.WatcherRecord;

public class DefaultSubsruibe implements Subscriber<WatcherRecord> {
    @Override
    public void subscriber(WatcherRecord param) {
        CaptainLogger.info(this.getClass(), "sub  message:" + JSON.toJSONString(param));
    }
}
