package com.vaani.algo.os.cache;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * https://github.com/xiaoningning/algorithm/blob/master/LRU/src/LRU.java
 * least-recently-used cache linkedhashmap with a fixed size to implement LRU
 */
public class LRUWithLinkedHM<K, V> extends LinkedHashMap<K, V> {


    private int maxSize;

    // private static final long serialVersionUID = 1L;

    public LRUWithLinkedHM(int size) {
        // removeEldestEntry() is called after a put(). To allow maxEntries in
        // cache, capacity should be maxEntries + 1 (+1 for the entry which will
        // be removed). Load factor is taken as 1 because size is fixed. This is
        // less space efficient when very less entries are present, but there
        // will be no effect on time complexity for get(). The third parameter
        // in the base class constructor says that this map is access-order
        // oriented.
        super(size + 1, 1, true);
        maxSize = size;

    }

    public static void main(String[] args) {

        LRUWithLinkedHM<String, Integer> lru = new LRUWithLinkedHM<String, Integer>(2);

        lru.put("A", 1);
        lru.put("B", 2);

        lru.put("C", 3);
        if (lru.containsKey("A"))
            System.out.println("LRU is failed");
        else
            System.out.println("Yes : " + lru.size());


    }

    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest) {
        // After size exceeds max entries, this statement returns true and the
        // oldest value will be removed. Since this map is access oriented the
        // oldest value would be least recently used.
        return size() > maxSize;
    }

}
