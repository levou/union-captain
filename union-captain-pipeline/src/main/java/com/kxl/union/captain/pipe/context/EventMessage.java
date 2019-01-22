package com.kxl.union.captain.pipe.context;

/**
 * 事件消息,在context中
 */
public interface EventMessage<M> {

    M message();

    void message(M message);

    boolean isError();

    Throwable throwable();

    void throwable(Throwable throwable);

    static <M> EventMessage<M> newMessage() {
        return new DefaultEventMessage();
    }
    static <M> EventMessage<M> newMessage(M message) {
        return new DefaultEventMessage(message);
    }

    static <M> EventMessage<M> newMessage(M message, boolean isError) {
        return new DefaultEventMessage(message, isError);
    }
}
