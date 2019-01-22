package com.kxl.union.captain.test;

import com.kxl.union.captain.watcher.annotation.Watcher;

@Watcher
public class WatcherTestService {

    public void sayWatcher(String name) {
        System.out.println("sayWatcher doing");
    }
}
