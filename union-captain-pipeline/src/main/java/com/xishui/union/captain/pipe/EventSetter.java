package com.xishui.union.captain.pipe;

import com.xishui.union.captain.pipe.exception.EventPipelineException;

public interface EventSetter<T> {

    void set(T t) throws EventPipelineException;
}
