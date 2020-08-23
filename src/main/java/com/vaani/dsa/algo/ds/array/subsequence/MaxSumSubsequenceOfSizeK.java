package com.vaani.dsa.algo.ds.array.subsequence;


import java.util.Arrays;

public class MaxSumSubsequenceOfSizeK {

    // DP

    /**
     * dp[i][1]=arr[i] for length 1 , maximum icreasing subsequence is equal to the array value
     * dp[i][l+1]= max(dp[i][l+1], dp[j][l]+arr[i]) for any length l between 1 to k-1
     */
    static int MaxIncreasingSub(int[] arr, int k) {
        Arrays.sort(arr);
        int n = arr.length;
        // In the implementation dp[n][k] represents
        // maximum sum subsequence of length k and the
        // subsequence is ending at index n.
        int[][] dp = new int[n][k + 1];

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

                for (int l = 1; l <= k - 1; l++) {
                    int currSum = dp[j][l] + arr[i];
                    // Proceed if value is pre calculated
                    if (currSum % 2 == 0 && dp[j][l] != -1) {

                        // Check for all the subsequences
                        // ending at any j<i and try including
                        // element at index i in them for
                        // some length l. Update the maximum
                        // value for every length.


                        dp[i][l + 1] = Math.max(dp[i][l + 1],
                                currSum);

                    }
                }

            }
        }

        int ans = -1;
        // The final result would be the maximum
        // value of dp[i][k] for all different i.
        for (int i = 0; i < n; i++) {
            if (dp[i][k] % 2 == 0 && dp[i][k] > ans)
                ans = dp[i][k];
        }

        // When no subsequence of length k is
        // possible sum would be considered zero
        return ans;
    }

    // Driver code
    public static void main(String[] args) {
        int k = 3;
        int[] arr = {2, 3, 3, 5, 5};
        Arrays.sort(arr);
        int ans = MaxIncreasingSub(arr, k);
        System.out.println(ans);

    }

}
