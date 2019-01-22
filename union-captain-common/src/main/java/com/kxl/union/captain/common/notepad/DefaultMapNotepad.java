package com.kxl.union.captain.common.notepad;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultMapNotepad<Note> implements MassNotepad<Note> {

    private final Map<String, List<NoteRecord<Note>>> PADS = new ConcurrentHashMap<>();

    @Override
    public void addNotes(List<NoteRecord<Note>> notes) {
        if (null == notes || notes.size() <= 0) {
            return;
        }
        notes.forEach(noteRecord -> {
            if (PADS.containsKey(noteRecord.getKey())) {
                PADS.get(noteRecord.getKey()).add(noteRecord);
            } else {
                final List<NoteRecord<Note>> noteList = new ArrayList<>();
                noteList.add(noteRecord);
                PADS.put(noteRecord.getKey(), noteList);
            }
        });
    }

    @Override
    public List<NoteRecord<Note>> gets(List<String> noteKeys) {
        if (null == noteKeys || noteKeys.size() <= 0) {
            return null;
        }
        final List<NoteRecord<Note>> result = new ArrayList<>();
        noteKeys.forEach(noteKey -> {
            if (PADS.containsKey(noteKey)) {
                result.addAll(PADS.get(noteKey));
            }
        });
        return result;
    }

    @Override
    public List<NoteRecord<Note>> all() {
        if (PADS.isEmpty()) {
            return null;
        }
        final List<NoteRecord<Note>> result = new ArrayList<>();
        PADS.forEach((k, v) -> result.addAll(v));
        return result;
    }

    @Override
    public void removeNotes(List<String> noteKeys) {
        if (null == noteKeys || noteKeys.size() <= 0) {
            return;
        }
        noteKeys.forEach(noteKey -> {
            if (PADS.containsKey(noteKey)) {
                PADS.remove(noteKey);
            }
        });
    }

    @Override
    public void clearNotes() {
        PADS.clear();
    }

    @Override
    public void addNote(String noteKey, Note noteContent) {
        final List<NoteRecord<Note>> noteRecords = new ArrayList<>();
        noteRecords.add(new NoteRecord<>(noteKey,noteContent));
        this.addNotes(noteRecords);

    }

    @Override
    public List<NoteRecord<Note>> getNote(String noteKey) {
        return PADS.get(noteKey);
    }

    @Override
    public void removeNote(String noteKey) {
        if (PADS.containsKey(noteKey)) {
            PADS.remove(noteKey);
        }
    }

    @Override
    public boolean containsNoteKey(String noteKey) {
        return PADS.containsKey(noteKey);
    }
}
