package com.vaani.dsa.algo.paradigm.backtracking.nqueen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/n-queens/
 * 51. N-Queens
 * Hard
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * <p>
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * <p>
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 * <p>
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 * <p>
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
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */

public class NQueens {

    public static void main(String[] args) {
        new NQueens.UsingDFS1().solveNQueens(8).stream().forEach(strings -> System.out.println(strings));
    }

    static class UsingDFS1 {
        public List<List<String>> solveNQueens(int n) {
            List<List<String>> result = new ArrayList<>();
            helper(0, new int[n], result);
            return result;
        }

        public void helper(int curr, int[] row, List<List<String>> result) {
            int n = row.length;
            if (curr == n) {
                result.add(generateSol(row));
                return;
            }
            for (int i = 0; i < n; i++) {
                boolean ok = true;
                row[curr] = i;
                for (int j = 0; j < curr; j++) {
                    if (row[curr] == row[j] || curr - row[curr] == j - row[j] || curr + row[curr] == j + row[j]) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    helper(curr + 1, row, result);
                }
            }

        }

        public List<String> generateSol(int[] row) {
            int n = row.length;
            String[] sol = new String[n];
            for (int i = 0; i < n; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < n; j++)
                    if (j == row[i]) {
                        line.append("Q");
                    } else {
                        line.append(".");
                    }
                sol[i] = line.toString();
            }
            return Arrays.asList(sol);
        }
    }


    static class UsingDFS2 {
        /**
         * https://leetcode.com/problems/n-queens/discuss/19945/*Java*-When-you-are-familiar-with-backtracking-you'll-find-this-solution-straightforward-(5ms-beats-84)
         * Put Q in the board column after column starting from the first column (col=1)
         * Check if it is safe to put Q at current location (row, col)
         * If safe, put Q at (row, col) (greedy part) and move to next column and check if safe
         * If no row in next column is safe, meaning we can not put Q at (row, col), so remove Q (backtracking part) and continue, i.e., check (row+1, col) if row<N or remove Q at previous column, etc.
         * If col gets to N, meaning we are done filling all N queens. In this case, add board into res. And return false so that it will backtrack as illustrated in the last step.
         * keep running till exhausted, i.e., backtracked to last row, first column
         */
        public List<List<String>> solveNQueens(int N) {
            List<List<String>> result = new ArrayList<>();
            if (N < 1) {
                return result;
            }
            char[][] board = new char[N][N];
            for (char[] row : board) {
                for (int j = 0; j < N; j++) {
                    row[j] = '.';
                }
            }
            solve(board, N, 0, result);
            return result;
        }

        private boolean solve(char[][] board, int N, int col, List<List<String>> res) {
            if (col == N) { // done soving, simply add the board into the result
                List<String> list = new ArrayList<String>();
                for (int i = 0; i < N; i++) {
                    list.add(String.valueOf(board[i]));
                }
                res.add(list);
                return false; // return false so will backtrack
            }
            for (int i = 0; i < N; i++) {
                if (isSafe(board, N, i, col)) {
                    board[i][col] = 'Q'; // greedy
                    if (solve(board, N, col + 1, res)) {
                        return true;
                    } else {
                        board[i][col] = '.'; // backtrack
                    }
                }
            }
            return false;
        }

        private boolean isSafe(char[][] board, int N, int row, int col) {
            for (int i = 0; i < N; i++) {
                if (board[i][col] != '.') return false;
                if (board[row][i] != '.') return false;
            }
            int step = 1;
            while (row - step >= 0 && col - step >= 0)
                if (board[row - step][col - step++] != '.') return false;
            step = 1;
            while (row + step < N && col - step >= 0)
                if (board[row + step][col - step++] != '.') return false;
            return true;
        }
    }


}
