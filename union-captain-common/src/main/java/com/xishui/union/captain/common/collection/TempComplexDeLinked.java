//package com.kxl.union.captain.common.collection;
//
//import java.util.LinkedList;
//import java.util.NoSuchElementException;
//
//public class TempComplexDeLinked<T> implements DeLinked<T> {
//    //    private static final int DEFAULT_START_CURSOR = 0;
////    /**
////     * 总大小
////     */
////    private volatile int size;
////    /**
////     * 头节点
////     */
////    private transient Node<T> head;
////    /**
////     * 操作的当前节点
////     */
////    private transient Node<T> current;
////    /**
////     * 尾节点
////     */
////    private transient Node<T> tail;
////
////    public ComplexDeLinked() {
////        this.size = 0;
////    }
////
////    public static <T> ComplexDeLinked<T> emptyDeLinked() {
////        return new ComplexDeLinked<>();
////    }
////
////    @Override
////    public boolean hasPre() {
////        return null != current && null != current.pre;
////    }
////
////    //上一个元素,并把当前元素设置为上一个元素
////    @Override
////    public T pre() throws NoSuchNodeException {
////        if (checkEmpty() || current == null) {
////            throw new NoSuchNodeException("DeLinked No Element for Pre() ");
////        }
////        if (null == current.pre) {
////            throw new NoSuchNodeException("DeLinked CurrentNode is Root Node.");
////        }
////        final T value = current.pre.element;
////        current = current.pre;
////        return value;
////    }
////
////    //当前节点的值
////    @Override
////    public T getValue() throws NoSuchElementException {
////        return current.element;
////    }
////
////    @Override
////    public int currentCursor() throws NoSuchElementException {
////        return current.cursor;
////    }
////
////    //添加头节点,如果还是一个空集合,则都为first
////    @Override
////    public void addFirst(T node) {
////        if (checkEmpty()) {
////            initDeLinked(node);
////            return;
////        }
////        Node<T> first = new Node<T>(node, null, head, DEFAULT_START_CURSOR);
////        head.pre = first;
////        head = first;
////        incrementCursor(head);
////        size++;
////
////    }
////
////    //尾部添加
////    @Override
////    public void addLast(T node) {
////        final Node<T> l = tail;
////        final Node<T> newNode = new Node<T>(node, l, null, size);
////        tail = newNode;
////        if(null == l){
////            head = newNode;
////        }else{
////            l.next = newNode;
////        }
////        current = newNode;
////        size++;
////    }
////
////    //添加到当前节点之后
////    @Override
////    public void addAfter(T node) throws NoSuchNodeException {
////        if (checkEmpty()) {
////            initDeLinked(node);
////            return;
////        }
////        if (current.cursor == tail.cursor) {
////            addLast(node);
////            return;
////        }
////        Node<T> after = new Node<T>(node, current, current.next, current.cursor + 1);
////        current.next.pre = after;
////        current.next = after;
////        incrementCursor(after);
////        current = after;
////        size++;
////    }
////
////    //添加到当前节点之前
////    @Override
////    public void addBefore(T node) throws NoSuchNodeException {
////        if (checkEmpty()) {
////            initDeLinked(node);
////            return;
////        }
////        Node<T> before = new Node<T>(node, current.pre, current, current.cursor);
////        current.pre.next = before;
////        current.pre = before;
////        incrementCursor(before);
////        size++;
////    }
////
////    //获取第一个节点
////    @Override
////    public T getFirst() throws NoSuchNodeException {
////        return head.element;
////    }
////
////    //获取最后一个节点
////    @Override
////    public T getLast() throws NoSuchNodeException {
////        return tail.element;
////    }
////
////    //添加到当前元素后面
////    @Override
////    public void add(T t) {
////        addLast(t);
////    }
////
////    @Override
////    public int size() {
////        return size;
////    }
////
////    //通过游标获取元素
////    @Override
////    public T get(int cursor) throws NoSuchNodeException {
////        if (cursor < 0) {
////            throw new NoSuchElementException("DeLinked Input Cursor Less Than 0 :" + cursor);
////        }
////        if (size <= 0) {
////            throw new NoSuchElementException("DeLinked is Empty");
////        }
////        if (null == head || null == tail) {
////            throw new NoSuchElementException("DeLinked Head or Tail is null");
////        }
////        if (checkCursorModifier(cursor)) {
////            throw new NoSuchElementException("DeLinked Input Cursor " + cursor + " OutOfCursor by Max " + size);
////        }
////        //在当前节点与头节点之间
////        if (cursor <= current.cursor) {
////            getValue(cursor, current);
////        }
////        return getValue(cursor, tail);
////    }
////
////
////    @Override
////    public void remove(int cursor) throws NoSuchNodeException {
////
////    }
////
////    @Override
////    public boolean hasNext() {
////        return null != current && null != current.next;
////    }
////
////    //下一个元素，并把当前元素设置为下一个元素
////    @Override
////    public T next() {
////        if (checkEmpty() || current == null) {
////            throw new NoSuchNodeException("DeLinked No Element for nextF() ");
////        }
////        if (null == current.next) {
////            throw new NoSuchNodeException("DeLinked CurrentNode is Tail Node.");
////        }
////        final T value = current.next.element;
////        current = current.next;
////        return value;
////    }
////
////    @Override
////    public void resetCurrent() throws NoSuchElementException {
////        this.current = this.head;
////    }
////
////    private final boolean checkCursorModifier(int cursor) {
////        return cursor <= size;
////    }
////
////    /**
////     * 都采取向前查找
////     */
////    private final T getValue(int cursor, Node<T> endNode) throws NoSuchElementException {
////        Node<T> searchNode = endNode;
////        if (searchNode.cursor == cursor) {
////            return searchNode.element;
////        }
////        while (null != searchNode.pre) {
////            //find it
////            if (searchNode.pre.cursor == cursor) {
////                current = searchNode.pre;
////                return searchNode.pre.element;
////            }
////            searchNode = searchNode.pre;
////        }
////        return null;
////    }
////
////    /**
////     * 做游标依次后移+1
////     */
////    private final void incrementCursor(Node<T> startNode) {
////        Node<T> nextNode = startNode.next;
////        while (null != nextNode) {
////            nextNode.setCursor(nextNode.cursor + 1);
////            nextNode = nextNode.next;
////        }
////    }
////
////    /**
////     * 做游标依次后移-1
////     */
////    private final void decrementCursor(Node<T> startNode) {
////        Node<T> nextNode = startNode.next;
////        while (null != nextNode) {
////            nextNode.setCursor(nextNode.cursor - 1);
////            nextNode = nextNode.next;
////        }
////    }
////
////    private final boolean checkEmpty() {
////        return (null == head || size <= 0);
////    }
////
////    private final void initDeLinked(T node) {
////        Node<T> first = new Node<T>(node, null, null, DEFAULT_START_CURSOR);
////        head = first;
////        current = first;
////        tail = first;
////        size++;
////    }
////
////    @Data
////    protected class Node<T> {
////        private T element;
////        private Node<T> pre;
////        private Node<T> next;
////        private int cursor;
////
////        public Node(T element, Node<T> pre, Node<T> next, int cursor) {
////            this.element = element;
////            this.pre = pre;
////            this.next = next;
////            this.cursor = cursor;
////        }
////    }
//}
