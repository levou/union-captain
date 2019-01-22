package com.kxl.union.captain.pipe.context;

import com.kxl.union.captain.pipe.attribute.ComplexAttribute;
import com.kxl.union.captain.pipe.exception.EventPipelineException;

public interface EventResponse<Resp> extends ComplexAttribute {

    Resp response() throws EventPipelineException;

    EventResponse<Resp> addResponse(Resp resp) throws EventPipelineException;

    static <Resp>DefaultEventResponse<Resp> newResponse(){
        return new DefaultEventResponse<>();
    }
}
