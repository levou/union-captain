package com.xishui.union.captain.pipe;

import com.google.common.util.concurrent.ListenableFuture;
import com.xishui.union.captain.pipe.context.EventContext;
import com.xishui.union.captain.pipe.context.EventResponse;
import com.xishui.union.captain.pipe.exception.EventExecutorException;

import java.util.Map;

//事件的执行体
public interface Executor<Req, Resp> {
    EventResponse<Resp> syncExecutor(EventContext<Req, Resp> eventContext) throws EventExecutorException;

    ListenableFuture<EventResponse<Resp>> asyncExecutor(EventContext<Req, Resp> eventContext) throws EventExecutorException;

    EventResponse<Resp> syncExecutor(EventContext<Req, Resp> eventContext, final Map<String, Object> extendParams) throws
            EventExecutorException;

    ListenableFuture<EventResponse<Resp>> asyncExecutor(EventContext<Req, Resp> eventContext, final Map<String, Object> extendParams)
            throws EventExecutorException;
}
