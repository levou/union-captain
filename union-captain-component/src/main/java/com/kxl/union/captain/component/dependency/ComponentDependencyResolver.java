package com.kxl.union.captain.component.dependency;

import com.kxl.union.captain.common.logger.CaptainLogger;
import com.kxl.union.captain.component.AbstractComponent;
import com.kxl.union.captain.component.ComponentConfig;
import com.kxl.union.captain.component.exception.ComponentDefineException;
import com.kxl.union.captain.component.holder.DefineHolders;
import com.kxl.union.captain.component.holder.InstanceHolders;

import java.util.Set;

/**
 * 依赖检查
 */
public enum ComponentDependencyResolver {
    RESOLVER;

    public void checkDependency() throws ComponentDefineException {
        final Set<AbstractComponent> instances = InstanceHolders.HOLDERS.allInstances();
        if (null == instances || instances.size() <= 0) {
            CaptainLogger.info(this.getClass(), "Component Dependency Resolver,Get Instances is null.");
            return;
        }
        instances.forEach(instance -> {
            final Set<String> dependencies = instance.dependencyComponentNames();
            if (null != dependencies && dependencies.size() > 0) {
                //在define中有，同时在instances中也有
                dependencies.forEach(dependencyName -> {
                    if (!DefineHolders.HOLDERS.checkExistDefineByClassName(dependencyName)
                            || !InstanceHolders.HOLDERS.checkExistByInstanceKey(dependencyName)) {
                        throw new ComponentDefineException("Instance " + instance.getClass().getName() + " dependency "
                                + dependencyName + " Not Be Define or Instance.");
                    }
                });
            }
        });
    }
}
