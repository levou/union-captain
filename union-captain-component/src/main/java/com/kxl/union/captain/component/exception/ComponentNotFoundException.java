package com.kxl.union.captain.component.exception;

public class ComponentNotFoundException extends RuntimeException{
    public ComponentNotFoundException() {
        super();
    }

    public ComponentNotFoundException(String message) {
        super(message);
    }

    public ComponentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComponentNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ComponentNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
