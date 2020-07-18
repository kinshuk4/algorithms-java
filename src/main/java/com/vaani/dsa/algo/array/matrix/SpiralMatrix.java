package com.vaani.dsa.algo.array.matrix;

import java.util.ArrayList;
import java.util.List;
/* https://leetcode.com/problems/spiral-matrix/
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
*/

public class SpiralMatrix {
    public List<Integer> spiralOrderRecursive(int[][] matrix) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int rowLength = matrix.length;
        List<Integer> results = new ArrayList<Integer>();
        if (rowLength == 0) {
            return results;
        }
        int columnLength = matrix[0].length;

        spiralOrderHelper(0, rowLength, columnLength, matrix, results);
        return results;
    }

    public void spiralOrderHelper(int layer, int row, int col, int[][] matrix, List<Integer> res) {
        if (row == 0 || col == 0) {
            return;
        } else if (row == 1) {
            for (int i = 0; i < col; i++) {
                res.add(matrix[layer][i + layer]);
            }
            return;
        } else if (col == 1) {
            for (int i = 0; i < row; i++) {
                res.add(matrix[i + layer][layer]);
            }
            return;
        } else {
            //add top to result
            for (int i = 0; i < col - 1; i++) {
                res.add(matrix[layer][i + layer]);
            }
            //add right to result
            for (int i = 0; i < row - 1; i++) {
                res.add(matrix[i + layer][col + layer - 1]);
            }
            //add botton to result
            for (int i = col - 1; i > 0; i--) {
                res.add(matrix[row + layer - 1][i + layer]);
            }
            //add left to result
            for (int i = row - 1; i > 0; i--) {
                res.add(matrix[i + layer][layer]);
            }

            spiralOrderHelper(layer + 1, row - 2, col - 2, matrix, res);
        }
    }

    // submitted
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();

        if (matrix.length == 0) {
            return result;
        }

        int top = 0;
        int bottom = matrix.length-1;
        int left = 0;
        int right = matrix[0].length - 1;
        int size = matrix.length * matrix[0].length;
        while (result.size() < size) {
            // Traverse Right
            for (int j = left; j <= right; j ++) {
                result.add(matrix[top][j]);
            }
            top++;

            // Traverse Down
            for (int j = top; j <= bottom; j ++) {
                result.add(matrix[j][right]);
            }
            right--;

            if (top <= bottom) {
                // Traverse Left
                for (int j = right; j >= left; j --) {
                    result.add(matrix[bottom][j]);
                }
            }
            bottom--;

            if (left <= right) {
                // Traver Up
                for (int j = bottom; j >= top; j --) {
                    result.add(matrix[j][left]);
                }
            }
            left ++;
        }

        return result;
    }
}
