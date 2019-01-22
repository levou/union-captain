package com.kxl.union.captain.bus;

import com.kxl.union.captain.common.logger.CaptainLogger;
import com.kxl.union.captain.component.ComponentCaptain;
import com.kxl.union.captain.watcher.annotation.Watcher;

public class TyreComponent implements ComponentCaptain {
    @Watcher(active = true, name = "tyre-watcher")
    public void tyre(String name) {
        CaptainLogger.info(this.getClass(), "Bus Component(Tyre Component) doing-" + name);
    }

    public void mulitTyre(String name) {
        CaptainLogger.info(this.getClass(), "Bus Component(MulitTyre Component) doing-" + name);
    }
}
