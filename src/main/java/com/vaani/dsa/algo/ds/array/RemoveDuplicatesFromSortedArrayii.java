package com.vaani.dsa.algo.ds.array;

/**
 * 80. Remove Duplicates from Sorted Array II
 * Medium
 * <p>
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
 * <p>
 * Do not allocate extra space for another array; you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Clarification:
 * <p>
 * Confused why the returned value is an integer, but your answer is an array?
 * <p>
 * Note that the input array is passed in by reference, which means a modification to the input array will be known to the caller.
 * <p>
 * Internally you can think of this:
 * <p>
 * // nums is passed in by reference. (i.e., without making a copy)
 * int len = removeDuplicates(nums);
 * <p>
 * // any modification to nums in your function would be known by the caller.
 * // using the length returned by your function, it prints the first len elements.
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,2,2,3]
 * Output: 5, nums = [1,1,2,2,3]
 * Explanation: Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively. It doesn't matter what you leave beyond the returned length.
 * Example 2:
 * <p>
 * Input: nums = [0,0,1,1,1,1,2,3,3]
 * Output: 7, nums = [0,0,1,1,2,3,3]
 * Explanation: Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively. It doesn't matter what values are set beyond the returned length.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums is sorted in ascending order.
 */

public class RemoveDuplicatesFromSortedArrayii {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0 || n == 1 || n == 2) {
            return n;
        }

        int pre = Integer.MAX_VALUE;
        int writeSeq = 1;
        for (int readSeq = 1; readSeq < n; readSeq++) {
            if (nums[readSeq] != nums[readSeq - 1]) {
                nums[writeSeq++] = nums[readSeq];
            } else {
                if (pre != nums[readSeq]) {
                    pre = nums[readSeq];
                    nums[writeSeq++] = nums[readSeq];
                }
            }
        }
        return writeSeq;
    }
}
