package com.kxl.union.captain.pipe;

import com.kxl.union.captain.pipe.exception.EventExecutorException;

public interface EventGetter<T>{

    T get() throws EventExecutorException;
}
