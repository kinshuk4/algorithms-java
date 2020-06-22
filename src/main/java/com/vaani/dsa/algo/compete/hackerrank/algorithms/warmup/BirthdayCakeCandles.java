package com.vaani.dsa.algo.compete.hackerrank.algorithms.warmup;

//https://www.hackerrank.com/challenges/birthday-cake-candles/problem
public class BirthdayCakeCandles {
    static int birthdayCakeCandles(int[] ar) {
        int max = 0;
        int frequency = 0;
        int n = ar.length;

        for (int height : ar) {
            if (height > max) {
                max = height;
                frequency = 1;
            } else if (height == max) {
                frequency++;
            }
        }

        return frequency;
    }

}
