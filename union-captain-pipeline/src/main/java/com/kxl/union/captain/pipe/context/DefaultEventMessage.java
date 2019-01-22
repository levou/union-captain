package com.kxl.union.captain.pipe.context;

import lombok.Data;

@Data
public class DefaultEventMessage<T> implements EventMessage<T> {
    private T message;
    private boolean isError = true;
    private Throwable throwable;

    public DefaultEventMessage() {
    }

    public DefaultEventMessage(T message) {
        this.message = message;
        this.isError = true;
    }

    public DefaultEventMessage(T message, boolean isError) {
        this.message = message;
        this.isError = isError;
    }

    public DefaultEventMessage(T message, boolean isError, Throwable throwable) {
        this.message = message;
        this.isError = isError;
        this.throwable = throwable;
    }

    public DefaultEventMessage(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public T message() {
        return this.message;
    }

    @Override
    public boolean isError() {
        return this.isError;
    }

    @Override
    public void message(T message) {
        this.message = message;
    }

    @Override
    public Throwable throwable() {
        return this.throwable;
    }

    @Override
    public void throwable(Throwable throwable) {
        this.throwable = throwable;
    }
}
