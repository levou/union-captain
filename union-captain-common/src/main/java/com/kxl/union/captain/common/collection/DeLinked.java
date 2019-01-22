package com.kxl.union.captain.common.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface DeLinked<T> extends Iterator<T>, ComplexOperator<T> {

    boolean hasPre();

    T pre() throws NoSuchNodeException;

    int currentCursor() throws NoSuchElementException;

    int size();

    //重新设置一下队列的current位置为head位置
    void resetHead() throws NoSuchElementException;

    void resetTail() throws NoSuchElementException;

    void resetCursor(int cursor) throws NoSuchElementException;
}
