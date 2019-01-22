package com.xishui.union.captain.common.pubsub;

import lombok.Data;

import java.util.concurrent.ExecutorService;
@Data
public class PSConfig {

    private int queueSize = 1024;

    private ExecutorService executorService;
}
