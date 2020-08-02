package com.vaani.dsa.algo.ds.array;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/majority-element-ii/
 * 229. Majority Element II
 * Medium
 * Given an array of integers, the majority number is the number that occurs more than 1/3 of the size of the array.
 * <p>
 * Find it.
 * <p>
 * Note
 * There is only one majority number in the array
 * <p>
 * Example
 * For [1, 2, 1, 2, 1, 3, 3] return 1
 * <p>
 * Challenge
 * O(n) time and O(1) space
 */
public class MajorityElement2 {

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();

        Integer n1 = null, n2 = null;
        int c1 = 0, c2 = 0;

        for (int i : nums) {
            if (n1 != null && i == n1) {
                c1++;
            } else if (n2 != null && i == n2) {
                c2++;
            } else if (c1 == 0) {
                c1 = 1;
                n1 = i;
            } else if (c2 == 0) {
                c2 = 1;
                n2 = i;
            } else {
                c1--;
                c2--;
            }
        }

        c1 = c2 = 0;

        for (int i : nums) {
            if (i == n1) {
                c1++;
            } else if (i == n2.intValue()) {
                c2++;
            }
        }

        if (c1 > nums.length / 3) {
            result.add(n1);
        }
        if (c2 > nums.length / 3) {
            result.add(n2);
        }

        return result;
    }

    // submitted
    public List<Integer> majorityNumber2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        if (nums.length == 1) {
            return List.of(nums[0]);
        }

        int n = nums.length;
        int n1 = nums[0];
        int c1 = 1;
        int i = 1;
        while (i < n && nums[i] == n1) {
            i++;
            c1++;
        }


        // List only contains n1
        if (i == n) {
            return List.of(n1);
        }

        int n2 = nums[i];
        int c2 = 1;
        i++;
        while (i < n) {
            int num = nums[i];
            if (num == n1) {
                c1++;
            } else if (num == n2) {
                c2++;
            } else if (c1 == 0) {
                n1 = num;
                c1 = 1;
            } else if (c2 == 0) {
                n2 = num;
                c2 = 1;
            } else {
                c1--;
                c2--;
            }
            i++;
        }

        c1 = 0;
        c2 = 0;
        for (int num : nums) {
            if (num == n1) {
                c1++;
            }
            if (num == n2) {
                c2++;
            }
        }
        List<Integer> result = new ArrayList<>();
        int k = n / 3;
        if (c1 > k) {
            result.add(n1);
        }

        if (c2 > k) {
            result.add(n2);
        }
        return result;
    }
}
