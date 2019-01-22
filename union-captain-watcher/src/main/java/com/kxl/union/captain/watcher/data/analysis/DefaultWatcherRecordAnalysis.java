package com.kxl.union.captain.watcher.data.analysis;

import com.alibaba.fastjson.JSON;
import com.kxl.union.captain.common.logger.CaptainLogger;
import com.kxl.union.captain.watcher.data.WatcherRecordLoader;
import com.kxl.union.captain.watcher.data.model.WatcherRecordAnalysisModel;
import com.kxl.union.captain.watcher.model.WatcherRecord;

import java.util.List;
import java.util.Map;

public class DefaultWatcherRecordAnalysis extends AbstractWatcherRecordAnalysis {
    public DefaultWatcherRecordAnalysis(WatcherRecordLoader watcherRecordLoader) {
        super(watcherRecordLoader);
    }

    @Override
    protected List<WatcherRecordAnalysisModel> doAnalysis(Map<String, List<WatcherRecord>> watcherRecords) {
        //分析
        if (null == watcherRecords || watcherRecords.isEmpty()) {
            return null;
        }
        watcherRecords.forEach((k, v) -> {
            CaptainLogger.info(this.getClass(), k);
            v.forEach(r -> CaptainLogger.info(this.getClass(), "\t" + JSON.toJSONString(r)));
        });

        return null;
    }
}
