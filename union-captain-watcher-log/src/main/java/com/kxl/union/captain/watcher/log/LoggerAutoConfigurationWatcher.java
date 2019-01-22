package com.kxl.union.captain.watcher.log;

import com.kxl.union.captain.watcher.AutoConfigurationWatcher;
import com.kxl.union.captain.watcher.log.data.LoggerWatcherRecordLoader;
import com.kxl.union.captain.watcher.log.holder.LoggerHolder;
import com.kxl.union.captain.watcher.log.holder.LoggerHolderKey;
import com.kxl.union.captain.watcher.log.sub.LoggerWatcherSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

public enum LoggerAutoConfigurationWatcher {
    LOGGER;
    private final Logger logger = LoggerFactory.getLogger(LoggerAutoConfigurationWatcher.class);
    private final AtomicBoolean addLogger = new AtomicBoolean(false);


    public void autoInit(String logPath) {
        synchronized (LoggerAutoConfigurationWatcher.class) {
            if (null == logPath || "".equals(logPath)) {
                logger.error("Init Logger AutoConfiguration Watcher Err, logPath is null.");
                return;
            }
            if (!addLogger.get()) {
                AutoConfigurationWatcher.AUTO.addWatcherSubscriber(new LoggerWatcherSubscriber());
                AutoConfigurationWatcher.AUTO.initAnalysis(new LoggerWatcherRecordLoader());
                LoggerHolder.HOLDER.add(LoggerHolderKey.LOGGER_PATH, logPath);
                addLogger.set(true);
            }
        }
    }
}
