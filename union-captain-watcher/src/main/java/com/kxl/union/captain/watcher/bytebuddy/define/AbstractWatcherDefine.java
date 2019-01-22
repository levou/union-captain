package com.kxl.union.captain.watcher.bytebuddy.define;


import com.kxl.union.captain.watcher.bytebuddy.AsyncEnhance;
import com.kxl.union.captain.watcher.bytebuddy.intercept.AsyncConstructorInterceptor;
import com.kxl.union.captain.watcher.bytebuddy.intercept.WatcherAroundHook;
import com.kxl.union.captain.watcher.bytebuddy.intercept.WatcherInterceptor;
import com.kxl.union.captain.watcher.context.AsyncContext;
import com.kxl.union.captain.watcher.exception.WatcherException;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

public abstract class AbstractWatcherDefine implements WatcherDefine {
    private static final String INNER_ASYNC_FIELD_NAME = "asyncContext";

    @Override
    public DynamicType.Builder<?> reDefine(String originalClassName, DynamicType.Builder<?> newBuilder, ClassLoader classLoader) throws WatcherException {
        if (isAsync()) {
            //增加了字段,添加了一个接口实现
            newBuilder = newBuilder.defineField(INNER_ASYNC_FIELD_NAME, AsyncContext.class,
                    Opcodes.ACC_PRIVATE | Opcodes.ACC_VOLATILE)
                    .implement(AsyncEnhance.class).intercept(FieldAccessor.ofField(INNER_ASYNC_FIELD_NAME));
            //增加构造方法实现,构造方法中
            newBuilder = newBuilder.constructor(constructorMatcher()).intercept(SuperMethodCall.INSTANCE
                    .andThen(MethodDelegation.withDefaultConfiguration().to(new AsyncConstructorInterceptor())));

        }
        newBuilder = newBuilder.method(ElementMatchers.any().and(methodMatcher()))
                .intercept(MethodDelegation.withDefaultConfiguration().to(new WatcherInterceptor(interceptor())));

        return newBuilder;
    }

    /**
     * 类下面有多少方法是需要被redefine的
     *
     * @return
     */
    protected abstract ElementMatcher<MethodDescription> methodMatcher();

    /**
     * 代理执行类
     */
    protected abstract Class<? extends WatcherAroundHook> interceptor();

    //need override
    protected boolean isAsync() {
        return false;
    }

    //need override
    protected ElementMatcher<MethodDescription> constructorMatcher() {
        return ElementMatchers.none();
    }
}
