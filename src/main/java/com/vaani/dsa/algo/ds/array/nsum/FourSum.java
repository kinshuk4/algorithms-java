package com.vaani.dsa.algo.ds.array.nsum;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/4sum/
 * 18. 4Sum
 * Medium
 * <p>
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 * <p>
 * Note:
 * <p>
 * The solution set must not contain duplicate quadruplets.
 * <p>
 * Example:
 * <p>
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 * <p>
 * A solution set is:
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 */

public class FourSum {
    public static void main(String[] args) throws Exception {
        FourSum underTest = new FourSum();

//        int[] nums = {1, 0, -1, 0, -2, 2};
//        System.out.println(new FourSum().fourSum2(nums, 0));

        Assert.assertEquals(List.of(List.of(-4, 5, 5, 5), List.of(0, 1, 5, 5)),
                underTest.fourSum(new int[]{0, 1, 5, 0, 1, 5, 5, -4}, 11));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        if (length < 4) {
            return result;
        }

        for (int i = 0; i < length - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = length - 1; j >= 2; j--) {
                if (j != length - 1 && nums[j] == nums[j + 1]) {
                    continue;
                }
                int L = i + 1;
                int R = j - 1;
                int cur = nums[i] + nums[j];
                int remainder = target - cur;
                while (L < R) {
                    if (L != i + 1 && nums[L] == nums[L - 1]) {
                        L++;
                        continue;
                    }
                    if (R != j - 1 && nums[R] == nums[R + 1]) {
                        R--;
                        continue;
                    }
                    int temp = nums[L] + nums[R];
                    if (temp == remainder) {
                        result.add(List.of(nums[i], nums[L], nums[R], nums[j]));
                        L++;
                        R--;
                    } else if (temp < remainder) {
                        L++;
                    } else {
                        R--;
                    }
                }
            }
        }
        return result;
    }
}