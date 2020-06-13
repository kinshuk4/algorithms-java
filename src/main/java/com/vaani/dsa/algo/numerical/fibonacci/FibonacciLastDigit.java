package com.vaani.dsa.algo.numerical.fibonacci;

import java.util.*;

public class FibonacciLastDigit {
    static long[] f;

    private static int getFibonacciLastDigitNaive(int n) {
        if (n <= 1)
            return n;

        int previous = 0;
        int current = 1;

        for (int i = 0; i < n - 1; ++i) {
            int tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % 10;
    }

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

    private static int getFibonacciLastDigit(int n) {
        // In Java, values are 0 by default


        // Precomputing units digit of
        // first 60 Fibonacci numbers
        if (f == null) {
            f = getNFibNumbers(60);
        }

        int index = (int) (n % 60L);

        return (int)f[index];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigit(n);
        System.out.println(c);
    }
}