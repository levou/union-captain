package com.kxl.union.captain.common.notepad;

import java.util.List;

/**
 * 笔记本(笔记本应归类存放)，注意要给笔记加索引，不然就乱套了
 * 1.新增笔记
 * 2.查询笔记
 * 3.删除笔记
 */
public interface Notepad<Note extends Object> {

    void addNote(String noteKey, Note noteContent);

    List<NoteRecord<Note>> getNote(String noteKey);

    void removeNote(String noteKey);

    boolean containsNoteKey(String noteKey);
}
