package com.kxl.union.captain.watcher.log.sub;

import com.alibaba.fastjson.JSON;
import com.kxl.union.captain.watcher.model.WatcherRecord;
import com.kxl.union.captain.watcher.pubsub.AbstractWatcherSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class LoggerWatcherSubscriber extends AbstractWatcherSubscriber {

    private final Logger logger = LoggerFactory.getLogger(LoggerWatcherSubscriber.class);

    @Override
    public void subscriber(WatcherRecord param) {
        if (null == param) {
            logger.info("subscriber Watcher Record is null.");
        } else {
            logger.info(JSON.toJSONString(param));
        }
    }
}
