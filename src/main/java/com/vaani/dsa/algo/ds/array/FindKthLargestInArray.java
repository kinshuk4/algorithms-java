package com.vaani.dsa.algo.ds.array;

import java.util.Comparator;
import java.util.PriorityQueue;

import static com.vaani.dsa.algo.ds.array.FindKthSmallestInArray.getKthSmallestQuickSelect2;

public class FindKthLargestInArray {
    public static int findKthLargest(int[] nums, int k) {
        if (k < 1 || nums == null) {
            return 0;
        }

        return getKthSmallestQuickSelect2(nums, nums.length - k + 1, 0, nums.length - 1);
    }

    public static int getKthLargestUsingHeap(int[] nums, int k) {
        int n = nums.length;
        // use max heap
        if (k < n - k) {
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            for (int i : nums) {
                if (maxHeap.size() == k) {
                    maxHeap.poll();
                }
                maxHeap.offer(i);
            }

            int kthLargest = 0;
            for (int i = 0; i < k; i++) {
                kthLargest = maxHeap.poll();
            }

            return kthLargest;
        } else { // use min heap

            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            for (int i : nums) {
                // pop out the elements when heap size is higher
                if (minHeap.size() == (n - k + 1)) {
                    minHeap.poll();
                }
                minHeap.offer(i);
            }

            int kthLargest = 0;
            for (int i = 0; i < (n - k + 1); i++) {
                kthLargest = minHeap.poll();
            }

            return kthLargest;
        }

    }

    public static void main(String[] args) {
        int k = 1;
        int[] nums = {1, 3, 4, 2};
        System.out.println(findKthLargest(nums, k));
    }

}
