package com.vaani.dsa.algo.paradigm.dp;

/**
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example 1:
 * Input:
 * <p>
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb".
 * <p>
 * <p>
 * Example 2:
 * Input:
 * <p>
 * "cbbd"
 * Output:
 * 2
 * One possible longest palindromic subsequence is "bb".
 */
public class LongestPalindromicSubsequence {
    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println(new LongestPalindromicSubsequence().longestPalindromeSubseq("bbbab"));
    }

    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length() + 1][s.length() + 1];
        String sI = new StringBuilder(s).reverse().toString();
        for (int i = 1, l = s.length(); i <= l; i++)
            for (int j = 1; j <= l; j++) {
                dp[i][j] = (s.charAt(i - 1) == sI.charAt(j - 1))
                        ? dp[i - 1][j - 1] + 1
                        : Math.max(dp[i - 1][j], dp[i][j - 1]);
            }

        return dp[s.length()][s.length()];
    }
}
