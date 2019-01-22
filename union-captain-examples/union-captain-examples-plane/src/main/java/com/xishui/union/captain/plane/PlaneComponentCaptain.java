package com.xishui.union.captain.plane;

import com.xishui.union.captain.common.logger.CaptainLogger;
import com.xishui.union.captain.component.AbstractComponent;
import com.xishui.union.captain.component.ComponentExporter;
import com.xishui.union.captain.component.exception.ComponentBootException;

import java.util.Set;

public class PlaneComponentCaptain extends AbstractComponent {
    @Override
    public Set<String> dependencyComponentNames() throws ComponentBootException {
        return super.dependencyComponentNames();
    }

    @Override
    public void prepare() throws ComponentBootException {
        super.prepare();
        CaptainLogger.info(this.getClass(),"Plane ComponentCaptain prepare doing");
    }

    @Override
    public void doBoot() {
        CaptainLogger.info(this.getClass(),"Plane ComponentCaptain Boot doing");
    }

    @Override
    public ComponentExporter exporterInstance() {
        return new PlaneComponentExporter();
    }
}
