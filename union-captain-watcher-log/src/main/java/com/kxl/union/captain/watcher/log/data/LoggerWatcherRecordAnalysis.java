package com.kxl.union.captain.watcher.log.data;

import com.kxl.union.captain.watcher.data.WatcherRecordAnalysis;
import com.kxl.union.captain.watcher.data.model.WatcherRecordAnalysisModel;

import java.util.Date;
import java.util.List;

public enum LoggerWatcherRecordAnalysis {
    ANALYSIS;

    public List<WatcherRecordAnalysisModel> analysis() {
        return WatcherRecordAnalysis.newAnalysis().analysis();
    }

    public List<WatcherRecordAnalysisModel> analysis(Date startDate, Date endDate) {
        return WatcherRecordAnalysis.newAnalysis().analysis(startDate, endDate);
    }

    public List<WatcherRecordAnalysisModel> analysis(String watcherId) {
        return WatcherRecordAnalysis.newAnalysis().analysis(watcherId);
    }
}
