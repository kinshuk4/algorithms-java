package com.vaani.dsa.ds.design.os.cache;

import java.util.HashMap;
import java.util.Map;


// Tail has the latest value and head the oldest
public class LRUCacheWithDLLNodeAndHashMap {
    static class DLLNodeKV {
        int key;
        int value;
        DLLNodeKV prev;
        DLLNodeKV next;

        public DLLNodeKV(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private DLLNodeKV head;
    private DLLNodeKV tail;
    private final Map<Integer, DLLNodeKV> map;
    private final int capacity;

    public LRUCacheWithDLLNodeAndHashMap(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

    }

    public int get(int key) {
        if (map.containsKey(key)) {
            DLLNodeKV value = map.get(key);
            removeDLLNode(value);
            offerDLLNode(value);
            return value.value;
        }

        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            DLLNodeKV old = map.get(key);
            old.value = value;

            //move to tail
            removeDLLNode(old);
            offerDLLNode(old);
        } else {
            if (map.size() >= capacity) {
                //delete head
                map.remove(head.key);
                removeDLLNode(head);
            }

            //add to tail
            DLLNodeKV node = new DLLNodeKV(key, value);
            offerDLLNode(node);
            map.put(key, node);
        }
    }

    private void removeDLLNode(DLLNodeKV node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
    }

    private void offerDLLNode(DLLNodeKV n) {
        if (tail != null) {
            tail.next = n;
        }

        n.prev = tail;
        n.next = null;
        tail = n;

        if (head == null) {
            head = tail;
        }
    }


}
