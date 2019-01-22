package com.xishui.union.captain.springboot.logger;

import com.xishui.union.captain.common.logger.CaptainLoggerWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLoggerWrapper implements CaptainLoggerWrapper {
    private final Logger logger = LoggerFactory.getLogger(Slf4jLoggerWrapper.class);

    @Override
    public void error(Class<?> cls, String errMessage) {
        logger.error(cls.getName() + " " + errMessage);
    }

    @Override
    public void error(Class<?> cls, Throwable throwable) {
        logger.error(cls.getName() + " " + throwable.getMessage());
    }

    @Override
    public void info(Class<?> cls, String infoMessage) {
        logger.info(cls.getName() + " " + infoMessage);
    }
}
