package com.vaani.dsa.ds.core.queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class FixedSizeQueue<T> {
    private int maxSize;
    private ArrayDeque<T> queue;

    private FixedSizeQueue() {

    }
    public FixedSizeQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = new ArrayDeque<T>(maxSize);
    }

    public boolean add(T t) {
        if (queue.size() == maxSize) {
            queue.removeFirst();
        }
        queue.add(t);
        return true;
    }

    public boolean remove(T t) {
        return queue.remove(t);
    }

    public boolean contains(T t) {
        return queue.contains(t);
    }

    @Override
    public String toString() {
        return queue.toString();
    }

}
