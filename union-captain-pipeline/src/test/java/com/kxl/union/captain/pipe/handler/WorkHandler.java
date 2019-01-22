package com.kxl.union.captain.pipe.handler;

import com.kxl.union.captain.pipe.Request;
import com.kxl.union.captain.pipe.Response;
import com.kxl.union.captain.pipe.context.EventContext;
import com.kxl.union.captain.pipe.exception.EventPipelineException;

import java.util.Map;

public class WorkHandler extends GenericEventHandler<Request,Response>{
    @Override
    protected void doHandler(EventContext<Request, Response> eventContext, Map<String, Object> extendParams) throws EventPipelineException {
        System.out.println("Work Handler doing " + eventContext.eventRequest().request().getStep());
    }
}
