package com.vaani.dsa.algo.paradigm.backtracking;

// https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/

import java.util.Arrays;

public class SubsetSumMinDifference {

    public static int findMinRecHelper(int[] arr, int i, int sumCalculated, int sumTotal) {
        // If we have reached last element.  Sum of one
        // subset is sumCalculated, sum of other subset is
        // sumTotal-sumCalculated.  Return absolute difference
        // of two sums.
        if (i == 0)
            return Math.abs((sumTotal - sumCalculated) - sumCalculated);


        // For every item arr[i], we have two choices
        // (1) We do not include it first set
        // (2) We include it in first set
        // We return minimum of two choices
        return Math.min(
                findMinRecHelper(arr, i - 1, sumCalculated + arr[i - 1], sumTotal),
                findMinRecHelper(arr, i - 1, sumCalculated, sumTotal));
    }

    // Returns minimum possible difference between sums of two subsets
    // https://www.youtube.com/watch?v=-GtpxG6l_Mc
    // Let S1, S2 be 2 subset, S1 + S2 = sum, S1 - S2 = min, min + sum = 2S1 => min = sum - 2S1
    int findMinRecursive(int[] arr, int n) {
        // Compute total sum of elements
        int sumTotal = Arrays.stream(arr).sum();

        // Compute result using recursive function
        return findMinRecHelper(arr, n, 0, sumTotal);
    }

    public static int findMinDP(int[] arr, int n) {
        // Calculate sum of all elements
        int sum = Arrays.stream(arr).sum();
        ;

        // Create an array to store results of subproblems
        boolean[][] dp = new boolean[n + 1][sum + 1];

        // Initialize first column as true. 0 sum is possible with all elements.
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Initialize top row, except dp[0][0], as false. With
        // 0 elements, no other sum except 0 is possible
        for (int i = 1; i <= sum; i++) {
            dp[0][i] = false;
        }

        // Fill the partition table in bottom up manner
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                // If i'th element is excluded
                dp[i][j] = dp[i - 1][j];

                // If i'th element is included
                if (arr[i - 1] <= j)
                    dp[i][j] |= dp[i - 1][j - arr[i - 1]];
            }
        }

        // Initialize difference of two sums.
        int diff = Integer.MAX_VALUE;

        // Find the largest j such that dp[n][j]
        // is true where j loops from sum/2 t0 0
        for (int j = sum / 2; j >= 0; j--) {
            // Find the
            if (dp[n][j]) {
                diff = sum - 2 * j;
                break;
            }
        }
        return diff;
    }
}
