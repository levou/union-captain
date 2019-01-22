package com.xishui.union.captain.common.collection;

public class NoSuchNodeException extends RuntimeException{
    public NoSuchNodeException() {
        super();
    }

    public NoSuchNodeException(String message) {
        super(message);
    }

    public NoSuchNodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchNodeException(Throwable cause) {
        super(cause);
    }

    protected NoSuchNodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
