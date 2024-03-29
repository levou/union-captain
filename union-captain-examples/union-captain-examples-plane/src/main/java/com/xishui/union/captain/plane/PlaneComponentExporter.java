package com.xishui.union.captain.plane;

import com.xishui.union.captain.component.ComponentExporter;

import java.util.HashSet;
import java.util.Set;

public class PlaneComponentExporter implements ComponentExporter {
    @Override
    public Set<Class> export() {
        final Set<Class> exporters = new HashSet<>();
        exporters.add(CabinComponent.class);
        exporters.add(ChairComponent.class);
        return exporters;
    }
}
