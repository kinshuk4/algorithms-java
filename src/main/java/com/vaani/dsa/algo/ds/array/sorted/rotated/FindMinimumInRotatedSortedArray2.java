package com.vaani.dsa.algo.ds.array.sorted.rotated;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * <p>
 * Find the minimum element.
 * <p>
 * The array may contain duplicates.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,3,5]
 * Output: 1
 * <p>
 * Example 2:
 * <p>
 * Input: [2,2,2,0,1]
 * Output: 0
 * <p>
 * Note:
 * <p>
 * This is a follow up problem to Find Minimum in Rotated Sorted Array.
 * Would allow duplicates affect the run-time complexity? How and why?
 */
public class FindMinimumInRotatedSortedArray2 {
    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray2 test = new FindMinimumInRotatedSortedArray2();
        int[] num = {1, 1};
        System.out.println(test.findMin(num));
    }

    /**
     * For case where AL == AM == AR, the minimum could be on AM’s left or right side (eg,
     * [1, 1, 1, 0, 1] or [1, 0, 1, 1, 1]). In this case, we could not discard either subarrays and
     * therefore such worst case degenerates to the order of O(n)
     */
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start < end && nums[start] >= nums[end]) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else if (nums[mid] < nums[start]) {
                end = mid;
            } else {
                start++;
            }
        }
        return nums[start];
    }
}
