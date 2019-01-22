package com.xishui.union.captain.watcher.log.holder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum  LoggerHolder {
    HOLDER;

    private final Map<String,String> LOGGER_HOLDER = new ConcurrentHashMap<>(5);

    public void add(String key,String value){
        LOGGER_HOLDER.put(key,value);
    }
    public String get(String key){
        return LOGGER_HOLDER.get(key);
    }
}
