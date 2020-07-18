package com.vaani.dsa.algo.ds.array;

import java.util.Arrays;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), find the duplicate element in the array. The duplicate element repeats twice.
 */
public class FindDuplicateInRangedArray {
    public int findDuplicate(int[] nums) {
        int N = nums.length;
        int n = N - 1;
        int expectedSum = n * (n + 1) / 2;

        int actualSum = Arrays.stream(nums).sum();

        return actualSum - expectedSum;
    }
}
