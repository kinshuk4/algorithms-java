package com.vaani.dsa.algo.search.binary;


/* https://leetcode.com/problems/search-a-2d-matrix-ii/
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

    Integers in each row are sorted in ascending from left to right.
    Integers in each column are sorted in ascending from top to bottom.

 */
public class Search2DMatrix2 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        Pair returned = findInMatrix(matrix, 0, matrix.length - 1, 0, matrix[0].length - 1, target);
        return returned != null;
    }

    public static Pair findInMatrix(int[][] data, int rowLow, int rowHigh, int colLow, int colHigh, int element) {
        if (rowLow > rowHigh || colLow > colHigh) {
            return null;
        }

        int rowMid = rowLow + (rowHigh - rowLow) / 2;
        int colMid = colLow + (colHigh - colLow) / 2;

        int midElement = data[rowMid][colMid];
        if (midElement == element) {
            return new Pair(rowMid, colMid);
        } else if (element < midElement) {
            Pair topLeft = findInMatrix(data, rowLow, rowMid - 1, colLow, colMid - 1, element);
            if (topLeft == null) {
                Pair topRight = findInMatrix(data, rowLow, rowMid - 1, colMid, colHigh, element);
                if (topRight == null) {
                    Pair bottomLeft = findInMatrix(data, rowMid, rowHigh, colLow, colMid - 1, element);
                    return bottomLeft;
                } else {
                    return topRight;
                }
            } else {
                return topLeft;
            }
        } else { // element > mid
            Pair topRight = findInMatrix(data, rowLow, rowMid - 1, colMid + 1, colHigh, element);
            if (topRight == null) {
                Pair bottomLeft = findInMatrix(data, rowMid + 1, rowHigh, colLow, colMid, element);
                if (bottomLeft == null) {
                    Pair bottomRight = findInMatrix(data, rowMid, rowHigh, colMid + 1, colHigh, element);
                    return bottomRight;
                } else
                    return bottomLeft;
            } else
                return topRight;
        }
    }
}
