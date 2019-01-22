package com.xishui.union.captain.component;

import com.xishui.union.captain.component.exception.ComponentBootException;

import java.util.Set;

/**
 * 组件定义类
 */
public interface Component {
    /**
     * 组件依赖来哪些组件,这个需要优先加载并处理依赖的组件
     */
    Set<String> dependencyComponentNames() throws ComponentBootException;

    /**
     * 组件的准备阶段
     */
    void prepare() throws ComponentBootException;

    /**
     * 组件的初始化/实例阶段
     */
    void boot() throws ComponentBootException;

    /**
     * 组件的销毁阶段
     */
    void shutDown() throws ComponentBootException;
}
