package com.kxl.union.captain.common.pubsub;

public interface Publisher<T> {

    void publish(T param);
}
