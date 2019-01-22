package com.kxl.union.captain.pipe.context;

import com.kxl.union.captain.pipe.attribute.ComplexAttribute;
import com.kxl.union.captain.pipe.exception.EventPipelineException;

public interface EventRequest<Req> extends ComplexAttribute {

    Req request() throws EventPipelineException;

    EventRequest<Req> addRequest(Req req) throws EventPipelineException;

    static <Req>DefaultEventRequest<Req> newRequest(){
        return new DefaultEventRequest<>();
    }
}
