package com.kxl.union.captain.watcher.resolver;

import com.kxl.union.captain.common.wrapper.RunnableWrapper;
import com.kxl.union.captain.watcher.bytebuddy.define.WatcherDefine;
import com.kxl.union.captain.watcher.exception.WatcherException;
import com.kxl.union.captain.watcher.holder.WatcherHolder;
import com.kxl.union.captain.watcher.holder.WatcherKey;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.*;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;
import java.util.Set;

public enum WatcherByteResolver {
    RESOLVER;

    public void byteResolver(Instrumentation instrumentation) throws WatcherException {

        final Set<WatcherDefine> watcherDefines = (Set<WatcherDefine>) WatcherHolder.HOLDER.getSingleValue(WatcherKey.WATCHER_DEFINE_KEY);
        if (null == watcherDefines || watcherDefines.size() <= 0) {
            throw new WatcherException("Watcher ByteResolver . defines is null.");
        }
        new AgentBuilder.Default()
//                .ignore(ElementMatchers.nameStartsWith("org.slf4j."))
//                .ignore(ElementMatchers.nameStartsWith("ch.qos.logback"))
//                .ignore(ElementMatchers.nameStartsWith("com.alibaba.fastjson"))
                .type(buildMatcher(watcherDefines))
                .transform(new DefaultWatcherByteTransformer(watcherDefines))
                .with(new DefaultWatcherByteBuddyListener())
                .installOn(instrumentation);
    }

    private ElementMatcher<? super TypeDescription> buildMatcher(final Set<WatcherDefine> watcherDefines) {
        //可以提高效率
        final ElementMatcher.Junction junction = ElementMatchers.any();
        watcherDefines.forEach(m -> junction.or(m.matcher().buildMatcher()));
        return junction;
    }

    public class DefaultWatcherByteTransformer implements AgentBuilder.Transformer {
        private final Set<WatcherDefine> watcherDefines;

        public DefaultWatcherByteTransformer(final Set<WatcherDefine> watcherDefines) {
            this.watcherDefines = watcherDefines;
        }

        @Override
        public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription,
                                                ClassLoader classLoader, JavaModule javaModule) {
            DynamicType.Builder<?> newBuilder = builder;
            System.out.println(typeDescription.getTypeName());
            for (final WatcherDefine watcherDefine : watcherDefines) {
                if (watcherDefine.matcher().isMatcher(typeDescription)) {
                    newBuilder = watcherDefine.reDefine(typeDescription.getTypeName(), newBuilder, classLoader);
                }
            }
            return newBuilder;
        }
    }

    public class DefaultWatcherByteBuddyListener implements AgentBuilder.Listener {
        @Override
        public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

        }

        @Override
        public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {
        }

        @Override
        public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b) {

        }

        @Override
        public void onError(String s, ClassLoader classLoader, JavaModule javaModule, boolean b, Throwable throwable) {

        }

        @Override
        public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

        }
    }
}
