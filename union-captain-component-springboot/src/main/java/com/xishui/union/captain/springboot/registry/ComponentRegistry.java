package com.xishui.union.captain.springboot.registry;

import com.xishui.union.captain.common.logger.LoggerHolder;
import com.xishui.union.captain.component.ComponentBoot;
import com.xishui.union.captain.springboot.logger.Slf4jLoggerWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ComponentRegistry implements BeanDefinitionRegistryPostProcessor {
    private final Logger logger = LoggerFactory.getLogger(ComponentRegistry.class);

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        try {
            LoggerHolder.HOLDER.initLogger(new Slf4jLoggerWrapper());
            ComponentBoot.BOOT.starter();
            final Set<Class> beanDefines = ComponentBoot.BOOT.beanDefines();
            if (null == beanDefines || beanDefines.size() <= 0) {
                logger.warn("Can't loaded Component Defines");
                return;
            }
            final StringBuilder sb = new StringBuilder();
            sb.append("\n");
            beanDefines.forEach(define -> {
                BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(define);
                GenericBeanDefinition genericBeanDefinition = (GenericBeanDefinition) definitionBuilder.getRawBeanDefinition();
                genericBeanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
                genericBeanDefinition.setBeanClassName(define.getName());
                beanDefinitionRegistry.registerBeanDefinition(define.getName(), genericBeanDefinition);
                sb.append("**********| Add Component to Spring : " + genericBeanDefinition.getBeanClassName()).append("\n");
            });
            logger.info("Components Success to Spring:" + sb.toString());
        } catch (Exception e) {
            logger.error("Spring Bean Define Registry By Component Err.", e);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    }
}
