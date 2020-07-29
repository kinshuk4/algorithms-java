package com.vaani.dsa.algo.ds.array.sorted.rotated;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p>
 * Find the minimum element.
 * <p>
 * You may assume no duplicate exists in the array.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,4,5,1,2]
 * Output: 1
 * <p>
 * Example 2:
 * <p>
 * Input: [4,5,6,7,0,1,2]
 * Output: 0
 */
public class FindMinimumInRotatedSortedArray {
    public static void main(String[] args) {
        int[] num = {4, 5, 6, 7, 8, 1, 2, 3};
        FindMinimumInRotatedSortedArray test = new FindMinimumInRotatedSortedArray();
        System.out.println(test.findMin(num));
    }

    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start < end && nums[start] > nums[end]) {
            int mid = start + (end - start) / 2;


            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return nums[start];
    }
}
