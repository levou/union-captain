package com.xishui.union.captain.pipe;

import com.alibaba.fastjson.JSON;
import com.xishui.union.captain.pipe.async.TestFutureCallback;
import com.xishui.union.captain.pipe.context.EventRequest;
import com.xishui.union.captain.pipe.handler.*;

import java.util.concurrent.Executors;

public class PipeEvent {

    public void testSyncPipeEvent() {
        Response eventResponse = EventBootstrap.<Request, Response>boot()
                .add(new WakeUpHandler())
                .add(new LunchHandler())
                .add(new WorkHandler())
                .add(new SleepHandler())
                .addFinal(new FinalHandler())
                .sync(EventRequest.<Request>newRequest().addRequest(new Request("测试")))
                .response();
        System.out.println(JSON.toJSONString(eventResponse));
    }
    public void testAsyncPipeEvent() {
        EventBootstrap.<Request, Response>boot()
                .add(new WakeUpHandler())
                .add(new LunchHandler())
                .add(new WorkHandler())
                .add(new SleepHandler())
                .addFinal(new FinalHandler())
                .executor(Executors.newFixedThreadPool(3))
                .async(EventRequest.<Request>newRequest().addRequest(new Request("测试")),new TestFutureCallback());
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
