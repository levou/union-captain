package com.xishui.union.captain.watcher.data;

import com.xishui.union.captain.watcher.model.WatcherRecord;

import java.util.Date;
import java.util.List;

public interface WatcherRecordLoader {
    //order by watcherDate
    List<WatcherRecord> loadAllWatcherRecord();
    //通过时间段loader数据，可能会出现链路断裂(时间限制了)
    List<WatcherRecord> loadBetweenData(Date startDate,Date endDate);
    //通过watcherId 查询记录
    List<WatcherRecord> loadByWatcherId(String watcherId);
}
