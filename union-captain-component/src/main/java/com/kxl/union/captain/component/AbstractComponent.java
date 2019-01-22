package com.kxl.union.captain.component;

import com.kxl.union.captain.common.logger.CaptainLogger;
import com.kxl.union.captain.component.exception.ComponentBootException;
import com.kxl.union.captain.component.holder.ExporterHolders;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractComponent implements Component {
    @Override
    public Set<String> dependencyComponentNames() throws ComponentBootException {
        return new HashSet<>();
    }

    @Override
    public void prepare() throws ComponentBootException {

    }

    @Override
    public final void boot() throws ComponentBootException {
        //做boot的事情
        doBoot();
        //做export的事情
        exporter();
    }

    @Override
    public void shutDown() throws ComponentBootException {
    }

    public abstract void doBoot();

    public abstract ComponentExporter exporterInstance();

    private void exporter() throws ComponentBootException {
        ComponentExporter componentExporter = exporterInstance();
        if (null == componentExporter) {
            //没有exporter
            return;
        }
        Set<Class> exports = componentExporter.export();
        if (null == exports || exports.size() <= 0) {
            //没有服务
            CaptainLogger.info(this.getClass(), componentExporter.getClass().getName() + " Not Found Exporters.");
            return;
        }
        exports.forEach(export -> {
            ExporterHolders.HOLDERS.export(componentExporter.getClass(), export);
        });
    }
}
