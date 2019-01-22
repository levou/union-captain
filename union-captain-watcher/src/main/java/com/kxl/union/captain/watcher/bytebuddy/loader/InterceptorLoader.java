package com.kxl.union.captain.watcher.bytebuddy.loader;

import com.kxl.union.captain.common.logger.CaptainLogger;
import com.kxl.union.captain.watcher.bytebuddy.intercept.WatcherAroundHook;

import java.util.HashMap;
import java.util.Map;

public enum InterceptorLoader {
    LOADER;

    private static final Map<String, WatcherAroundHook> INTERCEPTOR = new HashMap<>();

    public synchronized WatcherAroundHook get(Class<? extends WatcherAroundHook> hookClass) {
        if (!INTERCEPTOR.containsKey(hookClass.getName())) {
            try {
                Object obj = hookClass.newInstance();
                if (obj instanceof WatcherAroundHook) {
                    final WatcherAroundHook watcherAroundHook = (WatcherAroundHook) obj;
                    INTERCEPTOR.put(hookClass.getName(), watcherAroundHook);
                }else {
                    return null;
                }
            } catch (Exception e) {
                CaptainLogger.error(this.getClass(), "Get WatcherAroundHook err." + e.getMessage());
                return null;
            }
        }
        return INTERCEPTOR.get(hookClass.getName());
    }
}
