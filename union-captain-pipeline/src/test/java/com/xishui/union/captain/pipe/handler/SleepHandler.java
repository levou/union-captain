package com.xishui.union.captain.pipe.handler;

import com.xishui.union.captain.pipe.Request;
import com.xishui.union.captain.pipe.Response;
import com.xishui.union.captain.pipe.context.EventContext;
import com.xishui.union.captain.pipe.exception.EventPipelineException;

import java.util.Map;

public class SleepHandler extends GenericEventHandler<Request, Response> {
    @Override
    protected void doHandler(EventContext<Request, Response> eventContext, Map<String, Object> extendParams) throws EventPipelineException {
        System.out.println("Sleep Handler doing " + eventContext.eventRequest().request().getStep());
    }

    @Override
    public void causeException(EventContext<Request, Response> eventContext) {
        eventContext.<String>eventMessage().message("更新一下信息");
        System.out.println("Sleep Handler back " + eventContext.eventMessage().throwable().getMessage() + " " + eventContext.eventMessage().message());
    }
}
