package com.kxl.union.captain.pipe.exception;

public class EventIllegalException extends EventPipelineException {
    public EventIllegalException() {
        super();
    }

    public EventIllegalException(String message) {
        super(message);
    }

    public EventIllegalException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventIllegalException(Throwable cause) {
        super(cause);
    }

    protected EventIllegalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
