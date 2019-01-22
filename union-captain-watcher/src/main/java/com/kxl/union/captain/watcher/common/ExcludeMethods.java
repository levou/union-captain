package com.kxl.union.captain.watcher.common;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ExcludeMethods {
    private static final List<String> EXCLUDE_METHODS = new ArrayList<>();

    static {
        Class<?> clazz = Object.class.getClass();
        for (final Method method : clazz.getMethods()) {
            if (Modifier.isPublic(method.getModifiers())) {
                EXCLUDE_METHODS.add(method.getName());
            }
        }
    }

    public static boolean include(String methodName) {
        return EXCLUDE_METHODS.contains(methodName);
    }
}
