package com.xishui.union.captain.pipe;

import lombok.Data;

@Data
public class Request {
    private String step;

    public Request(String step) {
        this.step = step;
    }
}
