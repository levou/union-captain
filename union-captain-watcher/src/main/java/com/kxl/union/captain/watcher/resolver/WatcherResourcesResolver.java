package com.kxl.union.captain.watcher.resolver;

import com.kxl.union.captain.common.ResourceLoader;
import com.kxl.union.captain.common.logger.CaptainLogger;
import com.kxl.union.captain.watcher.config.WatcherConfig;
import com.kxl.union.captain.watcher.exception.WatcherException;
import com.kxl.union.captain.watcher.holder.ClassLoaderHolder;
import com.kxl.union.captain.watcher.holder.WatcherHolder;
import com.kxl.union.captain.watcher.holder.WatcherKey;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum WatcherResourcesResolver {
    RESOLVER;

    public Set<URL> resourcesResolver() throws WatcherException {
        final Set<URL> url = new HashSet<>();
        try {
            final WatcherConfig watcherConfig = WatcherHolder.HOLDER.get(WatcherKey.WATCHER_CONFIG_KEY, WatcherConfig.class);
            if (null == watcherConfig) {
                throw new NullPointerException("Watcher Config is null");
            }
            ResourceLoader.LOADER.loader(url, watcherConfig.getScanPackages());
            if (url.size() <= 0) {
                throw new NullPointerException("Can't Load Watcher URL about scanPackages by:" + watcherConfig.getPackages());
            }
            //做一个类名和文件路径的映射处理
            final Map<String, String> classMaps = new HashMap<>();
            for (String pkg : watcherConfig.getScanPackages()) {
                url.forEach(u -> {
                    if (u.getPath().indexOf(pkg) >= 0) {
                        classMaps.put(u.getPath(), u.getPath().substring(u.getPath().indexOf(pkg)));
                    }
                });
            }
            ClassLoaderHolder.addAll(classMaps);
        } catch (Exception e) {
            throw new WatcherException(e);
        }
        return url;
    }
}
