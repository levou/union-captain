package com.kxl.union.captain.common.notepad;

import java.util.List;

public interface MassNotepad<Note> extends Notepad<Note> {

    void addNotes(List<NoteRecord<Note>> notes);

    List<NoteRecord<Note>> gets(List<String> noteKeys);

    List<NoteRecord<Note>> all();

    void removeNotes(List<String> noteKeys);

    void clearNotes();
}
