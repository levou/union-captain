package com.xishui.union.captain.pipe.pipeline;

import com.xishui.union.captain.pipe.exception.EventPipelineException;
import com.xishui.union.captain.pipe.handler.EventHandler;

/**
 * 事件管道
 * 负责事件的添加
 * 1.顺序添加
 * 2.头/尾添加
 */
public interface EventPipeline<Req, Resp> {

    EventPipeline<Req, Resp> add(EventHandler<Req, Resp> eventHandler);

    EventPipeline<Req, Resp> addFirst(EventHandler<Req, Resp> eventHandler);

    EventPipeline<Req, Resp> addLast(EventHandler<Req, Resp> eventHandler);


    EventHandler<Req, Resp> next() throws EventPipelineException;

    EventHandler<Req, Resp> pre() throws EventPipelineException;

}
