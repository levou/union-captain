package com.kxl.union.captain.watcher.data;

import com.kxl.union.captain.watcher.model.WatcherRecord;

import java.util.List;
import java.util.Map;

public interface WatcherRecordReduce {

    Map<String,List<WatcherRecord>> reduce(List<WatcherRecord> watcherRecords);
}
