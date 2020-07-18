package com.vaani.dsa.algo.ds.heap;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.Assert;

import java.util.*;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 * <p>
 * Given a non-empty array of integers, return the k most frequent elements.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * <p>
 * Note:
 * <p>
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
 * You can return the answer in any order.
 */
public class TopKFrequentElements {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};

        TopKFrequentElements underTest = new TopKFrequentElements();
        Assert.assertArrayEquals(new int[]{1, 2}, underTest.topKFrequent(nums, 2));
    }

    public int[] topKFrequent(int[] nums, int k) {
        // O(1) time
        if (k == nums.length) {
            return nums;
        }

        // 1. build hash map : character and how often it appears
        // O(N) time
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        Queue<Integer> minHeap = new PriorityQueue<>((n1, n2) -> frequencyMap.get(n1) - frequencyMap.get(n2));
//        Queue<Integer> minHeap = new PriorityQueue<>(        Comparator.comparingInt(frequencyMap::get));


        // 2. keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (int n : frequencyMap.keySet()) {
            minHeap.add(n);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // 3. build an output array
        // O(k log k) time
        int[] top = new int[k];
        for (int i = k - 1; i >= 0; --i) {
            top[i] = minHeap.poll();
        }
        return top;
    }

    // https://leetcode.com/articles/top-k-frequent-elements/#
    public int[] topKFrequentQuickSelect(int[] nums, int k ){
        throw new NotImplementedException("Not yet implemented");
    }
}
