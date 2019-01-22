package com.xishui.union.captain.test;

import com.xishui.union.captain.watcher.annotation.Watcher;

@Watcher
public class WatcherTestService {

    public void sayWatcher(String name) {
        System.out.println("sayWatcher doing");
    }
}
