package com.kxl.union.captain.examples.watcher;

import com.kxl.union.captain.examples.watcher.we.WatcherExample;
import com.kxl.union.captain.examples.watcher.we.WatcherOther;
import com.kxl.union.captain.watcher.log.LoggerAutoConfigurationWatcher;
import com.kxl.union.captain.watcher.log.data.LoggerWatcherRecordAnalysis;

import java.util.Arrays;

public class WatcherMain {

    public static void main(String... args) {
        LoggerAutoConfigurationWatcher.LOGGER.autoInit("/Users/xishui/work/data/logs/watcher/");
//        new WatcherExample().sayWatcher("main thread");
        new WatcherExample().testMethod2(Arrays.asList(new String[]{"abc","dbc"}));
        WatcherOther other = new WatcherOther();
//        other.valueReturn(2);
//        other.valueReturn2(3);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                new WatcherExample().sayWatcher("Thread 1");
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                new WatcherExample().sayWatcher("Thread 2");
//            }
//        }).start();
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        LoggerWatcherRecordAnalysis.ANALYSIS.analysis();
    }
}
