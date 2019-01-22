package com.xishui.union.captain.common.collection;

public interface Operator<T> {
    //当前位置增加
    void add(T t);
    //通过游标获取节点
    T get(int cursor) throws NoSuchNodeException;
    //通过游标删除节点
    void remove(int cursor) throws NoSuchNodeException;

}
