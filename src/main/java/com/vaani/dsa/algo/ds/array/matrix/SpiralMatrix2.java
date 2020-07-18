package com.vaani.dsa.algo.ds.array.matrix;

/* https://leetcode.com/problems/spiral-matrix-ii/
Given an integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/

public class SpiralMatrix2 {
    public int[][] generateMatrixRecursive(int n) {

        int[][] result = new int[n][n];
        if (n == 0) {
            return result;
        }
        return placement(0, 0, result, 1, n);
    }

    public int[][] placement(int row, int col, int[][] m, int number, int length) {

        while (number < length * length) {
            m[row][col] = number;
            //first right
            while (col < length - 1 && m[row][col + 1] == 0) {
                m[row][col++] = number++;
            }

            //down
            while (row < length - 1 && m[row + 1][col] == 0) {
                m[row++][col] = number++;

            }

            //left
            while (col > 0 && m[row][col - 1] == 0) {
                m[row][col--] = number++;
            }

            //up
            while (row > 0 && m[row - 1][col] == 0) {
                m[row--][col] = number++;
            }
        }
        m[row][col] = number;
        return m;

    }

    // submitted
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        if (matrix.length == 0) {
            return matrix;
        }

        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;
        int size = n * n;
        int k = 1;
        while (k <= size) {
            // Traverse Right
            for (int j = left; j <= right; j++) {
                matrix[top][j] = k++;
            }
            top++;

            // Traverse Down
            for (int j = top; j <= bottom; j++) {
                matrix[j][right] = k++;
            }
            right--;

            if (top <= bottom) {
                // Traverse Left
                for (int j = right; j >= left; j--) {
                    matrix[bottom][j] = k++;
                }
            }
            bottom--;

            if (left <= right) {
                // Traver Up
                for (int j = bottom; j >= top; j--) {
                    matrix[j][left] = k++;
                }
            }
            left++;
        }

        return matrix;
    }
}
