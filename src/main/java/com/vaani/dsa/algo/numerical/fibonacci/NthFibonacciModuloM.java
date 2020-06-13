package com.vaani.dsa.algo.numerical.fibonacci;

public class NthFibonacciModuloM {
    static long getPisanoPeriod(long m) {
        long prev = 0, curr = 1, c = prev + curr;
        for (int i = 0; i < m * m; i++) {
            c = (prev + curr) % m;
            prev = curr;
            curr = c;
            if (prev == 0 && curr == 1) {
                return i + 1;
            }
        }

        return -1;
    }

     static long getFibonacciModuloM(long n, long m) {
        long remainder = n % getPisanoPeriod(m);

        long first = 0;
        long second = 1;

        long res = remainder;

        for (int i = 1; i < remainder; i++) {
            res = (first + second) % m;
            first = second;
            second = res;
        }

        return res % m;
    }

    public static void main(String[] args) {
        int n = 10;
        int m = 3;
        System.out.println(getFibonacciModuloM(n, m));
    }
}
