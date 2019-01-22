package com.xishui.union.captain.common.logger;

public interface CaptainLoggerWrapper {

    void error(Class<?> cls, String errMessage);

    void error(Class<?> cls, Throwable throwable);

    void info(Class<?> cls, String infoMessage);
}
