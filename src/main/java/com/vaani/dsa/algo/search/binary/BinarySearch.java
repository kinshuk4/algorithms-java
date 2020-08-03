package com.vaani.dsa.algo.search.binary;

/**
 * https://leetcode.com/problems/binary-search/
 * 704. Binary Search
 * Easy
 * Given a sorted (in ascending order) integer array nums of n elements and a target value, write a function to search target in nums. If target exists, then return its index, otherwise return -1.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * You may assume that all elements in nums are unique.
 * n will be in the range [1, 10000].
 * The value of each element in nums will be in the range [-9999, 9999].
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 11};
        int i = binarySearchRecursive(nums, 11);
        System.out.println(i);
        int i1 = binarySearchIterative(nums, 1);
        System.out.println(i1);
    }

    // submitted
    public static int binarySearchRecursive(int[] nums, int target) {
        return helper(nums, 0, nums.length - 1, target);
    }

    private static int helper(int[] nums, int start, int end, int key) {
        if (start > end) {
            return -1;
        }
//        int mid = (start + end) >> 1;
//        int mid = (start + end) / 2; // results in overflow
        int mid = start + (end - start) / 2;
        if (nums[mid] == key) {
            return mid;
        } else if (nums[mid] > key) {
            return helper(nums, start, mid - 1, key);
        } else {
            return helper(nums, mid + 1, end, key);
        }
    }

    private static int binarySearchIterative(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }


}
