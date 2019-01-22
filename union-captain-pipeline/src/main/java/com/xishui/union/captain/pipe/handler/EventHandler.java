package com.xishui.union.captain.pipe.handler;

import com.xishui.union.captain.pipe.context.EventContext;
import com.xishui.union.captain.pipe.exception.EventPipelineException;

import java.util.Map;

//事件处理对象
public interface EventHandler<Req, Resp> {

    EventContext<Req, Resp> handler(EventContext<Req, Resp> eventContext, Map<String, Object> extendParams) throws EventPipelineException;

    void causeException(EventContext<Req, Resp> eventContext);
}
