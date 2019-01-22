package com.xishui.union.captain.pipe.async;

import com.alibaba.fastjson.JSON;
import com.xishui.union.captain.pipe.EventFutureCallback;
import com.xishui.union.captain.pipe.Response;
import com.xishui.union.captain.pipe.context.EventResponse;

import javax.annotation.Nullable;

public class TestFutureCallback implements EventFutureCallback<EventResponse<Response>> {
    @Override
    public void onSuccess(@Nullable EventResponse<Response> responseEventResponse) {
        System.out.println(JSON.toJSONString(responseEventResponse.response()));
    }

    @Override
    public void onFailure(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }
}
