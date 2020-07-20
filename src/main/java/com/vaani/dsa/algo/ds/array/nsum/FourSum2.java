package com.vaani.dsa.algo.ds.array.nsum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://leetcode.com/problems/4sum-ii/
 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
 * <p>
 * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
 * <p>
 * Example:
 * <p>
 * Input:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * <p>
 * Output:
 * 2
 * <p>
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
public class FourSum2 {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : A)
            for (int b : B) {
                int s = a + b;
                map.put(s, map.getOrDefault(s, 0) + 1);
            }

        int result = 0;
        for (int c : C)
            for (int d : D) {
                int s = -c - d;
                result += map.getOrDefault(s, 0);
            }
        return result;
    }

    // fails may be due to thread issue
    public int fourSumCountParallelStream(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();

        Arrays.stream(A).parallel().forEach(a -> {
            Arrays.stream(B).parallel().forEach(b -> map.put(a + b, map.getOrDefault(a + b, 0) + 1));
        });

        AtomicInteger result = new AtomicInteger();
        Arrays.stream(C).parallel().forEach(c -> {
            Arrays.stream(D).parallel().forEach(d -> {
                result.addAndGet(map.getOrDefault(-c - d, 0));
            });
        });

        return result.get();
    }

    // fast -
    public int fourSumCountStream(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();

        Arrays.stream(A).forEach(a -> {
            Arrays.stream(B).parallel().forEach(b -> map.put(a + b, map.getOrDefault(a + b, 0) + 1));
        });

        AtomicInteger result = new AtomicInteger();
        Arrays.stream(C).forEach(c -> {
            Arrays.stream(D).parallel().forEach(d -> {
                result.addAndGet(map.getOrDefault(-c - d, 0));
            });
        });

        return result.get();
    }
}
