package com.vaani.dsa.algo.paradigm.dp;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/
 * 02/04/2017. Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * <p>For example, Given [10, 9, 2, 5, 3, 7, 101, 18], The longest increasing subsequence is [2, 3,
 * 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is
 * only necessary for you to return the length.
 *
 * <p>Your algorithm should run in O(n^2) complexity.
 *
 * <p>Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) throws Exception {
        int[] nums = {9, 8, 7, 6};
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS1(nums));
    }

    public int lengthOfLIS1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] A = new int[nums.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0, l = nums.length; i < l; i++) {
            int lis = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) lis = Math.max(lis, A[j] + 1);
            }
            A[i] = lis;
            max = Math.max(max, A[i]);
        }
        return max;
    }

    public static int lengthOfLISDP(int[] arr) {
        if (arr == null || arr.length == 0){
            return 0;
        }
        int n = arr.length;
        int[] dp = new int[n];

        dp[0] = 1;
        int max = 1; // a number is LIS itself
        /* Compute optimized LIS values in bottom up manner */
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++)
                if (arr[i] > arr[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    max = Math.max(max, dp[i]);
                }
        }
        return max;
    }
}
