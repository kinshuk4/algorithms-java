package com.vaani.dsa.algo.numerical;

public class BinomialCoefficient {
    public static long binomialCoefficientIterative(int n, int k) {
        long res = 1;

        // Since C(n, k) = C(n, n-k)
        if (k > n - k) {
            k = n - k;
        }

        // Calculate value of [n*(n-1)*---*(n-k+1)] / [k*(k-1)*---*1]
        for (int i = 0; i < k; ++i) {
            res *= (n - i);
            res /= (i + 1);
        }

        return res;
    }
}
