package com.xishui.union.captain.common.collection;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * 双向链表
 */
public class ComplexDeLinked<T> implements DeLinked<T> {
    //记录当前操作的游标位置
    private int currentCursor;

    private final LinkedList<T> linked = new LinkedList<>();

    public static <T> ComplexDeLinked<T> emptyDeLinked() {
        return new ComplexDeLinked<>();
    }

    public ComplexDeLinked() {
        this.currentCursor = 0;
    }

    @Override
    public boolean hasPre() {
        if (currentCursor < 0) {
            return false;
        }
        return null != linked.get(currentCursor);
    }

    @Override
    public T pre() throws NoSuchNodeException {
        if (currentCursor < 0) {
            return null;
        }
        final T value = linked.get(currentCursor);
        this.currentCursor--;
        return value;
    }

    @Override
    public int currentCursor() throws NoSuchElementException {
        return this.currentCursor;
    }

    @Override
    public int size() {
        return linked.size();
    }

    @Override
    public void resetHead() throws NoSuchElementException {
        this.currentCursor = 0;
    }

    @Override
    public void resetTail() throws NoSuchElementException {
        this.currentCursor = this.linked.size() - 1;
    }

    @Override
    public void resetCursor(int cursor) throws NoSuchElementException {
        if (cursor >= this.linked.size()) {
            throw new NoSuchElementException("No Element Cursor is " + cursor);
        }
        this.currentCursor = cursor;
    }

    @Override
    public void addFirst(T node) {
        linked.addFirst(node);
        this.currentCursor = 0;
    }

    @Override
    public void addLast(T node) {
        linked.addLast(node);
        this.currentCursor = linked.size() - 1;
    }

    @Override
    public void addAfter(int index, T node) throws NoSuchNodeException {
        linked.add(index + 1, node);
        this.currentCursor = index;
    }

    @Override
    public void addBefore(int index, T node) throws NoSuchNodeException {
        linked.add(index, node);
        this.currentCursor = index;
    }

    @Override
    public T getFirst() throws NoSuchNodeException {
        final T value = linked.getFirst();
        this.currentCursor = 0;
        return value;
    }

    @Override
    public T getLast() throws NoSuchNodeException {
        final T value = linked.getLast();
        this.currentCursor = linked.size() - 1;
        return value;
    }

    @Override
    public void add(T t) {
        linked.add(t);
        this.currentCursor = linked.size() - 1;
    }

    @Override
    public T get(int cursor) throws NoSuchNodeException {
        checkModel(cursor);
        final T value = linked.get(cursor);
        this.currentCursor = cursor;
        return value;
    }

    @Override
    public void remove(int cursor) throws NoSuchNodeException {
        checkModel(cursor);
        linked.remove(cursor);
    }

    private void checkModel(int cursor) throws NoSuchElementException {
        if (cursor >= linked.size() || cursor < 0) {
            throw new NoSuchNodeException("No Element by Cursor " + cursor);
        }
    }

    @Override
    public boolean hasNext() {
        if (currentCursor >= linked.size()) {
            return false;
        }
        return null != linked.get(currentCursor);
    }

    @Override
    public T next() {
        if (currentCursor >= linked.size()) {
            return null;
        }
        final T value = linked.get(currentCursor);
        currentCursor++;
        return value;
    }

    @Override
    public void increament() {
        this.currentCursor++;
    }

    @Override
    public void descreament() {
        this.currentCursor--;
    }
}
