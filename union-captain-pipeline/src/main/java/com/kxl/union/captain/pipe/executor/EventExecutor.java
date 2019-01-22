package com.kxl.union.captain.pipe.executor;

import com.kxl.union.captain.pipe.EventSetter;
import com.kxl.union.captain.pipe.EventGetter;
import com.kxl.union.captain.pipe.Executor;

import java.util.concurrent.ExecutorService;

/**
 * 事件处理器
 */
public interface EventExecutor<Req, Resp> extends Executor<Req, Resp>, EventSetter<ExecutorService>,EventGetter<ExecutorService> {

}
