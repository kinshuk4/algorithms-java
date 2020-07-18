package com.vaani.dsa.algo.ds.array.matrix;

/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
*/

public class RotateImage {
    public void rotate1(int[][] matrix) {
        // Note: The Solution object is instantiated only once and is reused by each test case.

        int length = matrix.length;
        if (length == 0) {
            return;
        }

        for (int layer = 0; layer < length / 2; layer++) {

            for (int offset = layer; offset < length - layer - 1; offset++) {
                //Store the top
                int temp = matrix[layer][offset];

                //left -> top
                matrix[layer][offset] = matrix[length - offset - 1][layer];

                //down -> left
                matrix[length - offset - 1][layer] = matrix[length - layer - 1][length - 1 - offset];

                //right -> down
                matrix[length - layer - 1][length - 1 - offset] = matrix[offset][length - layer - 1];

                //top -> right
                matrix[offset][length - layer - 1] = temp;

            }
        }
    }

    public void rotate(int[][] matrix) {
        int N = matrix.length;
        for (int i = 0; i < N / 2; ++i) {
            for (int j = 0; j < (N + 1) / 2; ++j) {
                int temp = matrix[i][j];  // save the top element;
                int u = N - 1 - i;
                int v = N - 1 - j;
                matrix[i][j] = matrix[v][i];  // right -> top;
                matrix[v][i] = matrix[u][v]; // bottom -> right;
                matrix[u][v] = matrix[j][u];// left -> bottom;
                matrix[j][u] = temp;                // top -> left;
            }
        }
    }
}
