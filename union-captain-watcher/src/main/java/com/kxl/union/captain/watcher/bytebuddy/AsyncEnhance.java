package com.kxl.union.captain.watcher.bytebuddy;

import com.kxl.union.captain.watcher.context.AsyncContext;

public interface AsyncEnhance {

    void setAsyncContext(AsyncContext asyncContext);

    AsyncContext getAsyncContext();
}
