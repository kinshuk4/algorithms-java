package com.vaani.dsa.algo.array;

/**
 * Given an array of integers, every element appears twice except for one. Find
 * that single one. </br>
 * </br>
 * Note: Your algorithm should have a linear runtime complexity. Could you
 * implement it without using extra memory?
 *
 */
public class SingleNumberOthersRepeat {
    public int singleNumber(int[] A) {
        int num = 0;
        for (int i = 0; i < A.length; i++) {
            num ^= A[i];
        }
        return num;
    }
}
