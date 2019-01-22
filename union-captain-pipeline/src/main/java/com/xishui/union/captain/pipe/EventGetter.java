package com.xishui.union.captain.pipe;

import com.xishui.union.captain.pipe.exception.EventExecutorException;

public interface EventGetter<T>{

    T get() throws EventExecutorException;
}
