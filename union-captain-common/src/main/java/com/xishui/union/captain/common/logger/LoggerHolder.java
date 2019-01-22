package com.xishui.union.captain.common.logger;

import java.util.HashSet;
import java.util.Set;

public enum LoggerHolder {
    HOLDER;

    private static final Set<CaptainLoggerWrapper> LOGGER_WRAPPER_MAP = new HashSet<>();

    public void initLogger(CaptainLoggerWrapper captainLoggerWrapper) {
        if (!LOGGER_WRAPPER_MAP.isEmpty()) {
            LOGGER_WRAPPER_MAP.clear();
        }
        LOGGER_WRAPPER_MAP.add(captainLoggerWrapper);
    }

    public CaptainLoggerWrapper loggerWrapper() {
        if (LOGGER_WRAPPER_MAP.isEmpty()) {
            LOGGER_WRAPPER_MAP.add(new DefaultCaptainLoggerWrapper());
        }
        return LOGGER_WRAPPER_MAP.iterator().next();
    }
}
