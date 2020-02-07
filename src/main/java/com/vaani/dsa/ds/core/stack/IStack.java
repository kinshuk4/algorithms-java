package com.vaani.dsa.ds.core.stack;

public interface IStack<T> {
    void push(T x);
    T pop();
    T top();

    boolean isEmpty();

    void makeEmpty();
}