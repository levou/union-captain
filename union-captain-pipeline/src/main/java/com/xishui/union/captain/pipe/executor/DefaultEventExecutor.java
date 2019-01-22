package com.xishui.union.captain.pipe.executor;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.xishui.union.captain.common.wrapper.CallableWrapper;
import com.xishui.union.captain.pipe.context.EventContext;
import com.xishui.union.captain.pipe.context.EventMessage;
import com.xishui.union.captain.pipe.context.EventResponse;
import com.xishui.union.captain.pipe.exception.EventExecutorException;
import com.xishui.union.captain.pipe.exception.EventPipelineException;
import com.xishui.union.captain.pipe.handler.EventHandler;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public class DefaultEventExecutor<Req, Resp> implements EventExecutor<Req, Resp> {
    private ListeningExecutorService executorService = null;

    @Override
    public EventResponse<Resp> syncExecutor(EventContext<Req, Resp> eventContext) throws EventExecutorException {
        return doExecutor(eventContext, null);
    }

    @Override
    public ListenableFuture<EventResponse<Resp>> asyncExecutor(final EventContext<Req, Resp> eventContext) throws EventExecutorException {
        return executorService.submit(CallableWrapper.of(new Callable<EventResponse<Resp>>() {
            @Override
            public EventResponse<Resp> call() throws Exception {
                return doExecutor(eventContext, null);
            }
        }));
    }

    @Override
    public void set(ExecutorService executorService) throws EventPipelineException {
        this.executorService = MoreExecutors.listeningDecorator(executorService);
    }

    @Override
    public ExecutorService get() throws EventExecutorException {
        return executorService;
    }

    private final EventResponse<Resp> doExecutor(EventContext<Req, Resp> eventContext, final Map<String, Object> extendParams) {
        EventHandler<Req, Resp> eventHandler = null;
        //执行前做一些初始化
        eventContext.get().resetFirst();
        eventContext.addEventResponse(EventResponse.newResponse());
        boolean isException = false;
        try {
            //正向
            while (null != (eventHandler = eventContext.get().next())) {
                eventContext = eventHandler.handler(eventContext, extendParams);
                if (eventContext.isPause()) {
                    return eventContext.eventResponse();
                }
            }
        } catch (Exception e) {
            //往回查找，并执行
            isException = true;
            eventContext.get().descreament();
            if (null == eventContext.eventMessage()) {
                eventContext.addEventMessage(EventMessage.newMessage());
            }
            eventContext.eventMessage().throwable(e);
            //current handler.
            eventHandler.causeException(eventContext);
            if(eventContext.exceptionRollback()) {
                while (null != (eventHandler = eventContext.get().pre())) {
                    eventHandler.causeException(eventContext);
                }
            }
            if (null != eventContext.get().finalHandler()) {
                eventContext.get().finalHandler().causeException(eventContext);
            }
        } finally {
            //最终会处理的内容
            if (null != eventContext.get().finalHandler()) {
                if (!isException) {
                    eventContext.get().finalHandler().handler(eventContext, extendParams);
                }
            }
        }
        return eventContext.eventResponse();
    }

    @Override
    public EventResponse<Resp> syncExecutor(EventContext<Req, Resp> eventContext, Map<String, Object> extendParams) throws EventExecutorException {
        return doExecutor(eventContext, extendParams);
    }

    @Override
    public ListenableFuture<EventResponse<Resp>> asyncExecutor(EventContext<Req, Resp> eventContext, final Map<String, Object>
            extendParams) throws EventExecutorException {
        return executorService.submit(CallableWrapper.of(new Callable<EventResponse<Resp>>() {
            @Override
            public EventResponse<Resp> call() throws Exception {
                return doExecutor(eventContext, extendParams);
            }
        }));
    }
}
