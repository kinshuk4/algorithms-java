package com.vaani.dsa.algo.paradigm.backtracking;

public class CountSubsetSumEqualK {

    public static int countSubsetDP(int[] arr, int sum) {
        int[][] dp = new int[arr.length][sum + 1];

        int n = arr.length;
        // for sum = 0, we just exclude each element, hence 1 way
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        // if values are equal to jth sum, it is 1 way
        for (int j = 0; j <= sum; j++) {
            if (arr[0] == j) {
                dp[0][j] = 1;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= sum; j++) {
                int includingCurrentValue = 0;

                if (arr[i] <= j) {
                    includingCurrentValue = dp[i - 1][j - arr[i]];
                }

                int excludingCurrentValue = dp[i - 1][j];
                // including + exclusing arr[i]
                dp[i][j] = includingCurrentValue + excludingCurrentValue;
            }
        }
        return dp[n - 1][sum];
    }

    // Reuse subset sum
    // let S be Set. S1 - S2 = min. S1+S2 = sum, min + sum = 2S1 => min = sum - 2S1


}
