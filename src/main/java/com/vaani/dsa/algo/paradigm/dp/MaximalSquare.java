package com.vaani.dsa.algo.paradigm.dp;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximal-square/
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 * <p>For example, given the following matrix:
 * <p>
 * Example:
 * <p>
 * Input:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * Output: 4
 */
public class MaximalSquare {
    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        char[][] A = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(new MaximalSquare().maximalSquare(A));
    }

    //  Solution: O(n * m) time and space complexity. Calculate the max length of a square using
    //  DP(i,j) = min(DP[i - 1][j], DP[i][j - 1], DP[i - 1][j - 1]) + 1 Return the square of the answer.
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j - 1 >= 0 && i - 1 >= 0) {
                    if (matrix[i][j] == '1') {
                        // all the 3 directions - top, left, diagonal
                        dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i-1][j-1]));
                    }
                } else {
                    dp[i][j] = matrix[i][j] == '1' ? 1 : 0;
                }
            }
        }
        int max = 0;
        for (int[] ints : dp) {
            max = Math.max(max, Arrays.stream(ints).max().orElse(0));
        }
        return max * max;
    }
}
