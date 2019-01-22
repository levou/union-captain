package com.xishui.union.captain.watcher;

import com.xishui.union.captain.common.pubsub.PSFactory;
import com.xishui.union.captain.watcher.bytebuddy.define.WatcherDefine;
import com.xishui.union.captain.watcher.data.WatcherRecordLoader;
import com.xishui.union.captain.watcher.holder.WatcherHolder;
import com.xishui.union.captain.watcher.holder.WatcherKey;
import com.xishui.union.captain.watcher.model.WatcherRecord;
import com.xishui.union.captain.watcher.pubsub.AbstractWatcherSubscriber;

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
