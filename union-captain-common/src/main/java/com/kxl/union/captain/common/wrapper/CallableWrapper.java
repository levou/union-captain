package com.kxl.union.captain.common.wrapper;

import java.util.concurrent.Callable;

@CrossThread
public class CallableWrapper<V> implements Callable<V> {

    private final Callable<V> wrapper;

    public CallableWrapper(Callable<V> wrapper) {
        this.wrapper = wrapper;
    }

    public static <V> CallableWrapper<V> of(Callable<V> callable) {
        return new CallableWrapper<>(callable);
    }

    @Override
    public V call() throws Exception {
        return wrapper.call();
    }
}
