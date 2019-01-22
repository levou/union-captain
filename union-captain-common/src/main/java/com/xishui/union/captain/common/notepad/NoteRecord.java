package com.xishui.union.captain.common.notepad;

import lombok.Data;

@Data
public class NoteRecord<T extends Object> {
    private String key;
    private T value;

    public NoteRecord(String key, T value) {
        this.key = key;
        this.value = value;
    }
}
