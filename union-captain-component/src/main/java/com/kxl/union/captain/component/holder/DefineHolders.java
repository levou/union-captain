package com.kxl.union.captain.component.holder;

import com.kxl.union.captain.common.notepad.DefaultMapNotepad;
import com.kxl.union.captain.common.notepad.MassNotepad;
import com.kxl.union.captain.common.notepad.NoteRecord;
import com.kxl.union.captain.component.ComponentDefine;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum DefineHolders {
    HOLDERS;
    private static final MassNotepad<ComponentDefine> COMPONENT_DEFINE_MASS_NOTEPAD = new DefaultMapNotepad<>();

    public void addDefines(final Set<ComponentDefine> componentDefines) {
        if (null == componentDefines || componentDefines.size() <= 0) {
            return;
        }
        componentDefines.forEach(componentDefine ->
                DefineHolders.COMPONENT_DEFINE_MASS_NOTEPAD.addNote(componentDefine.getName(), componentDefine));
    }

    public Set<ComponentDefine> allDefines() {
        final Set<ComponentDefine> componentDefines = new HashSet<>();
        final List<NoteRecord<ComponentDefine>> noteRecords = DefineHolders.COMPONENT_DEFINE_MASS_NOTEPAD.all();
        if (null == noteRecords || noteRecords.size() <= 0) {
            return componentDefines;
        }
        noteRecords.forEach(noteRecord -> componentDefines.add(noteRecord.getValue()));
        return componentDefines;
    }

    public boolean checkExistDefineByName(String defineName) {
        return DefineHolders.COMPONENT_DEFINE_MASS_NOTEPAD.containsNoteKey(defineName);
    }

    /**
     * 调用频繁的话，性能有待提升
     */
    public boolean checkExistDefineByClassName(String defineClassName) {
        final List<NoteRecord<ComponentDefine>> noteRecords = DefineHolders.COMPONENT_DEFINE_MASS_NOTEPAD.all();
        if (null == noteRecords || noteRecords.size() <= 0) {
            return false;
        }
        for (final NoteRecord<ComponentDefine> noteRecord : noteRecords) {
            if (defineClassName.equals(noteRecord.getValue())) {
                return true;
            }
        }
        return false;
    }
}
