package com.xishui.union.captain.pipe.handler;

import com.xishui.union.captain.pipe.context.EventContext;
import com.xishui.union.captain.pipe.exception.EventPipelineException;

import java.util.Map;

/**
 * 事件handler
 */
public abstract class GenericEventHandler<Req, Resp> implements EventHandler<Req, Resp> {

    @Override
    public final EventContext<Req, Resp> handler(EventContext<Req, Resp> eventContext, Map<String, Object> extendParams) throws EventPipelineException {
        doHandler(eventContext, extendParams);
        return eventContext;
    }

    @Override
    public void causeException(EventContext<Req, Resp> eventContext) {
    }

    protected abstract void doHandler(EventContext<Req, Resp> eventContext, Map<String, Object> extendParams)
            throws EventPipelineException;

}
