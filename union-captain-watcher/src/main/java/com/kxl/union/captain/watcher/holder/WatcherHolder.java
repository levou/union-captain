package com.kxl.union.captain.watcher.holder;

import com.kxl.union.captain.common.notepad.DefaultMapNotepad;
import com.kxl.union.captain.common.notepad.NoteRecord;
import com.kxl.union.captain.common.notepad.Notepad;

import java.util.List;

public enum WatcherHolder {
    HOLDER;
    private static Notepad<Object> WATCHER_HOLDER = new DefaultMapNotepad<>();

    public void add(String key, Object value) {
        WATCHER_HOLDER.addNote(key, value);
    }

    public List<NoteRecord<Object>> get(String key) {
        return WATCHER_HOLDER.getNote(key);
    }

    public Object getSingleValue(String key){
        if (!WATCHER_HOLDER.containsNoteKey(key)) {
            return null;
        }
        final List<NoteRecord<Object>> value = WATCHER_HOLDER.getNote(key);
        if (null == value || value.size() <= 0) {
            return null;
        }
        Object result = value.get(0).getValue();
        return result;
    }
    public <T> T get(String key, Class<T> cls) {

        Object result = getSingleValue(key);
        if(cls.isInterface() && cls.isAssignableFrom(result.getClass())){
            return (T) result;
        }
        if (result.getClass().equals(cls)) {
            return (T) result;
        }
        return null;
    }
}
