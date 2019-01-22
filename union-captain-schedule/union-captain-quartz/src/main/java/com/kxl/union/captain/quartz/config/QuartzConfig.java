package com.kxl.union.captain.quartz.config;

import lombok.Data;

@Data
public class QuartzConfig {

    private String jobGroup;
    private String jobName;
    private String triggerGroup;
    private String triggerName;
    private  QuartzTriggerType quartzTriggerType;

}
