package com.xishui.union.captain.common.pubsub;

public abstract class AbstractPublisher<T> implements Publisher<T> {

    @Override
    public final void publish(T param) {
        if (!PSFactory.<T>factory().isStarted()) {
            PSFactory.<T>factory().init();
        }
        if (processorParam(param)) {

            PSFactory.<T>factory().pub(param);
        }
    }

    //可以对发布对消息做一些处理，或者做一些判断，返回为true才会发布出去
    protected abstract boolean processorParam(T param);

}
