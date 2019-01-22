package com.kxl.union.captain.watcher.data.analysis;

import com.kxl.union.captain.watcher.data.WatcherRecordAnalysis;
import com.kxl.union.captain.watcher.data.WatcherRecordLoader;
import com.kxl.union.captain.watcher.data.model.WatcherRecordAnalysisModel;
import com.kxl.union.captain.watcher.data.reduce.DefaultWatcherRecordReduce;
import com.kxl.union.captain.watcher.model.WatcherRecord;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class AbstractWatcherRecordAnalysis implements WatcherRecordAnalysis {

    private WatcherRecordLoader watcherRecordLoader;

    public AbstractWatcherRecordAnalysis(WatcherRecordLoader watcherRecordLoader) {
        this.watcherRecordLoader = watcherRecordLoader;
    }

    @Override
    public List<WatcherRecordAnalysisModel> analysis() {
        return doAnalysis(new DefaultWatcherRecordReduce().reduce(watcherRecordLoader.loadAllWatcherRecord()));
    }

    @Override
    public List<WatcherRecordAnalysisModel> analysis(Date startDate, Date endDate) {
        return doAnalysis(new DefaultWatcherRecordReduce().reduce(watcherRecordLoader.loadBetweenData(startDate, endDate)));
    }

    @Override
    public List<WatcherRecordAnalysisModel> analysis(String watcherId) {
        return doAnalysis(new DefaultWatcherRecordReduce().reduce(watcherRecordLoader.loadByWatcherId(watcherId)));
    }

    protected abstract List<WatcherRecordAnalysisModel> doAnalysis(Map<String, List<WatcherRecord>> watcherRecords);
}
