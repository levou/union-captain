package com.xishui.union.captain.watcher.bytebuddy.matcher;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

public class ClazzMatcher implements Matcher {
    private String clazz;

    public ClazzMatcher(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public ElementMatcher.Junction<?> buildMatcher() {
        return ElementMatchers.named(clazz);
    }

    @Override
    public boolean isMatcher(TypeDescription typeDefinitions) {
        return typeDefinitions.getName().equals(clazz);
    }

    public static ClazzMatcher of(String clazz) {
        return new ClazzMatcher(clazz);
    }
}
