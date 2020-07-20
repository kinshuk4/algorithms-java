package com.vaani.dsa.algo.paradigm.dp;

/**
 * The longest common subsequence (LCS) problem is the problem of finding the longest subsequence common to all sequences in a set of sequences (often just two sequences).
 * It differs from problems of finding common substrings: unlike substrings, subsequences are not required to occupy consecutive positions within the original sequences.
 * <p>
 * Examples:
 * LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
 * LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
 * <p>
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String S = "AGGTAB";
        String T = "GXTXAYB";

        System.out.println(findLcsDP(S, T));
        System.out.println(printLCS(S, T));
    }

    /**
     * Time(2 ^ n)
     */
    public static int findLcsRecursion(String S, String T) {
        return findLcsRecursion(S, T, S.length() - 1, T.length() - 1);
    }

    public static int findLcsRecursion(String S, String T, int m, int n) {
        if (m < 0 || n < 0) {
            return 0;
        } else if (S.charAt(m) == T.charAt(n)) {
            return findLcsRecursion(S, T, m - 1, n - 1) + 1;
        } else {
            return Math.max(findLcsRecursion(S, T, m, n - 1), findLcsRecursion(S, T, m - 1, n));
        }
    }

    /**
     * Time(m * n), Space (m * n)
     * Reference: http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
     */
    public static int findLcsDP(String S, String T) {
        int m = S.length();
        int n = T.length();
        int[][] dp = getLcsMemoArray(S, T);
        return dp[m][n];
    }

    public static int[][] getLcsMemoArray(String S, String T) {
        int m = S.length();
        int n = T.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = 0;
        }

        for (int j = 0; j < n + 1; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp;
    }


    /**
     * Reference: http://www.geeksforgeeks.org/printing-longest-common-subsequence/
     */
    public static String printLCS(String S, String T) {
        int m = S.length();
        int n = T.length();
        int[][] memo = getLcsMemoArray(S, T);

        StringBuilder result = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (S.charAt(i - 1) == T.charAt(j - 1)) {
                result.insert(0, S.charAt(i - 1));
                i--;
                j--;
            } else if (memo[i - 1][j] > memo[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return result.toString();
    }


}
