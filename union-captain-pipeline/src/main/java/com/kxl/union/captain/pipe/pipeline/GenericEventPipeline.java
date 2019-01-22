package com.kxl.union.captain.pipe.pipeline;

import com.kxl.union.captain.common.collection.ComplexDeLinked;
import com.kxl.union.captain.common.collection.DeLinked;
import com.kxl.union.captain.pipe.exception.EventPipelineException;
import com.kxl.union.captain.pipe.handler.EventHandler;
import lombok.Getter;

public abstract class GenericEventPipeline<Req, Resp> implements ComplexEventPipeline<Req, Resp> {
    //队列
    @Getter
    private final DeLinked<EventHandler<Req, Resp>> HANDLERS = ComplexDeLinked.emptyDeLinked();

    private EventHandler<Req, Resp> finalHandler;

    @Override
    public EventPipeline<Req, Resp> add(EventHandler<Req, Resp> eventHandler) {
        this.HANDLERS.add(eventHandler);
        return this;
    }

    @Override
    public EventPipeline<Req, Resp> addFirst(EventHandler<Req, Resp> eventHandler) {
        this.HANDLERS.addFirst(eventHandler);
        return this;
    }

    @Override
    public EventPipeline<Req, Resp> addLast(EventHandler<Req, Resp> eventHandler) {
        this.HANDLERS.addLast(eventHandler);
        return this;
    }

    @Override
    public EventHandler<Req, Resp> next() throws EventPipelineException {
        return HANDLERS.next();
    }

    @Override
    public EventHandler<Req, Resp> pre() throws EventPipelineException {
        return HANDLERS.pre();
    }

    @Override
    public void addFinal(EventHandler<Req, Resp> eventHandler) throws EventPipelineException {
        this.finalHandler = eventHandler;
    }

    @Override
    public EventHandler<Req, Resp> finalHandler() throws EventPipelineException {
        return finalHandler;
    }

    @Override
    public void resetFirst() {
        HANDLERS.resetHead();
    }

    @Override
    public void descreament() {
        HANDLERS.descreament();
    }
}
