package com.vaani.dsa.algo.paradigm.dp;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-path-sum/
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * <p/>
 * Note: You can only move either down or right at any point in time.
 * <p/>
 * Example:
 * <p>
 * Input:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */

public class Minimum_PathSum_CostPath_InMatrix {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        // result 7
        DijkstraPathSum(grid);
        minPahtSum(grid);


        int[][] a2 = new int[][]{{3}};
        //3
        DijkstraPathSum(a2);
        minPahtSum(a2);


        int[][] a3 = new int[][]{
                {1, 0, 4, 9, 6, 0, 9, 1, 8, 9, 5},
                {1, 2, 8, 9, 2, 4, 8, 1, 7, 3, 2},
                {5, 0, 7, 9, 3, 5, 1, 3, 8, 2, 3},
                {3, 2, 2, 5, 3, 3, 3, 2, 0, 5, 6},
                {9, 6, 8, 3, 6, 2, 0, 1, 4, 6, 1},
                {1, 7, 4, 8, 8, 9, 7, 1, 3, 2, 5},
                {7, 7, 8, 0, 3, 0, 0, 0, 8, 1, 8},
                {8, 7, 4, 0, 9, 5, 4, 7, 9, 8, 5},
                {5, 6, 3, 5, 5, 6, 0, 7, 1, 7, 7},
                {9, 9, 2, 1, 1, 2, 1, 5, 0, 0, 4}
        };
        //40
        DijkstraPathSum(a3);
        minPahtSum(a3);


    }


    // only left or down, Dynamic programming to solve this.
    public static void minPahtSum(int[][] grid) {
        if (grid.length == 0) {
            System.out.println("sum path for zero grid " + 0);
            return;
        }

        int row = grid.length;
        int col = grid[0].length;

        // calculate left edge
        for (int i = row - 1; i > 0; --i)
            grid[i - 1][col - 1] += grid[i][col - 1];

        // calculate bottom edge
        for (int j = col - 1; j > 0; --j)
            grid[row - 1][j - 1] += grid[row - 1][j];

        for (int i = row - 2; i >= 0; --i) {
            for (int j = col - 2; j >= 0; --j) {
                //sum with the less one
                grid[i][j] +=
                        (grid[i + 1][j] < grid[i][j + 1]) ?
                                grid[i + 1][j] : grid[i][j + 1];
            }
        }

        System.out.println(grid[0][0]);

    }

    // Dijkstra's algorithm

    /**
     * If we could move to any direction in the path, then we kind of use
     * Dijkstra's algorithm to solve this problem. The difference with the
     * shortest path problem in graph is that in the graph, different node
     * to one node may have path with different weight. But int the grid,
     * each neighbor to this cell would sum with the same value. So if we
     * would also get minimun sum in fisrt calculate for this cell.
     * <p/>
     * Since the question ask to move either right or down only, then the
     * question merely become Robort move question. With dynamic programming
     * we could solve it in O(mn) time complexity.
     */
    public static void DijkstraPathSum(int[][] grid) {
        if (grid.length == 0) {
            System.out.println("sum path for zero grid " + 0);
            return;
        }

        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visit = new boolean[row][col];
        int[][] cost = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cost[i][j] = Integer.MAX_VALUE;
            }
        }

        String[][] path = new String[row][col];

        cost[0][0] = grid[0][0];
        path[0][0] = "0-0";
        visit[0][0] = true;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col && visit[i][j]; j++) {

                //left
                int r = i + 1, c = j;

                if (r < row) {
                    if (cost[r][c] > cost[i][j] + grid[r][c]) {
                        cost[r][c] = cost[i][j] + grid[r][c];
                        visit[r][c] = true;
                        path[r][c] = String.valueOf(i) + "-" + String.valueOf(j);
                    }

                }
                // down
                r = i;
                c = j + 1;
                if (c < col) {
                    if (cost[r][c] > cost[i][j] + grid[r][c]) {
                        cost[r][c] = cost[i][j] + grid[r][c];
                        visit[r][c] = true;
                        path[r][c] = String.valueOf(i) + "-" + String.valueOf(j);
                    }

                }
            }
        }

        System.out.println(cost[row - 1][col - 1]);
        // System.out.println(Arrays.deepToString(cost));
    }

    public int minPathSumDP(int[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        int[][] dp = new int[m][n];
//        sum = grid.clone();

        //initialize the first row and first column
        // we move from left to right from 0,0
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        // we move down from 0,0
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        //build the rest using the base columns and rows
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int left = dp[i][j - 1];
                int up = dp[i - 1][j];

                int preSum = grid[i][j] + Math.min(left, up);
                dp[i][j] += preSum;
            }
        }
        return dp[m - 1][n - 1];
    }


    // very slow exponential
    public int minPathSumBacktracking(int[][] grid) {

        return minPathSum(grid, 0, 0, 0);

    }

    private int minPathSum(int[][] grid, int x, int y, int currSum) {
        int m = grid.length;
        int n = grid[0].length;

        if (x == m - 1 && y == n - 1) {
            return grid[x][y] + currSum;
        }

        if (canMove(x, y, m, n)) {
            int item = grid[x][y];
            return Math.min(minPathSum(grid, x, y + 1, currSum + item), minPathSum(grid, x + 1, y, currSum + item));
        }

        return Integer.MAX_VALUE;
    }

    private boolean canMove(int x, int y, int m, int n) {
        // check if x and y are in limits and cell is not blocked
        if (x >= 0 && y >= 0 && x < m && y < n) {
            return true;
        }
        return false;
    }

    // fails because of wrong answer for
    // [[1,4,8,6,2,2,1,7],[4,7,3,1,4,5,5,1],[8,8,2,1,1,8,0,1],[8,9,2,9,8,0,8,9],[5,7,5,7,1,8,5,5],[7,0,9,4,5,6,5,6],[4,9,9,7,9,1,9,0]]
    // output: 53, expected: 47
    public int minPathSumBacktrackingMemoization(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] memo = new int[m][n];
        for (int[] memoItem : memo) {
            Arrays.fill(memoItem, Integer.MAX_VALUE);
        }

        return minPathSumMemo(grid, 0, 0, 0, memo);

    }

    private int minPathSumMemo(int[][] grid, int x, int y, int currSum, int[][] memo) {
        int m = grid.length;
        int n = grid[0].length;

        if (x == m - 1 && y == n - 1) {
            memo[x][y] = grid[x][y] + currSum;
            return memo[x][y];
        }

        if (canMove(x, y, m, n)) {
            if (memo[x][y] != Integer.MAX_VALUE) {
                return memo[x][y];
            }
            int item = grid[x][y];
            memo[x][y] = Math.min(minPathSumMemo(grid, x, y + 1, currSum + item, memo), minPathSumMemo(grid, x + 1, y, currSum + item, memo));
            return memo[x][y];
        }

        return Integer.MAX_VALUE;
    }

}
