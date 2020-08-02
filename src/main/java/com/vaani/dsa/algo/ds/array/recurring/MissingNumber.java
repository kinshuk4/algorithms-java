package com.vaani.dsa.algo.ds.array.recurring;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/missing-number/
 * 268. Missing Number
 * Easy
 * <p>
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,0,1]
 * Output: 2
 * <p>
 * Example 2:
 * <p>
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * <p>
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int expected = n * (n + 1) / 2;

        int sumVal = Arrays.stream(nums).sum();

        return expected - sumVal;
    }
}
