package com.kxl.union.captain.component;

import java.util.Set;

/**
 * 需要被暴露的有哪些服务
 */
public interface ComponentExporter {

    Set<Class> export();
}
