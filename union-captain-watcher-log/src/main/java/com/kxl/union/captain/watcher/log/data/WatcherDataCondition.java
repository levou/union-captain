package com.kxl.union.captain.watcher.log.data;

import lombok.Data;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class WatcherDataCondition {

    private Date startDate;

    private Date endDate;

    private String watcherId;

    private boolean canWatcherId = false;

    private boolean canDate = false;

    private AtomicInteger counter = new AtomicInteger(0);

    public static WatcherDataCondition newCondition() {
        return new WatcherDataCondition();
    }

    public WatcherDataCondition startDate(Date startDate) {
        this.startDate = startDate;
        this.canDate = true;
        return this;
    }

    public WatcherDataCondition endDate(Date endDate) {
        this.endDate = endDate;
        this.canDate = true;
        return this;
    }

    public WatcherDataCondition watcherId(String watcherId) {
        this.watcherId = watcherId;
        this.canWatcherId = true;
        return this;
    }
}
