package com.xishui.union.captain.watcher.config;

import com.xishui.union.captain.common.PropertiesLoader;
import com.xishui.union.captain.watcher.exception.WatcherException;
import com.xishui.union.captain.watcher.holder.WatcherHolder;
import com.xishui.union.captain.watcher.holder.WatcherKey;

import java.io.File;
import java.util.Properties;

public enum WatcherConfigResolver {
    RESOLVER;

    public void loadConfig(String path) throws WatcherException {
        if (!path.endsWith("/")) {
            path += "/";
        }
        final Properties properties = PropertiesLoader.LOADER.loader(path + WatcherConfig.WATCHER_CONFIG_FILE_NAME);
        if (null == properties) {
            throw new WatcherException("Can't load WatcherConfig from properties,path:" + path);
        }
        WatcherConfig watcherConfig = new WatcherConfig();
        if (properties.containsKey(WatcherConfigKey.PACKAGES)) {
            String packages = properties.getProperty(WatcherConfigKey.PACKAGES);
            if (null != packages) {
                String newPackages = packages.replaceAll("\\.", File.separator);
                watcherConfig.setPackages(packages);
                String[] pkgs = newPackages.split(",");
                for(int i =0;i < pkgs.length;i++){
                    if (!pkgs[i].startsWith(File.separator)) {
                        pkgs[i] = File.separator + pkgs[i];
                    }
                }
                watcherConfig.setScanPackages(pkgs);
            }
        }
        if (properties.containsKey(WatcherConfigKey.SWITCH)) {
            watcherConfig.setSwitch(Boolean.valueOf(properties.getProperty(WatcherConfigKey.SWITCH)));
        }
        WatcherHolder.HOLDER.add(WatcherKey.WATCHER_CONFIG_KEY, watcherConfig);
    }

    public interface WatcherConfigKey {
        String PACKAGES = "watcher.scan.packages";
        String SWITCH = "watcher.switch";
    }
}
