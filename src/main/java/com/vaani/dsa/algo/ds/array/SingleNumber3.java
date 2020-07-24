package com.vaani.dsa.algo.ds.array;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/single-number-iii/
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
 * <p>
 * Example:
 * <p>
 * Input:  [1,2,1,3,2,5]
 * Output: [3,5]
 * <p>
 * Note:
 * <p>
 * The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 */
public class SingleNumber3 {
    public static List<Integer> singleNumberIII(int[] nums) {
        int xor = 0;
        for (int i = 0; i < nums.length; i++) {
            xor ^= nums[i];
        }

        int mark = 1;
        while ((mark & xor) == 0) {
            mark <<= 1;
        }

        int num1 = 0, num2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((mark & nums[i]) == 0) {
                num1 ^= nums[i];
            } else {
                num2 ^= nums[i];
            }
        }

        List<Integer> result = new ArrayList<>();
        result.add(num1);
        result.add(num2);
        return result;
    }

    /*
        In the first pass, we XOR all elements in the array, and get the XOR of the two numbers we need to find. Note that since the two numbers are distinct, so there must be a set bit (that is, the bit with value '1') in the XOR result. Find out an arbitrary set bit (for example, the rightmost set bit).

    In the second pass, we divide all numbers into two groups, one with the aforementioned bit set, another with the aforementinoed bit unset. Two different numbers we need to find must fall into thte two distrinct groups. XOR numbers in each group, we can find a number in either group.

Complexity:

    Time: O (n)

    Space: O (1)
     */
    public int[] singleNumber(int[] nums) {
        // Pass 1 :
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // Get its last set bit
        diff &= -diff;

        // Pass 2 :
        int[] result = {0, 0}; // this array stores the two numbers we will return
        for (int num : nums)
        {
            if ((num & diff) == 0) // the bit is not set
            {
                result[0] ^= num;
            }
            else // the bit is set
            {
                result[1] ^= num;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] A = {1, 2, 2, 3, 4, 4, 5, 3};
        System.out.println(singleNumberIII(A));
    }
}
