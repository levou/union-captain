package com.xishui.union.captain.component.exception;

public class ComponentDefineException extends ComponentBootException{
    public ComponentDefineException() {
        super();
    }

    public ComponentDefineException(String message) {
        super(message);
    }

    public ComponentDefineException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComponentDefineException(Throwable cause) {
        super(cause);
    }

    protected ComponentDefineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
