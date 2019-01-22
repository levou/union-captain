package com.xishui.union.captain.test.thread;

public class ValueHolder {


    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public void add(String value) {
        threadLocal.set(value);
    }

    public String value() {
        return threadLocal.get();
    }
}
