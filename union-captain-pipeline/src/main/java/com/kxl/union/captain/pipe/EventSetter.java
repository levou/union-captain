package com.kxl.union.captain.pipe;

import com.kxl.union.captain.pipe.exception.EventPipelineException;

public interface EventSetter<T> {

    void set(T t) throws EventPipelineException;
}
