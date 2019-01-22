package com.kxl.union.captain.pipe.handler;

import com.kxl.union.captain.pipe.Request;
import com.kxl.union.captain.pipe.Response;
import com.kxl.union.captain.pipe.context.EventContext;
import com.kxl.union.captain.pipe.context.EventMessage;
import com.kxl.union.captain.pipe.exception.EventPipelineException;

import java.util.Map;

public class LunchHandler extends GenericEventHandler<Request, Response> {
    @Override
    protected void doHandler(EventContext<Request, Response> eventContext, Map<String, Object> extendParams) throws EventPipelineException {
        System.out.println("Lunch Handler doing " + eventContext.eventRequest().request().getStep());
        eventContext.setPause(true);
        eventContext.addEventMessage(EventMessage.newMessage("测试异常呀"));
        throw new EventPipelineException("假装抛出异常");
    }

    @Override
    public void causeException(EventContext<Request, Response> eventContext) {
        System.out.println("Lunch Handler back " + eventContext.eventMessage().throwable() + " " + eventContext.<String>eventMessage().message());

    }
}
