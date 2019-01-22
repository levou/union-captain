package com.kxl.union.captain.bus;

import com.kxl.union.captain.common.logger.CaptainLogger;
import com.kxl.union.captain.component.ComponentCaptain;
import com.kxl.union.captain.watcher.annotation.Watcher;

@Watcher
public class MotorComponent implements ComponentCaptain{
    @Watcher
    public void motor(String name) {
        CaptainLogger.info(this.getClass(), "Bus Component(Motor Component) doing-" + name);
    }
}
