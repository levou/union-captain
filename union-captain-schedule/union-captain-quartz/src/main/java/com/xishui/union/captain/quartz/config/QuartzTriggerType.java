package com.xishui.union.captain.quartz.config;

public enum QuartzTriggerType {
    SIMPLE(1),
    CRON(2);
    private int type;

    QuartzTriggerType(int type) {
        this.type = type;
    }
}
