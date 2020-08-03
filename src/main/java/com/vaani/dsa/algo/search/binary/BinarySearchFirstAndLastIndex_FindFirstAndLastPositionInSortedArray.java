package com.vaani.dsa.algo.search.binary;


import static com.vaani.dsa.algo.search.binary.BinarySearchFirstIndex.searchFirstIterative;
import static com.vaani.dsa.algo.search.binary.BinarySearchLastIndex.searchLastIterative;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * If the target is not found in the array, return [-1, -1].
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class BinarySearchFirstAndLastIndex_FindFirstAndLastPositionInSortedArray {

    public static void main(String[] args) {
        int[] arr = {5,7,7,8,8,10};
        System.out.println(searchRange(arr, 8));
    }
    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = searchFirstIterative(nums, target);
        result[1] = searchLastIterative(nums, target);
        return result;
    }


}
