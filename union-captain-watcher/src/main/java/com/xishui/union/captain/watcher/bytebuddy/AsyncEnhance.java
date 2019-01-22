package com.xishui.union.captain.watcher.bytebuddy;

import com.xishui.union.captain.watcher.context.AsyncContext;

public interface AsyncEnhance {

    void setAsyncContext(AsyncContext asyncContext);

    AsyncContext getAsyncContext();
}
