package com.kxl.union.captain.watcher.bytebuddy.define;

import com.kxl.union.captain.watcher.bytebuddy.matcher.Matcher;
import com.kxl.union.captain.watcher.exception.WatcherException;
import com.kxl.union.captain.watcher.model.WatcherDefinition;
import net.bytebuddy.dynamic.DynamicType;

public interface WatcherDefine {
    /**
     * 做字节码重构
     */
    DynamicType.Builder<?> reDefine(String originalClassName, DynamicType.Builder<?> newBuilder, ClassLoader classLoader) throws WatcherException;

    /**
     * 做类匹配（需要被代理的类）
     */
    Matcher matcher();



    static DefaultWatcherDefine newDefine(final WatcherDefinition watcherDefinition) {
        return new DefaultWatcherDefine(watcherDefinition);
    }

}
