package com.vaani.dsa.algo.technique.sliding_window;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
 * <p>
 * Follow up:
 * Could you solve it in linear time?
 * <p>
 * Example:
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int[] result = new int[nums.length - k + 1];

        // In deque we insert element at Start i.e. first
        // current max will be stored at last
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            // remove the item from window once index is out
            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.poll();
            }

            // if Current number is already higher than numbers in deque, just remove as this can be our sliding max
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.removeLast();
            }

            deque.offer(i);

            // as i + 1 is more than k, we can start filling in the result
            if (i + 1 >= k) {
                result[i + 1 - k] = nums[deque.peek()];
            }
        }

        return result;
    }
}
