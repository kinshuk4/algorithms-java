package com.vaani.dsa.algo.ds.array.subsequence;

/**
 * Given an array sequence [A1, A2 …An], the task is to find the maximum possible sum of increasing subsequence S of length k such that S1<=S2<=S3………<=Sk.
 * <p>
 * Examples:
 * <p>
 * Input :
 * n = 8 k = 3
 * A=[8 5 9 10 5 6 21 8]
 * Output : 40
 * Possible Increasing subsequence of Length 3 with maximum possible sum is 9 10 21
 * <p>
 * Input :
 * n = 9 k = 4
 * A=[2 5 3 9 15 33 6 18 20]
 * Output : 62
 * Possible Increasing subsequence of Length 4 with maximum possible sum is 9 15 18 20
 */
public class MaxSumIncreasingSubsequenceOfSizeK {

    // DP

    /**
     * dp[i][1]=arr[i] for length 1 , maximum icreasing subsequence is equal to the array value
     * dp[i][l+1]= max(dp[i][l+1], dp[j][l]+arr[i]) for any length l between 1 to k-1
     */
    static int MaxIncreasingSub(int[] arr, int k) {
        int n = arr.length;
        // In the implementation dp[n][k] represents
        // maximum sum subsequence of length k and the
        // subsequence is ending at index n.
        int[][] dp = new int[n][k + 1];
        int ans = -1;

        // Initializing whole multidimensional
        // dp array with value -1
        for (int i = 0; i < n; i++)
            for (int j = 0; j < k + 1; j++) {
                dp[i][j] = -1;
            }

        // For each ith position increasing subsequence
        // of length 1 is equal to that array ith value
        // so initializing dp[i][1] with that array value
        for (int i = 0; i < n; i++) {
            dp[i][1] = arr[i];
        }

        // Starting from 1st index as we have calculated
        // for 0th index. Computing optimized dp values
        // in bottom-up manner
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {

                // check for increasing subsequence
                if (arr[j] < arr[i]) {
                    for (int l = 1; l <= k - 1; l++) {

                        // Proceed if value is pre calculated
                        if (dp[j][l] != -1) {

                            // Check for all the subsequences
                            // ending at any j<i and try including
                            // element at index i in them for
                            // some length l. Update the maximum
                            // value for every length.
                            dp[i][l + 1] = Math.max(dp[i][l + 1],
                                    dp[j][l] + arr[i]);
                        }
                    }
                }
            }
        }

        // The final result would be the maximum
        // value of dp[i][k] for all different i.
        for (int i = 0; i < n; i++) {
            if (ans < dp[i][k]) {
                ans = dp[i][k];
            }
        }

        // When no subsequence of length k is
        // possible sum would be considered zero
        return (ans == -1) ? 0 : ans;
    }

    // Driver code
    public static void main(String[] args) {
        int n = 8, k = 3;
        int[] arr = {8, 5, 9, 10, 5, 6, 21, 8};
        int ans = MaxIncreasingSub(arr, k);
        System.out.println(ans);

    }

}
