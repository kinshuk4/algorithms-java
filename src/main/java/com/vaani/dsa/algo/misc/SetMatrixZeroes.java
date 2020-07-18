package com.vaani.dsa.algo.misc;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/set-matrix-zeroes/
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 * <p/>
 * Follow up:
 * Did you use extra space?
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */
public class SetMatrixZeroes {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3, 5},
                {2, 4, 0, 1},
                {2, 4, 2, 1},
                {0, 4, 2, 1},
        };

        System.out.println(Arrays.deepToString(matrix));
        setMatrixZero(matrix);
        System.out.println(Arrays.deepToString(matrix));

        matrix = new int[][]{
                {1, 2, 3, 5},
                {2, 4, 0, 1},
                {2, 4, 2, 1},
                {0, 4, 2, 1},
        };

        setZeros(matrix);
        System.out.println(Arrays.deepToString(matrix));

    }

    // do it in place
    // use first row/col to record zero flag
    public static void setMatrixZero(int[][] matrix) {
        if (matrix.length <= 0) {
            return;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        boolean firstRowZero = false;
        boolean firstColZero = false;

        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // Now use first row and first col to record the values
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    // set zero flag on the first row/col
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        // set zero based on zero flag
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstRowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        if (firstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }


    // need extra space O(m+n)
    //https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/discuss/87738/Java-20-lines-very-easy-solution-7ms-with-explanation
    public static void setZeros(int[][] matrix) {
        boolean[] row = new boolean[matrix.length];
        boolean[] column = new boolean[matrix[0].length];

        // Store the row and column index with value 0
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }

        // Set arr[i][j] to 0 if either row i or column j has a 0
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (row[i] || column[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

}
