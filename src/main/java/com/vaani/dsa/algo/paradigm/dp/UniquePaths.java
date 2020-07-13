package com.vaani.dsa.algo.paradigm.dp;

public class UniquePaths {

    //recursive, exceeds time limit
    public int uniquePathsRecursive(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        } else {
            return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
        }
    }

    public int uniquePathsDP(int m, int n) {

        int[][] path = new int[m][n];
        //initialize
        for (int i = 0; i < m; i++) {
            path[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            path[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                path[i][j] = path[i - 1][j] + path[i][j - 1];
            }
        }

        return path[m - 1][n - 1];
    }

    public int uniquePaths(int m, int n) {
        //math approach
        //in total m + n steps, choose m out of m + n
        double result = 1;
        int bigger = Math.max(m - 1, n - 1);
        for (int i = m + n - 2; i > bigger; i--) {
            result = result * i / (i - bigger);
        }
        return (int) (result + 0.5);
    }
}
