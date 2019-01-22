package com.xishui.union.captain.quartz.factory;

import com.xishui.union.captain.quartz.exception.QuartzException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Properties;

public enum ScheduleFactoryBoot {
    BOOT;

    public Scheduler builderScheduler() throws QuartzException, SchedulerException {
        final Scheduler  scheduler =  new StdSchedulerFactory().getScheduler();
        return scheduler;
    }

    public Scheduler builderScheduler(Properties properties) throws QuartzException, SchedulerException {
        final Scheduler scheduler =  new StdSchedulerFactory(properties).getScheduler();
        return scheduler;
    }
    @Deprecated
    public Scheduler builderScheduler(String schedulerName) throws QuartzException, SchedulerException {
        final Scheduler scheduler =  new StdSchedulerFactory().getScheduler(schedulerName);
        return scheduler;
    }

    public Scheduler builderScheduler(Properties properties, String schedulerName) throws QuartzException, SchedulerException {
        final Scheduler scheduler = new StdSchedulerFactory(properties).getScheduler(schedulerName);
        return scheduler;
    }
}
