package com.kxl.union.captain.component;

import com.kxl.union.captain.common.logger.CaptainLogger;
import com.kxl.union.captain.component.boot.ComponentBootResolver;
import com.kxl.union.captain.component.dependency.ComponentDependencyResolver;
import com.kxl.union.captain.component.exception.ComponentBootException;
import com.kxl.union.captain.component.exception.ComponentNotFoundException;
import com.kxl.union.captain.component.holder.ExporterHolders;
import com.kxl.union.captain.component.instance.ComponentInstanceResolver;
import com.kxl.union.captain.component.resource.ComponentResourceResolver;
import com.kxl.union.captain.component.transfer.ComponentDefineTransfer;

import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public enum ComponentBoot {
    BOOT;
    private static final AtomicBoolean INIT = new AtomicBoolean(false);

    public void starter(boolean initBean) throws Exception {

    }

    /**
     * 启动类
     */
    public synchronized void starter() throws Exception {
        if (ComponentBoot.INIT.get()) {
            return;
        }
        try {
            final Set<URL> urls = ComponentResourceResolver.RESOLVER.loadComponentDefines();
            ComponentDefineTransfer.TRANSFER.transfer(urls);
            ComponentInstanceResolver.RESOLVER.newInstances();
            ComponentDependencyResolver.RESOLVER.checkDependency();
            ComponentBootResolver.RESOLVER.boot();
            ComponentBoot.INIT.set(true);
        } catch (ComponentBootException e) {
            CaptainLogger.error(this.getClass(), "ComponentBoot Start Err." + e.getMessage());
            throw e;
        }
    }

    public <T> T getComponent(Class<T> componentClass) throws ComponentNotFoundException {
        Object value = ExporterHolders.HOLDERS.getExporter(componentClass.getName());
        if (null == value) {
            throw new ComponentNotFoundException("Can't Found Exporter Component :" + componentClass.getName());
        }
        return (T) value;
    }

    public Set<Class> beanDefines() throws Exception {

        return ExporterHolders.HOLDERS.getAllExporter();
    }
}
