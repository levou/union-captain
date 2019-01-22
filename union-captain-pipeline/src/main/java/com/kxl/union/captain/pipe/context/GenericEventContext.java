package com.kxl.union.captain.pipe.context;

import com.kxl.union.captain.pipe.attribute.ComplexAttribute;
import com.kxl.union.captain.pipe.attribute.DefaultMapAttribute;
import com.kxl.union.captain.pipe.exception.EventExecutorException;
import com.kxl.union.captain.pipe.exception.EventIllegalException;
import com.kxl.union.captain.pipe.exception.EventPipelineException;
import com.kxl.union.captain.pipe.pipeline.ComplexEventPipeline;
import lombok.Getter;
import lombok.Setter;

public abstract class GenericEventContext<Req, Resp> implements EventContext<Req, Resp> {

    private boolean isPause = false;
    //context 附加携带信息
    private final ComplexAttribute complexAttribute = new DefaultMapAttribute();
    @Setter
    private EventRequest<Req> eventRequest;
    @Setter
    private EventResponse<Resp> eventResponse;

    private ComplexEventPipeline<Req, Resp> complexEventPipeline;

    private EventMessage eventMessage;
    @Getter
    private boolean exceptionPipeline = true;

    @Override
    public void setPause(Boolean aBoolean) {
        this.isPause = aBoolean.booleanValue();
    }

    @Override
    public boolean isPause() {
        return isPause;
    }

    @Override
    public ComplexEventPipeline<Req, Resp> get() throws EventExecutorException {
        return complexEventPipeline;
    }

    @Override
    public void set(ComplexEventPipeline<Req, Resp> reqRespComplexEventPipeline) throws EventPipelineException {
        this.complexEventPipeline = reqRespComplexEventPipeline;
    }

    @Override
    public ComplexAttribute addAttribute(String attributeKey, Object value) throws EventPipelineException {
        return complexAttribute.addAttribute(attributeKey, value);
    }

    @Override
    public <M> M attribute(String key, Class<M> valueType) throws EventIllegalException {
        return complexAttribute.attribute(key, valueType);
    }

    @Override
    public ComplexAttribute removeAttribute(String attributeKey) throws EventPipelineException {
        return complexAttribute.removeAttribute(attributeKey);
    }

    @Override
    public EventRequest<Req> eventRequest() throws EventPipelineException {
        return this.eventRequest;
    }

    @Override
    public EventResponse<Resp> eventResponse() throws EventPipelineException {
        return this.eventResponse;
    }

    @Override
    public EventContext<Req, Resp> addEventRequest(EventRequest<Req> eventRequest) throws EventPipelineException {
        this.eventRequest = eventRequest;
        return this;
    }

    @Override
    public EventContext<Req, Resp> addEventResponse(EventResponse<Resp> eventResponse) throws EventPipelineException {
        this.eventResponse = eventResponse;
        return this;
    }

    @Override
    public <M> EventMessage<M> eventMessage() throws EventPipelineException {
        return this.eventMessage;
    }

    @Override
    public <M> void addEventMessage(EventMessage<M> eventMessage) throws EventPipelineException {
        this.eventMessage = eventMessage;
    }

    @Override
    public Object attribute(String key) throws EventIllegalException {
        return this.complexAttribute.attribute(key);
    }

    @Override
    public void exceptionPipeline(boolean flag) {
        this.exceptionPipeline = flag;
    }

    @Override
    public boolean exceptionRollback() {
        return false;
    }
}
