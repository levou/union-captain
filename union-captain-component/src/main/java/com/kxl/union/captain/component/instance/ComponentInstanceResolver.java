package com.kxl.union.captain.component.instance;

import com.kxl.union.captain.common.logger.CaptainLogger;
import com.kxl.union.captain.component.AbstractComponent;
import com.kxl.union.captain.component.Component;
import com.kxl.union.captain.component.ComponentConfig;
import com.kxl.union.captain.component.ComponentDefine;
import com.kxl.union.captain.component.exception.ComponentDefineException;
import com.kxl.union.captain.component.holder.DefineHolders;
import com.kxl.union.captain.component.holder.InstanceHolders;

import javax.management.InstanceNotFoundException;
import java.util.Set;

public enum ComponentInstanceResolver {
    RESOLVER;

    public void newInstances() {
        final Set<ComponentDefine> componentDefines = DefineHolders.HOLDERS.allDefines();
        if (null == componentDefines || componentDefines.size() <= 0) {
            CaptainLogger.info(this.getClass(), "Instance Resolver,Get Defines is null.");
            return;
        }
        componentDefines.forEach(componentDefine -> {
            try {
                InstanceHolders.HOLDERS.addInstance(newInstance(componentDefine));
            } catch (Exception e) {
                CaptainLogger.error(this.getClass(), "Resolver Instance " + componentDefine.getDefineClass() + " Err:" +
                        e.getMessage());
            }
        });
    }

    private AbstractComponent newInstance(ComponentDefine componentDefine) throws Exception {
        if (null == componentDefine || null == componentDefine.getDefineClass()) {
            throw new ComponentDefineException("Component Define is null.");
        }
        Class<?> cls = Class.forName(componentDefine.getDefineClass(), false, Component.class.getClassLoader());
        if (null == cls) {
            throw new NullPointerException(componentDefine.getDefineClass() + " Class.forName() return is null.");
        }
        Object object = cls.newInstance();
        if (object instanceof AbstractComponent) {
            return (AbstractComponent) object;
        }
        throw new InstanceNotFoundException(componentDefine.getDefineClass() + " Can't Instance");
    }
}
