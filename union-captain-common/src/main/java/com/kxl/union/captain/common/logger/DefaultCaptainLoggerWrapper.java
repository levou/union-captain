package com.kxl.union.captain.common.logger;

public class DefaultCaptainLoggerWrapper implements CaptainLoggerWrapper {
    @Override
    public  void error(Class<?> cls, String errMessage) {
        System.err.println(cls.getName() + " [ERROR] " + errMessage);
    }
    @Override
    public  void error(Class<?> cls, Throwable throwable) {
        System.err.println(cls.getName() + " [ERROR] " + throwable.getMessage());
    }
    @Override
    public  void info(Class<?> cls, String infoMessage) {
        System.out.println(cls.getName() + " [INFO] " + infoMessage);
    }

}
