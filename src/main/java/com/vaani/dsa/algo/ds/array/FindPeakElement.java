package com.vaani.dsa.algo.ds.array;

import org.junit.Assert;

/**
 * A peak element is an element that is greater than its neighbors.
 * <p>
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * <p>
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * <p>
 * You may imagine that num[-1] = num[n] = -∞.
 * <p>
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 */
public class FindPeakElement {
    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 1};
        int[] nums2 = {1, 2, 3, 4};
        FindPeakElement test = new FindPeakElement();
        Assert.assertEquals(0, test.findPeakElement(nums)); // as left side is -∞
        Assert.assertEquals(3, test.findPeakElement(nums2)); // as right side -∞
    }

    /**
     * Binary Search: O(logN)
     * <p>
     * http://www.geeksforgeeks.org/find-a-peak-in-a-given-array/
     */
    public int findPeakElement(int[] nums) {
        return findPeakElement(nums, 0, nums.length - 1);
    }

    public int findPeakElement(int[] nums, int start, int end) {

        int mid = start + (end - start) / 2;

        if ((mid == 0 || nums[mid - 1] < nums[mid])
                && (mid == nums.length - 1 || nums[mid + 1] < nums[mid])) {
            return mid;
        }

        if (mid > 0 && nums[mid - 1] > nums[mid]) {
            return findPeakElement(nums, start, mid - 1);
        } else {
            return findPeakElement(nums, mid + 1, end);
        }
    }

    // submitted
    public int findPeakElement2(int[]  nums) {
        return helper2( nums, 0,  nums.length - 1);
    }

    public int helper2(int[] nums, int start, int end) {
        if (start == end) {
            return start;
        } else if (start + 1 == end) {
            if (nums[start] > nums[end]) return start;
            return end;
        } else {
            int mid = start + (end - start) / 2;

            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid - 1] > nums[mid] && nums[mid] > nums[mid + 1]) { // increasing order to the right, search on left
                return helper2(nums, start, mid - 1);
            } else {
                return helper2(nums, mid + 1, end);
            }
        }
    }

}
