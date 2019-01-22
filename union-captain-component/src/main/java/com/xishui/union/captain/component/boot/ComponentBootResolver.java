package com.xishui.union.captain.component.boot;

import com.xishui.union.captain.common.logger.CaptainLogger;
import com.xishui.union.captain.component.AbstractComponent;
import com.xishui.union.captain.component.exception.ComponentBootException;
import com.xishui.union.captain.component.holder.InstanceHolders;

import java.util.Set;

public enum ComponentBootResolver {
    RESOLVER;

    public void boot() throws ComponentBootException {
        try {
            final Set<AbstractComponent> instances = InstanceHolders.HOLDERS.allInstances();
            if (null == instances || instances.size() <= 0) {
                CaptainLogger.info(this.getClass(), "Boot Start,Captain Component Instances is null.");
                return;
            }
            instances.forEach(instance -> {
                instance.prepare();
                instance.boot();
            });
        } catch (Exception e) {
            CaptainLogger.error(this.getClass(), "Boot Start Err." + e.getMessage());
            throw new ComponentBootException(e);
        }
    }
}
