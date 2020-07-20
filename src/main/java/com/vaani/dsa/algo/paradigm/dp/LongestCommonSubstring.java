package com.vaani.dsa.algo.paradigm.dp;

// https://youtu.be/Lj90FqNCIJE
// https://youtu.be/UZRkpGk943Q
// https://leetcode.com/problems/maximum-length-of-repeated-subarray/discuss/413054/4-different-ways-to-solve-Longest-Common-SubString-problem
public class LongestCommonSubstring {
    // Method1()- recursive solution(Top- down approach)
    // time complexity - O(3^(m+n))
    // space complexity - O(m+n)
    public static int LCSubStrM1(char[] X, char[] Y, int m, int n, int lcsCount) {
        if (m <= 0 || n <= 0)
            return lcsCount;

        int lcsCount1 = lcsCount;
        if (X[m - 1] == Y[n - 1])
            lcsCount1 = LCSubStrM1(X, Y, m - 1, n - 1, lcsCount + 1);

        int lcsCount2 = LCSubStrM1(X, Y, m, n - 1, 0);
        int lcsCount3 = LCSubStrM1(X, Y, m - 1, n, 0);

        return Math.max(lcsCount1, Math.max(lcsCount2, lcsCount3));
    }


    // Method2A1()- recursive solution with memoization (Top-down approach caching on method level)
    public static int LCSubStrM2A1(char[] X, char[] Y, int m, int n, int lcsCount, Integer[][][] dp) {
        if (m <= 0 || n <= 0)
            return lcsCount;

        if (dp[m][n][lcsCount] != null)
            return dp[m][n][lcsCount];

        int lcsCount1 = lcsCount;
        if (X[m - 1] == Y[n - 1])
            lcsCount1 = LCSubStrM2A1(X, Y, m - 1, n - 1, lcsCount + 1, dp);

        int lcsCount2 = LCSubStrM2A1(X, Y, m, n - 1, 0, dp);
        int lcsCount3 = LCSubStrM2A1(X, Y, m - 1, n, 0, dp);

        return dp[m][n][lcsCount] = Math.max(lcsCount1, Math.max(lcsCount2, lcsCount3));
    }

    // Method2A2()- recursive solution with memoization (Top-down approach caching on global level)
    public static int LCSubStrM2A2(char[] X, char[] Y, int m, int n, int lcsCount, Integer[][][] dp) {
        if (m <= 0 || n <= 0)
            return lcsCount;

        if (dp[m][n][lcsCount] != null)
            return dp[m][n][lcsCount];

        int lcsCount1 = lcsCount;
        if (X[m - 1] == Y[n - 1])
            lcsCount1 = LCSubStrM2A2(X, Y, m - 1, n - 1, lcsCount + 1, dp);

        int lcsCount2 = LCSubStrM2A2(X, Y, m, n - 1, 0, dp);
        int lcsCount3 = LCSubStrM2A2(X, Y, m - 1, n, 0, dp);

        return dp[m][n][lcsCount] = Math.max(lcsCount1, Math.max(lcsCount2, lcsCount3));
    }

    // Method3()- DP solution(Bottom up approach)
    // time complexity - O(m*n)
    // space complexity - O(m*n)
    public static int LCSubStrA3(String X, String Y, int m, int n) {
        int[][] memo = new int[m + 1][n + 1]; // 0th row and column are 0, if any substring is 0 len, maxLen = 0
        int result = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                // as dp array is 1 based, we are checking ith and jth char. But we write it as i-1, j-1
               if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    memo[i][j] = memo[i - 1][j - 1] + 1;// previous chars matched + 1
                    result = Math.max(result, memo[i][j]);
                } else {
                    memo[i][j] = 0;
                }
            }
        }
        return result;
    }
}
