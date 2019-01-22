package com.xishui.union.captain.component.resource;

import com.xishui.union.captain.common.ResourceLoader;
import com.xishui.union.captain.component.exception.ComponentDefineException;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public enum ComponentResourceResolver {
    RESOLVER;
    private static final String DEFAULT_COMPONENT_RESOURCES_FILE = "/META-INF/component-captain.def";
    private final Object lock = new Object();

    /**
     * 从工程以及jar包中查找定义的export component文件，并读取文件
     *
     * @return
     */
    public Set<URL> loadComponentDefines() throws ComponentDefineException {
        final Set<URL> urls = new HashSet<>();
        synchronized (lock) {
            try {
                ResourceLoader.LOADER.loader(urls,ComponentResourceResolver.DEFAULT_COMPONENT_RESOURCES_FILE);
            } catch (Exception e) {
                throw new ComponentDefineException(e);
            }
        }
        return urls;
    }
}
