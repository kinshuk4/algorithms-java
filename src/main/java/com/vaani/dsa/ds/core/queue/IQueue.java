package com.vaani.dsa.ds.core.queue;

/**
 * Created by kchandra on 19/08/16.
 */
public interface IQueue<T> {
    void enqueue(T x);
    T dequeue();
    T front();
    boolean isEmpty();
}
