package com.xishui.union.captain.watcher.config;

import lombok.Data;

@Data
public class WatcherConfig {

    public static final String WATCHER_CONFIG_FILE_NAME = "watcher-config.properties";
    /**
     * 需要扫描的包列表,配置以逗号隔开
     **/
    private String packages;

    private String[] scanPackages;

    private boolean isSwitch = false;

}
