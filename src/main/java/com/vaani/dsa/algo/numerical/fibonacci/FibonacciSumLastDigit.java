package com.vaani.dsa.algo.numerical.fibonacci;

import java.util.*;

public class FibonacciSumLastDigit {
    private static long getFibonacciSumNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current = 1;
        long sum = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current;
        }

        return sum % 10;
    }

    static long[] f;

    static long[] getNFibNumbers(int n) {
        // 0th and 1st number of
        // the series are 0 and 1
        long f[] = new long[60];
        f[0] = 0;
        f[1] = 1;

        // Add the previous 2 numbers
        // in the series and store
        // last digit of result
        for (int i = 2; i < n; i++)
            f[i] = (f[i - 1] + f[i - 2]) % 10;

        return f;
    }

    private static long getFibonacciSum(long n) {
        int pisanoPeriod = 60;

        if (n <= 1)
            return n;

        if (f == null) {
            f = getNFibNumbers(pisanoPeriod);
        }

        int index = (int) ((n + 2) % 60L);
        if (f[index] == 0) {
            return 9;
        } else {
            return (f[index] - 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSum(n);
        System.out.println(s);
    }
}