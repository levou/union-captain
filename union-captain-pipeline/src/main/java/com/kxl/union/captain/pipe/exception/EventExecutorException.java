package com.kxl.union.captain.pipe.exception;

public class EventExecutorException extends EventPipelineException{
    public EventExecutorException() {
        super();
    }

    public EventExecutorException(String message) {
        super(message);
    }

    public EventExecutorException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventExecutorException(Throwable cause) {
        super(cause);
    }

    protected EventExecutorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
