package com.xishui.union.captain.plane;

import com.xishui.union.captain.common.logger.CaptainLogger;
import com.xishui.union.captain.component.ComponentCaptain;

public class CabinComponent implements ComponentCaptain {

    public void cabin(String name) {
        CaptainLogger.info(this.getClass(), "Plane Component(Cabin Component) doing," + name);
    }
}
