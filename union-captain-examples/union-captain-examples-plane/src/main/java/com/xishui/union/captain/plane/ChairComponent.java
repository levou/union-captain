package com.xishui.union.captain.plane;

import com.xishui.union.captain.common.logger.CaptainLogger;
import com.xishui.union.captain.component.ComponentCaptain;

public class ChairComponent implements ComponentCaptain {

    public String chair(String name) {
        CaptainLogger.info(this.getClass(), "Plane Component(Chair Component) doing," + name);
        return "椅子制作完成";
    }
}
