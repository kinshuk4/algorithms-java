package com.vaani.dsa.algo.ds.array.matrix;

import java.io.*;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class MaxHourglassSum {
    static int getSum(int[][] arr, int i, int j) {
        return arr[i][j] + arr[i][j + 1] + arr[i][j + 2]
                + arr[i + 1][j + 1] +
                arr[i + 2][j] + arr[i + 2][j + 1] + arr[i + 2][j + 2];
    }

    static int getSumWindow(int[][] arr, int i, int j, int currSum) {

        return currSum - arr[i][j - 1] + arr[i][j + 2]
                - arr[i + 1][j] + arr[i + 1][j + 1]
                - arr[i + 2][j - 1] + arr[i + 2][j + 2];
    }

    static int hourglassSum(int[][] arr) {
        int currSum = Integer.MIN_VALUE;
        int maxSum = currSum;
        int n = arr.length;
        int m = arr[0].length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                if (j == 0){
                    currSum = getSum(arr,i, j);
                }else {
                    currSum = getSumWindow(arr, i, j, currSum);
                }

                maxSum = Math.max(currSum, maxSum);
            }
        }
        return maxSum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int[][] arr = {
                {1, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0},
                {0, 0, 2, 4, 4, 0},
                {0, 0, 0, 2, 0, 0},
                {0, 0, 1, 2, 4, 0}
        };

        int result = hourglassSum(arr);
        assertEquals(result, 19);

    }
}
