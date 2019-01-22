package com.kxl.union.captain.component.holder;

import com.kxl.union.captain.common.notepad.DefaultMapNotepad;
import com.kxl.union.captain.common.notepad.MassNotepad;
import com.kxl.union.captain.common.notepad.NoteRecord;
import com.kxl.union.captain.component.AbstractComponent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum InstanceHolders {
    HOLDERS;
    private static final MassNotepad<AbstractComponent>
            INSTANCE_NOTEPADS = new DefaultMapNotepad<>();

    public void addInstance(AbstractComponent componentInstance) {
        if (InstanceHolders.INSTANCE_NOTEPADS.containsNoteKey(componentInstance.getClass().getName())) {
            //exist
            return;
        }
        InstanceHolders.INSTANCE_NOTEPADS.addNote(componentInstance.getClass().getName(), componentInstance);
    }

    public Set<AbstractComponent> allInstances() {
        final Set<AbstractComponent> instances = new HashSet<>();
        final List<NoteRecord<AbstractComponent>> records = InstanceHolders.INSTANCE_NOTEPADS.all();
        if (null == records || records.size() <= 0) {
            return instances;
        }
        records.forEach(record -> instances.add(record.getValue()));
        return instances;
    }

    public boolean checkExistByInstanceKey(String instanceKey) {
        return InstanceHolders.INSTANCE_NOTEPADS.containsNoteKey(instanceKey);
    }
}
