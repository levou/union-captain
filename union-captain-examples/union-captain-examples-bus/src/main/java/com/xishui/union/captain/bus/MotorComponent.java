package com.xishui.union.captain.bus;

import com.xishui.union.captain.common.logger.CaptainLogger;
import com.xishui.union.captain.component.ComponentCaptain;
import com.xishui.union.captain.watcher.annotation.Watcher;

@Watcher
public class MotorComponent implements ComponentCaptain {
    @Watcher
    public void motor(String name) {
        CaptainLogger.info(this.getClass(), "Bus Component(Motor Component) doing-" + name);
    }
}
