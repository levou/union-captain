package com.xishui.union.captain.bus;

import com.xishui.union.captain.common.logger.CaptainLogger;
import com.xishui.union.captain.component.ComponentCaptain;
import com.xishui.union.captain.watcher.annotation.Watcher;

public class TyreComponent implements ComponentCaptain {
    @Watcher(active = true, name = "tyre-watcher")
    public void tyre(String name) {
        CaptainLogger.info(this.getClass(), "Bus Component(Tyre Component) doing-" + name);
    }

    public void mulitTyre(String name) {
        CaptainLogger.info(this.getClass(), "Bus Component(MulitTyre Component) doing-" + name);
    }
}
