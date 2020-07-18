package com.vaani.dsa.algo.ds.array.nsum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * <p>
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
 * Please note that your returned answers (both index1 and index2) are not zero-based.
 * <p>
 * You may assume that each input would have exactly one solution.
 * <p>
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 * <p>
 */
public class TwoSum {
    public static int[] twoSumUsingSorting(int[] numbers, int target) {
        int[] result = new int[2];
        Arrays.sort(numbers);
        return TwoSum2.twoSumOnSortedArr(numbers, target);
    }

    public static int[] twoSumUsingHashTable(int[] numbers, int target) {
        int[] result = new int[2];

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(target - numbers[i])) {
                result[0] = map.get(target - numbers[i]);
                result[1] = i;
                return result;
            } else {
                map.put(numbers[i], i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int[] result = twoSumUsingHashTable(nums, 6);
        System.out.println(result[0] + " " + result[1]);
    }
}
