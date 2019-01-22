package com.xishui.union.captain.pipe;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.xishui.union.captain.pipe.context.DefaultEventContext;
import com.xishui.union.captain.pipe.context.EventContext;
import com.xishui.union.captain.pipe.context.EventRequest;
import com.xishui.union.captain.pipe.context.EventResponse;
import com.xishui.union.captain.pipe.exception.EventPipelineException;
import com.xishui.union.captain.pipe.executor.DefaultEventExecutor;
import com.xishui.union.captain.pipe.executor.EventExecutor;
import com.xishui.union.captain.pipe.handler.EventHandler;
import com.xishui.union.captain.pipe.pipeline.ComplexEventPipeline;
import com.xishui.union.captain.pipe.pipeline.DefaultEventPipeline;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class EventBootstrap<Req, Resp> {
    private ComplexEventPipeline<Req, Resp> targetPipeline = null;
    private EventExecutor<Req, Resp> eventExecutor = null;

    public EventBootstrap() {
        targetPipeline = new DefaultEventPipeline<>();
        eventExecutor = new DefaultEventExecutor<>();
    }

    public static <Req, Resp> EventBootstrap<Req, Resp> boot() {
        return new EventBootstrap();
    }

    public void async(EventRequest<Req> request, EventFutureCallback<EventResponse<Resp>> futureCallback) throws EventPipelineException {
        async(request, futureCallback, false);
    }

    //异步执行
    public void async(EventRequest<Req> request, EventFutureCallback<EventResponse<Resp>> futureCallback, boolean rollback) throws EventPipelineException {
        final EventContext<Req, Resp> eventContext = new DefaultEventContext<>();
        eventContext.addEventRequest(request);
        eventContext.set(targetPipeline);
        checkAndSetExecutorService();
        ListenableFuture<EventResponse<Resp>> listenableFuture = eventExecutor.asyncExecutor(eventContext);
        Futures.addCallback(listenableFuture, futureCallback, MoreExecutors.directExecutor());
    }

    public EventResponse<Resp> sync(EventRequest<Req> request) throws EventPipelineException {
        return sync(request, false);
    }

    //同步执行
    public EventResponse<Resp> sync(EventRequest<Req> request, boolean rollback) throws EventPipelineException {
        final EventContext<Req, Resp> eventContext = new DefaultEventContext<>();
        eventContext.addEventRequest(request);
        eventContext.set(targetPipeline);
        return eventExecutor.syncExecutor(eventContext);
    }

    private void checkAndSetExecutorService() {
        if (null == eventExecutor.get()) {
            eventExecutor.set(Executors.newSingleThreadExecutor(new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setName("Default-EventBoot-Thread");
                    return thread;
                }
            }));
        }
    }

    public EventBootstrap<Req, Resp> executor(ExecutorService executorService) {
        eventExecutor.set(executorService);
        return this;
    }


    public EventBootstrap<Req, Resp> add(EventHandler<Req, Resp> eventHandler) {
        targetPipeline.add(eventHandler);
        return this;
    }

    public EventBootstrap<Req, Resp> addFirst(EventHandler<Req, Resp> eventHandler) {
        targetPipeline.addFirst(eventHandler);
        return this;
    }

    public EventBootstrap<Req, Resp> addLast(EventHandler<Req, Resp> eventHandler) {
        targetPipeline.addLast(eventHandler);
        return this;
    }

    public EventBootstrap<Req, Resp> addFinal(EventHandler<Req, Resp> eventHandler) {
        targetPipeline.addFinal(eventHandler);
        return this;
    }
}
