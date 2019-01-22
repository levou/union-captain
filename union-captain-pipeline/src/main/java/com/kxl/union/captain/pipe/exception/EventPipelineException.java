package com.kxl.union.captain.pipe.exception;

public class EventPipelineException extends RuntimeException {
    public EventPipelineException() {
        super();
    }

    public EventPipelineException(String message) {
        super(message);
    }

    public EventPipelineException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventPipelineException(Throwable cause) {
        super(cause);
    }

    protected EventPipelineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
