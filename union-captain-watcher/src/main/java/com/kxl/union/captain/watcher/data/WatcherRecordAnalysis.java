package com.kxl.union.captain.watcher.data;

import com.kxl.union.captain.watcher.data.analysis.DefaultWatcherRecordAnalysis;
import com.kxl.union.captain.watcher.data.model.WatcherRecordAnalysisModel;
import com.kxl.union.captain.watcher.holder.WatcherHolder;
import com.kxl.union.captain.watcher.holder.WatcherKey;

import java.util.Date;
import java.util.List;

public interface WatcherRecordAnalysis {

    List<WatcherRecordAnalysisModel> analysis();

    List<WatcherRecordAnalysisModel> analysis(Date startDate, Date endDate);

    List<WatcherRecordAnalysisModel> analysis(String watcherId);


    static DefaultWatcherRecordAnalysis newAnalysis(){
        return new DefaultWatcherRecordAnalysis(WatcherHolder.HOLDER.get(WatcherKey.WATCHER_RECORD_ANALYSIS_KEY,
                WatcherRecordLoader.class));
    }
}
