package com.xishui.union.captain.test.thread;

public class ChildRunnable implements Runnable {
    private ValueBean valueBean;

    public ChildRunnable(ValueBean value) {
        this.valueBean = value;
    }

    @Override
    public void run() {
        System.out.println(valueBean.getValue());
    }
}
