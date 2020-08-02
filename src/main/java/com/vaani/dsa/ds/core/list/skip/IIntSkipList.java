package com.vaani.dsa.ds.core.list.skip;

public interface IIntSkipList {
    boolean search(int target);

    void add(int num);

    boolean remove(int num);
}
