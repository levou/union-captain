package com.kxl.union.captain.common.collection;

public interface ComplexOperator<T> extends Operator<T> {

    void addFirst(T node);

    void addLast(T node);

    void addAfter(int index,T node) throws NoSuchNodeException;

    void addBefore(int index,T node) throws NoSuchNodeException;

    T getFirst() throws NoSuchNodeException;

    T getLast() throws NoSuchNodeException;

    void increament();

    void descreament();

}
