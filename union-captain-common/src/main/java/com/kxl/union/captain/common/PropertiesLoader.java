package com.kxl.union.captain.common;

import com.kxl.union.captain.common.logger.CaptainLogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public enum PropertiesLoader {

    LOADER;

    public synchronized Properties loader(String filePath) throws NullPointerException {
        File file = new File(filePath);
        if (!file.exists() || file.isDirectory()) {
            throw new NullPointerException("Properties loader file is null. path:" + filePath);
        }
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            Properties properties = new Properties();
            properties.load(bufferedReader);
            return properties;
        } catch (Exception e) {
            CaptainLogger.error(this.getClass(), "Properties loader Err:" + e.getMessage());
            return null;
        }
    }
}
