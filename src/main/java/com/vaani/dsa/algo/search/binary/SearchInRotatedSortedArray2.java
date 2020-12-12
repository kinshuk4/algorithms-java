package com.vaani.dsa.algo.search.binary;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * 81. Search in Rotated Sorted Array II
 * Medium
 * <p>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 * <p>
 * You are given a target value to search. If found in the array return true, otherwise return false.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * Example 2:
 * <p>
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 * Follow up:
 * <p>
 * This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
 * Would this affect the run-time complexity? How and why?
 */

public class SearchInRotatedSortedArray2 {
    public boolean search(int[] nums, int target) {
        if (nums.length == 0) {
            return false;
        }
        int s = 0, e = nums.length - 1;

        while (s <= e) {
            int m = s + (e - s) / 2;
            if (nums[m] == target || nums[s] == target || nums[e] == target) {
                return true;
            }
            else if (nums[s] < nums[m]) {
                if (nums[s] < target && target < nums[m]) {
                    e = m - 1;
                }
                else {
                    s = m + 1;
                }
            }
            else if (nums[m] < nums[e]) {
                if (nums[m] < target && target < nums[e]) {
                    s = m + 1;
                }
                else {
                    e = m - 1;
                }
            }
            else {
                e--;
            }
        }
        return false;

    }
}
