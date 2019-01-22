package com.xishui.union.captain.component.holder;

import com.xishui.union.captain.common.logger.CaptainLogger;
import com.xishui.union.captain.common.notepad.DefaultMapNotepad;
import com.xishui.union.captain.common.notepad.MassNotepad;
import com.xishui.union.captain.common.notepad.NoteRecord;
import com.xishui.union.captain.component.Component;
import com.xishui.union.captain.component.ComponentCaptain;
import com.xishui.union.captain.component.exception.ComponentDefineException;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum ExporterHolders {
    HOLDERS;
    private final static MassNotepad<Class> EXPORTER_NOTEPADS = new DefaultMapNotepad<>();
    //应该要区分一下，是从那个地方来的
    private final static MassNotepad<Class> EXPORTER_FROMS = new DefaultMapNotepad<>();

    private final static MassNotepad<Object> EXPORTER_INSTANCES = new DefaultMapNotepad<>();

    public void export(Class<?> fromClass, Class exporterClass) throws ComponentDefineException {
        if (null == fromClass || null == exporterClass) {
            CaptainLogger.error(this.getClass(), "Export  exporterClass Or fromClass is null.");
            return;
        }
        Object exporterObject = instanceExporter(exporterClass);
        if (null == exporterObject) {
            CaptainLogger.error(this.getClass(), "Export "
                    + exporterClass.getName() + " Instance Object is null. ");
            return;
        }
        ExporterHolders.EXPORTER_FROMS.addNote(fromClass.getName(), exporterClass);
        if (ExporterHolders.EXPORTER_NOTEPADS.containsNoteKey(exporterClass.getName())) {
            //exist
            return;
        }
        ExporterHolders.EXPORTER_NOTEPADS.addNote(exporterClass.getName(), exporterClass);
        //把export instance化
        ExporterHolders.EXPORTER_INSTANCES.addNote(exporterClass.getName(), exporterObject);
    }


    public Class matchExporter(String exporterClassName) {
        if (ExporterHolders.EXPORTER_NOTEPADS.containsNoteKey(exporterClassName)) {
            //exists
            return ExporterHolders.EXPORTER_NOTEPADS.getNote(exporterClassName).get(0).getValue();
        }
        return null;
    }

    public Object getExporter(String exporterClassName) {
        if (ExporterHolders.EXPORTER_INSTANCES.containsNoteKey(exporterClassName)) {
            //需要实现另外一种结构
            return ExporterHolders.EXPORTER_INSTANCES.getNote(exporterClassName).get(0).getValue();
        }
        return null;
    }

    public Set<Class> getAllExporter(){
        List<NoteRecord<Class>> allExporter = EXPORTER_NOTEPADS.all();
        if(null == allExporter || allExporter.size() <= 0){
            return null;
        }
        final Set<Class> resExporter = new HashSet<>();
        allExporter.forEach(record->resExporter.add(record.getValue()));
        return resExporter;
    }
    private Object instanceExporter(Class exporterClass) throws ComponentDefineException {
        try {
            if (exporterClass.isInterface()) {
                CaptainLogger.error(this.getClass(), "Export "
                        + exporterClass.getName() + " Is Interface ,Can't Be Instance ");
                return null;
            }
            Class cls = Class.forName(exporterClass.getName(), true, Component.class.getClassLoader());
            if (Modifier.isAbstract(cls.getModifiers())) {
                CaptainLogger.error(this.getClass(), "Export "
                        + exporterClass.getName() + " Is Abstract Class ,Can't Be Instance ");
                return null;
            }
            Object instance = cls.newInstance();
            if (instance instanceof ComponentCaptain) {
                return instance;
            }
            CaptainLogger.error(this.getClass(), "Export "
                    + exporterClass.getName() + " Not implements " + ComponentCaptain.class.getName());
        } catch (Exception e) {
            CaptainLogger.error(this.getClass(), "Exporter Class " + exporterClass.getName() + " Instance Err." + e.getMessage());
            throw new ComponentDefineException(e);
        }
        return null;
    }
}
