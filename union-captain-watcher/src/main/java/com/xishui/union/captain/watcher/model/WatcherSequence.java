package com.xishui.union.captain.watcher.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 步骤计数器
 */
public class WatcherSequence {
    private final AtomicInteger sequence = new AtomicInteger(0);


    public static WatcherSequence createSequence() {
        return new WatcherSequence();
    }

    public static WatcherSequence continueSequence(int seq) {
        WatcherSequence watcherSequence = new WatcherSequence();
        watcherSequence.sequence.set(seq);
        return watcherSequence;
    }

    public Integer nextSequence() {
        return sequence.incrementAndGet();
    }

    public Integer currentSequence() {
        return sequence.get();
    }
}
