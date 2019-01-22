package com.xishui.union.captain.watcher.bytebuddy.matcher;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

public interface Matcher {
    /**
     * 构建匹配器
     */
    ElementMatcher.Junction<?> buildMatcher();

    /**
     * 是否匹配
     */
    boolean isMatcher(TypeDescription typeDefinitions);

}
