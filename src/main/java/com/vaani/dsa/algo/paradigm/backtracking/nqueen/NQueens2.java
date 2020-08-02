package com.vaani.dsa.algo.paradigm.backtracking.nqueen;

/**
 * https://leetcode.com/problems/n-queens-ii/
 * 52. N-Queens II
 * Hard
 * <p>
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * <p>
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 * <p>
 * Example:
 * <p>
 * Input: 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
 * [
 * [".Q..",  // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 */
public class NQueens2 {
    int count;

    public int totalNQueens(int n) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        count = 0;
        helper(0, new int[n]);
        return count;
    }

    public void helper(int cur, int[] row) {
        if (cur == row.length) {
            count++;
            return;
        }

        for (int i = 0; i < row.length; i++) {
            boolean ok = true;
            row[cur] = i;
            for (int j = 0; j < cur; j++) {
                if (row[cur] == row[j] || row[cur] - row[j] == cur - j || row[cur] - row[j] == j - cur) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                helper(cur + 1, row);
            }
        }
    }
}
