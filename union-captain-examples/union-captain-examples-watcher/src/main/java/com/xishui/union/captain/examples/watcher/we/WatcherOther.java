package com.xishui.union.captain.examples.watcher.we;

import com.xishui.union.captain.watcher.annotation.Watcher;

public class WatcherOther {
    @Watcher(name = "Method-2")
    public String valueReturn(Integer in){
        System.out.println("Class 2 to method:"+in);
        return "我是类2测试";

    }
    @Watcher(name = "Method-2",active = false)
    public String valueReturn2(Integer in){
        System.out.println("Class 2 to method:"+in);
        return "我是类3测试";

    }
}
