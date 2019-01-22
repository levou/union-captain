package com.xishui.union.captain.watcher;

import com.xishui.union.captain.common.logger.CaptainLogger;
import com.xishui.union.captain.watcher.config.WatcherConfigResolver;
import com.xishui.union.captain.watcher.exception.WatcherException;
import com.xishui.union.captain.watcher.resolver.WatcherByteResolver;
import com.xishui.union.captain.watcher.resolver.WatcherDefineResolver;
import com.xishui.union.captain.watcher.resolver.WatcherResourcesResolver;

import java.lang.instrument.Instrumentation;
import java.net.URL;
import java.util.Set;

public class WatcherAgent {
    /**
     * agent的启动类
     */
    public static void premain(String args, Instrumentation instrumentation) {
        /**
         * 1.扫描加载需要被增强的类-方法 集合（被注解的）
         * 2.byte buddy 字节码增强
         * 3.启动 pub/sub模型，
         * 4.记录相关信息
         * 5.提供API 做数据分析
         */
        try {
            System.out.println("pre main doing");
            if (null == args) {
                throw new WatcherException("JavaAgent Param is null.must set Watcher config path(dir)");
            }
            WatcherConfigResolver.RESOLVER.loadConfig(args);
            final Set<URL> urls = WatcherResourcesResolver.RESOLVER.resourcesResolver();
            WatcherDefineResolver.RESOLVER.defineResolver(urls);
            WatcherByteResolver.RESOLVER.byteResolver(instrumentation);
        } catch (Throwable e) {
            CaptainLogger.error(WatcherAgent.class, e);
        }

    }
}
