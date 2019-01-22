package com.xishui.union.captain.quartz.holder;

import com.xishui.union.captain.quartz.exception.QuartzException;
import com.xishui.union.captain.quartz.factory.ScheduleFactoryBoot;
import org.quartz.Scheduler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum SchedulerHolder {
    HOLDER;
    private final static Map<String, Scheduler> SCHEDULER = new ConcurrentHashMap<>();

    public void addScheduler(String schedulerName, Scheduler scheduler) {
        SCHEDULER.putIfAbsent(schedulerName, scheduler);
    }

    public Scheduler getSchduler(String schedulerName) throws QuartzException {
        try {
            return SCHEDULER.getOrDefault(schedulerName, ScheduleFactoryBoot.BOOT.builderScheduler());
        } catch (Exception e) {
            throw new QuartzException(e);
        }
    }
}
