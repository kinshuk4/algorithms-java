package com.vaani.dsa.algo.ds.array.recurring;

import static com.vaani.dsa.ds.utils.generic.ArrayUtils.sum;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * <p>
 * Example 1:¡1Ω
 * <p>
 * Input: [3,0,1]
 * Output: 2
 * Example 2:
 * <p>
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int expected = n * (n + 1) / 2;

        int sumVal = sum(nums);
        return expected - sumVal;
    }
}
