package com.kxl.union.captain.common.logger;

/**
 * 临时用一下，需要适配日志组件
 */
public final class CaptainLogger {

    public static void error(Class<?> cls, String errMessage) {
        LoggerHolder.HOLDER.loggerWrapper().error(cls, errMessage);
    }

    public static void error(Class<?> cls, Throwable throwable) {
        LoggerHolder.HOLDER.loggerWrapper().error(cls, throwable);
    }

    public static void info(Class<?> cls, String infoMessage) {
        LoggerHolder.HOLDER.loggerWrapper().info(cls, infoMessage);
    }
}
