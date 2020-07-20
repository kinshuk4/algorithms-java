package com.vaani.dsa.ds.design.os.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


/**
 * least-recently-used cache
 * hashmap + queue with a fixed size to implement LRU
 */
public class LRUCacheWithQueueAndHashMap<K, V> {
    private HashMap<K, V> map;
    private Queue<K> queue;
    private int max;

    public LRUCacheWithQueueAndHashMap(int n) {
        map = new HashMap<>();
        queue = new LinkedList<>();
        max = n;
    }

    public static void main(String[] args) {
        LRUCacheWithQueueAndHashMap<String, Integer> lruCache = new LRUCacheWithQueueAndHashMap<>(2);

        lruCache.add("A", 1);
        lruCache.add("B", 2);
        System.out.println("getfirst: " + lruCache.getFirst());
        lruCache.add("C", 3);

        System.out.println("size: " + lruCache.size());
        System.out.println("c: " + lruCache.get("C"));
        System.out.println("getfirst: " + lruCache.getFirst());

        try {
            System.out.println(lruCache.get("A"));
        } catch (NullPointerException e) {
            System.out.println("null");
        }


    }

    public boolean add(K key, V value) {
        if (map.size() < max) {
            map.put(key, value);
            queue.add(key);
            return true;
        } else if (map.size() == max) {
            map.remove(queue.poll());
            queue.add(key);
            map.put(key, value);
            return true;
        }
        return false;
    }

    public boolean remove(K key) {
        if (queue.contains(key)) {
            queue.remove(key);
            map.remove(key);
            return true;
        }
        return false;
    }

    public int size() {
        return map.size();
    }

    public V get(K key) {
        if (queue.contains(key)) {
            return map.get(key);
        } else
            return null;
    }

    public V getFirst() {
        if (!queue.isEmpty()) {
            return map.get(queue.peek());
        } else
            return null;
    }
}