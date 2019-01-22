package com.kxl.union.captain.watcher;

import com.kxl.union.captain.common.pubsub.PSFactory;
import com.kxl.union.captain.watcher.bytebuddy.define.WatcherDefine;
import com.kxl.union.captain.watcher.data.WatcherRecordLoader;
import com.kxl.union.captain.watcher.holder.WatcherHolder;
import com.kxl.union.captain.watcher.holder.WatcherKey;
import com.kxl.union.captain.watcher.model.WatcherRecord;
import com.kxl.union.captain.watcher.pubsub.AbstractWatcherSubscriber;

public enum AutoConfigurationWatcher {
    AUTO;

    //添加监听
    public void addWatcherSubscriber(AbstractWatcherSubscriber abstractWatcherSubscriber) {
        PSFactory.<WatcherDefine>factory().init();
        PSFactory.<WatcherRecord>factory().addSubscriber(abstractWatcherSubscriber);
    }

    //添加数据加载器
    public void initAnalysis(WatcherRecordLoader watcherRecordLoader) {
        WatcherHolder.HOLDER.add(WatcherKey.WATCHER_RECORD_ANALYSIS_KEY, watcherRecordLoader);
    }
}
