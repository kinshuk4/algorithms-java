package com.vaani.dsa.algo.ds.array.nsum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 * 1. Two Sum
 * Easy
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * Example:
 * <p>
 * Given nums = [2, 7, 11, 15], target = 9,
 * <p>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int[] result = twoSum(nums, 6);
        System.out.println(result[0] + " " + result[1]);
    }

    // using sorting
    public static int[] twoSum3(int[] numbers, int target) {
        int[] result = new int[2];
        Arrays.sort(numbers);
        return TwoSum2.twoSumOnSortedArr(numbers, target);
    }

    // with realtime update of map
    public static int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];

        Map<Integer, Integer> map = new HashMap<>();
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

    // with offline update of map
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int otherNum = target - nums[i];
            if (map.containsKey(otherNum) && !map.get(otherNum).equals(i)) {
                return new int[]{i, map.get(otherNum)};
            }
        }
        return new int[]{-1, -1};
    }


}
