package com.xishui.union.captain.bus;

import com.xishui.union.captain.common.logger.CaptainLogger;
import com.xishui.union.captain.component.AbstractComponent;
import com.xishui.union.captain.component.ComponentExporter;
import com.xishui.union.captain.component.exception.ComponentBootException;

public class BusComponentCaptain extends AbstractComponent {
    //做一些前置准备验证
    @Override
    public void prepare() throws ComponentBootException {

        super.prepare();
        CaptainLogger.info(this.getClass(),"Bus ComponentCaptain Prepare doing");
    }
    //做一些自己的初始化等工作
    @Override
    public void doBoot() {
        CaptainLogger.info(this.getClass(), "Bus ComponentCaptain Boot doing");
    }

    @Override
    public ComponentExporter exporterInstance() {
        return new BusComponentExporter();
    }
}
