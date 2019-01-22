package com.kxl.union.captain.plane;

import com.kxl.union.captain.common.logger.CaptainLogger;
import com.kxl.union.captain.component.ComponentCaptain;

public class ChairComponent implements ComponentCaptain {

    public String chair(String name) {
        CaptainLogger.info(this.getClass(), "Plane Component(Chair Component) doing," + name);
        return "椅子制作完成";
    }
}
