package com.kxl.union.captain.pipe.context;

import com.kxl.union.captain.pipe.EventGetter;
import com.kxl.union.captain.pipe.EventSetter;
import com.kxl.union.captain.pipe.Pause;
import com.kxl.union.captain.pipe.attribute.ComplexAttribute;
import com.kxl.union.captain.pipe.exception.EventPipelineException;
import com.kxl.union.captain.pipe.pipeline.ComplexEventPipeline;

/**
 * 事件上下文(持有事件pipeline)
 * 1.主要包含请求对象
 * 2.返回对象
 * 3.常见
 */
public interface EventContext<Req, Resp> extends ComplexAttribute, Pause<Boolean>,
        EventSetter<ComplexEventPipeline<Req, Resp>>, EventGetter<ComplexEventPipeline<Req, Resp>> {
    EventRequest<Req> eventRequest() throws EventPipelineException;

    EventResponse<Resp> eventResponse() throws EventPipelineException;

    EventContext<Req, Resp> addEventRequest(EventRequest<Req> eventRequest) throws EventPipelineException;

    EventContext<Req, Resp> addEventResponse(EventResponse<Resp> eventResponse) throws EventPipelineException;

    boolean isPause();

    void exceptionPipeline(boolean flag);

    boolean exceptionRollback();

    <M> EventMessage<M> eventMessage() throws EventPipelineException;

    <M> void addEventMessage(EventMessage<M> eventMessage) throws EventPipelineException;
}
