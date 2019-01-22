package com.xishui.union.captain.examples.watcher.we;

import com.alibaba.fastjson.JSON;
import com.xishui.union.captain.common.wrapper.RunnableWrapper;
import com.xishui.union.captain.watcher.annotation.Watcher;
import com.xishui.union.captain.watcher.context.WatcherContext;

import java.util.List;

@Watcher
public class WatcherExample {

//    public void sayWatcher(String name){
////        WatcherContext.publishWatcher("我是方法体,我要写点信息");
////        WatcherContext.publishWatcher("ExampleModule","test Type","我是方法体,我要写点信息,包含了module和type");
//        System.out.println("WacherExample doing " + name);
//    }

    public void testMethod2(List<String> param){
        System.out.println("WacherExample doing " + JSON.toJSONString(param));
//        WatcherContext.publishWatcher("ExampleModule","test Type","我是测试的第二个方法");

        new Thread(RunnableWrapper.of(new Runnable() {
            @Override
            public void run() {
                WatcherContext.publishWatcher("异步哟","异步哈","这个是异步的消息");
            }
        })).start();
    }
}
