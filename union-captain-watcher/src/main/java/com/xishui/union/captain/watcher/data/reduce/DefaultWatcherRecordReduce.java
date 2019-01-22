package com.xishui.union.captain.watcher.data.reduce;

import com.xishui.union.captain.watcher.data.WatcherRecordReduce;
import com.xishui.union.captain.watcher.model.WatcherRecord;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultWatcherRecordReduce implements WatcherRecordReduce {
    @Override
    public Map<String, List<WatcherRecord>> reduce(List<WatcherRecord> watcherRecords) {
        if (null == watcherRecords || watcherRecords.size() <= 0) {
            return null;
        }
        //根据watcherId group by
        final Map<String, List<WatcherRecord>> reduceMap = watcherRecords.stream()
                .collect(Collectors.groupingBy(WatcherRecord::getWatcherId));
        //list的值排个序
        reduceMap.entrySet().stream().forEach(e -> e.getValue().stream().sorted(new Comparator<WatcherRecord>() {
            @Override
            public int compare(WatcherRecord o1, WatcherRecord o2) {
                return (o1.getSequence().intValue() > o2.getSequence().intValue()) ? 1 : -1;
            }
        }));
        return reduceMap;
    }
}
