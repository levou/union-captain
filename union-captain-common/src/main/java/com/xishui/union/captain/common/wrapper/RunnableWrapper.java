package com.xishui.union.captain.common.wrapper;

@CrossThread
public class RunnableWrapper implements Runnable {

    private final Runnable wrapper;

    public RunnableWrapper(Runnable wrapper) {
        this.wrapper = wrapper;
    }

    public static RunnableWrapper of(final Runnable runnable) {
        return new RunnableWrapper(runnable);
    }

    @Override
    public void run() {
        wrapper.run();
    }
}
