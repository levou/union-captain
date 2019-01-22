package com.kxl.union.captain.plane;

import com.kxl.union.captain.common.logger.CaptainLogger;
import com.kxl.union.captain.component.ComponentCaptain;

public class CabinComponent implements ComponentCaptain {

    public void cabin(String name) {
        CaptainLogger.info(this.getClass(), "Plane Component(Cabin Component) doing," + name);
    }
}
