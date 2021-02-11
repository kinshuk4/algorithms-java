package com.vaani.dsa.algo.misc;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/next-permutation/
 * 31. Next Permutation
 * Medium
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * <p>
 * If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
 * <p>
 * The replacement must be in place and use only constant extra memory.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * Example 2:
 * <p>
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * Example 3:
 * <p>
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 * Example 4:
 * <p>
 * Input: nums = [1]
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 */


public class NextPermutation {

    //from right to left, find the first number that violates the increasing trend --> Partition Number
    //from right to left, find the first number that is bigger than Partition Number --> Change Number
    //swap Partition and Change Number
    //reverse all the number on the right side of the partition index
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n == 0 || n == 1) {
            return;
        }

        // find first value smaller than right neighbour
        int partitionIdx = n;
        for (int i = n - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                partitionIdx = i - 1;
                break;
            }
        }

        if (partitionIdx == n) {
            Arrays.sort(nums);
            return;
        }

        //find the smallest value after partition index, then swap with it
        int min = Integer.MAX_VALUE;
        int swapIdx= n;
        for (int i = partitionIdx + 1; i < n; i++) {
            if(nums[i] < min && nums[i] > nums[partitionIdx]){
                min = nums[i];
                swapIdx = i;
            }
        }
        int temp = nums[partitionIdx];
        nums[partitionIdx] = nums[swapIdx];
        nums[swapIdx] = temp;

        //sort array after  partitionIdx
        Arrays.sort(nums, partitionIdx+1, nums.length);
    }
}
