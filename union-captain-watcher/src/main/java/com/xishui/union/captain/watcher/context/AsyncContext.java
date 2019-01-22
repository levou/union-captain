package com.xishui.union.captain.watcher.context;

import lombok.Data;

@Data
public class AsyncContext {
    private String watcherId;
    private int sequence;

    public static AsyncContext async(){
        return new AsyncContext();
    }
    public AsyncContext watcherId(String watcherId){
        this.watcherId = watcherId;
        return this;
    }
    public AsyncContext sequence(int sequence){
        this.sequence = sequence;
        return this;
    }
}
