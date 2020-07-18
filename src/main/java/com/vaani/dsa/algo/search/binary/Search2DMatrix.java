package com.vaani.dsa.algo.search.binary;

/* https://leetcode.com/problems/search-a-2d-matrix/
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.
*/

public class Search2DMatrix {
    // assume a single large array - as each row can be concatenated after other
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        boolean found = false;

        int low = 0, high = m * n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int row = mid / n;
            int column = mid % n;
            if (row > m - 1 || column > n - 1) {
                break;
            }
            if (matrix[row][column] == target) {
                found = true;
                break;
            } else if (matrix[row][column] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return found;
    }
}
