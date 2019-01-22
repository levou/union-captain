package com.kxl.union.captain.component.exception;

public class ComponentBootException extends RuntimeException {
    public ComponentBootException() {
    }

    public ComponentBootException(String message) {
        super(message);
    }

    public ComponentBootException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComponentBootException(Throwable cause) {
        super(cause);
    }

    public ComponentBootException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
