package com.kxl.union.captain.pipe.handler;

import com.kxl.union.captain.pipe.Request;
import com.kxl.union.captain.pipe.Response;
import com.kxl.union.captain.pipe.context.EventContext;
import com.kxl.union.captain.pipe.exception.EventPipelineException;

import java.util.Map;

public class FinalHandler extends GenericEventHandler<Request,Response>{
    @Override
    protected void doHandler(EventContext<Request, Response> eventContext, Map<String, Object> extendParams) throws EventPipelineException {
        System.out.println("Final Handler doing " + eventContext.eventRequest().request().getStep());
        eventContext.eventResponse().addResponse(new Response("Final"));
    }

    @Override
    public void causeException(EventContext<Request, Response> eventContext) {
        System.out.println("Final Handler back " + eventContext.eventMessage().throwable().getMessage() + " " + eventContext.<String>eventMessage().message());
        eventContext.eventResponse().addResponse(new Response("Final"));
    }
}
