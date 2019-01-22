package com.kxl.union.captain.watcher.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 1.如果修饰在类上面,则类中所有public 非 static方法均被增强,可以设置某个public方法的active为false，不做增强
 * 2.如果修饰在方法上,则该方法可被增强
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Watcher {

    String name() default "";

    boolean active() default true;
}
