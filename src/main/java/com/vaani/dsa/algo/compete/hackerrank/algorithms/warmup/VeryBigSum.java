package com.vaani.dsa.algo.compete.hackerrank.algorithms.warmup;

import java.util.Scanner;

/**
 * Created by mykola on 03.08.15.
 */
public class VeryBigSum {
    static long aVeryBigSum(long[] ar) {
        long sum = 0;
        for (long item : ar) {
            sum += item;
        }
        return sum;
    }

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] ar = new long[n];

        while (n > 0) {
            n--;
            long arItem = scanner.nextLong();
            ar[n] = arItem;

        }
        long sum = aVeryBigSum(ar);
        System.out.println(sum);
    }

}
