package com.kxl.union.captain.pipe;

import lombok.Data;

@Data
public class Response {

    private String value;

    public Response(String value) {
        this.value = value;
    }
}
