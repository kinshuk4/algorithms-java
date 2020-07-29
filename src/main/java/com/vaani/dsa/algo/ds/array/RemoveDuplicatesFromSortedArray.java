package com.vaani.dsa.algo.ds.array;

/**
 * 26. Remove Duplicates from Sorted Array
 * Easy
 * <p>
 * <p>
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * <p>
 * For example,
 * Given input array A = [1,1,2],
 * <p>
 * Your function should return length = 2, and A is now [1,2].
 */

public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int prev = 0;
        // prevNumber =
        for (int i = 1; i < nums.length; i++) {
            if (nums[prev] != nums[i]) {
                prev = prev + 1;
                nums[prev] = nums[i];
            }
        }
        return prev + 1;
    }

    public int removeDuplicates2(int[] A) {
        // Note: The Solution object is instantiated only once and is reused by each test case.

        if (A.length == 0)
            return 0;
        else if (A.length == 1)
            return 1;
        else {
            int writeSeq = 0;
            for (int readSeq = 1; readSeq < A.length; readSeq++) {
                if (A[readSeq - 1] != A[readSeq]) {
                    A[++writeSeq] = A[readSeq];
                }
            }
            return writeSeq + 1;
        }

    }
}
