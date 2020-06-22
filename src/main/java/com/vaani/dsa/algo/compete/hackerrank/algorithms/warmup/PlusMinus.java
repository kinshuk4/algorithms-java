package com.vaani.dsa.algo.compete.hackerrank.algorithms.warmup;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 * Ref: Plus Minus exercise https://www.hackerrank.com/challenges/plus-minus/problem
 */
public class PlusMinus {

    // Complete the plusMinus function below.
    static void plusMinus(int[] arr) {
        int n = arr.length;
        int positiveCounter = 0;
        int negativeCounter = 0;
        int zeroCounter = 0;

        // Iterate and compare numbers
        for (int index = 0; index < n; index++) {
            int currentNumber = arr[index];
            if (currentNumber > 0) {
                positiveCounter++;
            } else if (currentNumber < 0) {
                negativeCounter++;
            } else {
                zeroCounter++;
            }
        }

        System.out.printf("%f", (float) positiveCounter / n);
        System.out.printf("%f", (float) negativeCounter / n);
        System.out.printf("%f", (float) zeroCounter / n);

        // Calculate percentages
//        BigDecimal posCounter = new BigDecimal(positiveCounter);
//        BigDecimal negCounter = new BigDecimal(negativeCounter);
//        BigDecimal zerosCounter = new BigDecimal(zeroCounter);
//        BigDecimal totalCounter = new BigDecimal(n);
//
//        // Print answers
//        System.out.println(posCounter.divide(totalCounter, 6, RoundingMode.HALF_DOWN));
//        System.out.println(negCounter.divide(totalCounter, 6, RoundingMode.HALF_DOWN));
//        System.out.println(zerosCounter.divide(totalCounter, 6, RoundingMode.HALF_DOWN));

    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int arr_i = 0; arr_i < n; arr_i++) {
            arr[arr_i] = in.nextInt();
        }

        // Initialize counters
        plusMinus(arr);

    }
}
