package com.kxl.union.captain.test.thread;

public class AsyncTest {

    public static void main(String... args) {
        final ValueHolder valueHolder = new ValueHolder();
        valueHolder.add("parent value");
        ValueBean valueBean = new ValueBean();
        valueBean.setValue(valueHolder.value());
        System.out.println("main:"+valueHolder.value());
        new Thread(new ChildRunnable(valueBean)).start();
        try{
            Thread.sleep(2000L);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
