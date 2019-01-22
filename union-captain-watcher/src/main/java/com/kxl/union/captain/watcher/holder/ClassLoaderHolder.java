package com.kxl.union.captain.watcher.holder;

import java.util.HashMap;
import java.util.Map;

public final class ClassLoaderHolder {

    private static final Map<String, String> CLASS_MAP = new HashMap<>();

    public static void add(String key, String value) {
        CLASS_MAP.put(key, value);
    }

    public static void addAll(Map<String, String> classMap) {
        CLASS_MAP.putAll(classMap);
    }

    public static String get(String key) {
        return CLASS_MAP.get(key);
    }
}
