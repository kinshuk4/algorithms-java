package com.vaani.dsa.algo.paradigm.backtracking;

public class IsSubsetSum {
    public static void main(String[] args) {
        System.out.println(new IsSubsetSum().isSubsetSumRecursive(new int[]{1, 2, 3}, 5));
    }

    public static boolean isSubsetSumRecursive(int[] arr, int sum) {
        if (sum == 0) {
            return true;
        }


        if (arr == null || arr.length == 0) {
            return false;
        }
        int n = arr.length;

        return isSubsetSumHelper(n - 1, arr, sum) || isSubsetSumHelper(n - 1, arr, sum - arr[n - 1]);
    }

    private static boolean isSubsetSumHelper(int index, int[] arr, int sum) {
        // Base Cases
        if (sum == 0) {
            return true;
        }
        if (index == 0) {
            return false;
        }

        // If last element is greater than sum, then ignore it
        if (arr[index - 1] > sum) {
            return isSubsetSumHelper(index - 1, arr, sum);
        }

        /* else, check if sum can be obtained by any of the following
            (a) including the last element
            (b) excluding the last element */
        return isSubsetSumHelper(index - 1, arr, sum) || isSubsetSumHelper(index - 1, arr, sum - arr[index - 1]);
    }

    public static boolean isSubsetSumDP(int[] arr, int sum) {
        int n = arr.length;
        // The value of DP[i][j] will be true if there is a subset of set[0..j-1] with sum equal to i
        // --> sum
        // | -> index of elements
        boolean[][] dp = new boolean[n][sum + 1];

        // If sum is 0, then answer is true
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        // if values are equal to jth sum, it is 1 way
        for (int j = 0; j <= sum; j++) {
            if (arr[0] == j) {
                dp[0][j] = true;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= sum; j++) {

                boolean includingCurrentValue = false;

                if (arr[i] <= j) {
                    includingCurrentValue = dp[i - 1][j - arr[i]];
                }

                boolean excludingCurrentValue = dp[i - 1][j];
                // including + exclusing arr[i]
                dp[i][j] = includingCurrentValue || excludingCurrentValue;
            }
        }

        return dp[n - 1][sum];
    }

    // small difference of indexing 0 sum value or not
    public static boolean isSubsetSumDP2(int[] arr, int sum) {
        int n = arr.length;
        // The value of DP[i][j] will be true if there is a subset of set[0..j-1] with sum equal to i
        // --> sum
        // | -> index of elements
        boolean[][] dp = new boolean[n + 1][sum + 1];

        // If sum is 0, then answer is true
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // If sum is not 0 and set is empty, then answer is false
        for (int i = 1; i <= sum; i++) {
            dp[0][i] = false;
        }

        // Fill the subset table in botton up manner
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {

                if (j < arr[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                }
                if (j >= arr[i - 1]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                }
            }
        }

	/* // uncomment this code to print table
	for (int i = 0; i <= n; i++)
	{
	for (int j = 0; j <= sum; j++)
		printf ("%4d", subset[i][j]);
	printf("\n");
	}*/

        return dp[n][sum];

    }
}
