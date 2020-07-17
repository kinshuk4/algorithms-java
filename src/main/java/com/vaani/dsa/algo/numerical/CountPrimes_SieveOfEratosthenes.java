package com.vaani.dsa.algo.numerical;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/count-primes/
 * https://www.geeksforgeeks.org/sieve-of-eratosthenes/
 * Count the number of prime numbers less than a non-negative number, n.
 * <p>
 * Example:
 * <p>
 * Input: 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */
public class CountPrimes_SieveOfEratosthenes {
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        // exclude 1 and n
        int count = n - 2;

        // only need to check to sqrt(n) as the rest pairs are equivalent to previous pairs
        for (int i = 2; i * i < n; i++) {
            // not prime, its multiples have already been checked
            if (!isPrime[i]) continue;
            // start from i since previous pairs have been checked
            for (int j = i; i * j < n; j++) {
                if (isPrime[i * j]) {
                    isPrime[i * j] = false;
                    count--;
                }
            }
        }

        return count;
    }


    // bad for readability, but fast on leetcode - change isPrime to isNotPrime array
    public int countPrimes2(int n) {
        if (n <= 2) {
            return 0;
        }
        boolean[] isNotPrime = new boolean[n]; // by default true
        // exclude 1 and n
        int count = n - 2;

        // only need to check to sqrt(n) as the rest pairs are equivalent to previous pairs
        for (int i = 2; i * i < n; i++) {
            // not prime, its multiples have already been checked
            if (isNotPrime[i]) {
                continue;
            }
            // start from i since previous pairs have been checked
            for (int j = i; i * j < n; j++) {
                if (!isNotPrime[i * j]) {
                    isNotPrime[i * j] = true;
                    count--;
                }
            }
        }

        return count;
    }
}
