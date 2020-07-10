package com.vaani.dsa.algo.numerical.number;

import static com.vaani.dsa.algo.numerical.BinomialCoefficient.binomialCoefficientIterative;

public class CatalanNumber {

    //Time exponention
    public static int catalanRecursve(int n) {
        int result = 0;

        // Base case
        if (n <= 1) {
            return 1;
        }
        for (int i = 0; i < n; i++) {
            result += catalanRecursve(i) * catalanRecursve(n - i - 1);
        }
        return result;
    }

    // catalan DP = O(n^2)
    public static int catalanDP(int n) {
        int[] dp = new int[n + 1];

        // Initialize first two values in table
        dp[0] = dp[1] = 1;

        // Fill entries in catalan[] using recursive formula
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }

        // Return last entry
        return dp[n];
    }


    // A Binomial coefficient based function to find nth catalan number in O(n) time
    public static int catalanBinomial(int n) {
        // Calculate value of 2nCn
        long c = binomialCoefficientIterative(2 * n, n);

        // return 2nCn/(n+1)
        return (int) (c / (n + 1));
    }


}
