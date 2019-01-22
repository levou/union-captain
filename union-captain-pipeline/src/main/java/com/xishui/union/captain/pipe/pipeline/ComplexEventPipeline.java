package com.xishui.union.captain.pipe.pipeline;

import com.xishui.union.captain.pipe.exception.EventPipelineException;
import com.xishui.union.captain.pipe.handler.EventHandler;

public interface ComplexEventPipeline<Req, Resp> extends EventPipeline<Req, Resp> {

    void addFinal(EventHandler<Req, Resp> eventHandler) throws EventPipelineException;

    EventHandler<Req, Resp> finalHandler() throws EventPipelineException;

    void resetFirst();

    void descreament();
}
