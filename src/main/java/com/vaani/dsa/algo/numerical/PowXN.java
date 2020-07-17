package com.vaani.dsa.algo.numerical;

/*
Implement pow(x, n).
*/

public class PowXN {
    public static void main(String[] args) throws Exception {
        System.out.println(1 / new PowXN().pow2(2.00000, -2147483648));
    }

    // Solution: Works with O(log n)
    public double pow2(double x, int n) {
        if (n == 0) return 1D;
        long N = n; // use long to avoid overflow.
        return pow2Helper(n < 0 ? (1 / x) : x, N < 0 ? (N * -1) : N);
    }

    public double pow2Helper(double x, long n) {
        if (n == 1) return x;
        double val = pow2Helper(x, n / 2);
        return val * val * ((n % 2) == 0 ? 1 : x);
    }

    // without helper function
    public double pow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n == -1) return 1 / x;

        if (n % 2 == 0) {
            double temp = pow(x, n / 2);
            return temp * temp;
        } else {
            double temp = pow(x, (n - 1) / 2);
            return temp * temp * x;
        }
    }

    //exceeds time limit
    public double pow1(double x, int n) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        boolean neg = false;
        if (n == 0) return 1;
        if (n < 0) neg = true;

        n = Math.abs(n);
        double result = 1;
        int i = 1, k = 0;
        while (k < n) {
            double temp = x;
            for (i = 1; i * 2 <= n - k; i = i * 2) {
                temp *= temp;
            }
            k += i;
            result *= temp;
        }

        if (neg) return 1.0 / result;
        else return result;
    }
}