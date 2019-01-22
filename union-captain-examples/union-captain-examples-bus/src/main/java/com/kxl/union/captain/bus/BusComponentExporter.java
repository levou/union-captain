package com.kxl.union.captain.bus;

import com.kxl.union.captain.component.ComponentExporter;

import java.util.HashSet;
import java.util.Set;

public class BusComponentExporter implements ComponentExporter {
    @Override
    public Set<Class> export() {
        final Set<Class> exporters = new HashSet<>();
        exporters.add(TyreComponent.class);
        exporters.add(MotorComponent.class);
        return exporters;
    }
}
