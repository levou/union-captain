package com.xishui.union.captain.quartz;

import com.xishui.union.captain.quartz.exception.QuartzException;
import com.xishui.union.captain.quartz.factory.ScheduleFactoryBoot;
import org.quartz.Scheduler;

import java.util.Properties;

public enum QuartzBoot {
    BOOT;

    public void startScheduler(Properties properties, String schedulerName) throws QuartzException {
        try {
            Scheduler scheduler = ScheduleFactoryBoot.BOOT.builderScheduler(properties, schedulerName);
            if (!scheduler.isStarted()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new QuartzException(e);
        }
    }
}
