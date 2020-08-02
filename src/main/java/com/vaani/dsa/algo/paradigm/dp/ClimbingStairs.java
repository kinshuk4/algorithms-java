package com.vaani.dsa.algo.paradigm.dp;

/**
 * https://leetcode.com/problems/climbing-stairs/
 * 70. Climbing Stairs
 * Easy
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * <p>
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 * <p>
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 45
 */

// Very similar to Fibonacci
public class ClimbingStairs {
    public static int climbStairsRecursiveNaive(int n) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else {
            return climbStairsRecursiveNaive(n - 1) + climbStairsRecursiveNaive(n - 2);
        }
    }

    public static int climbStairsHelper(int n, int[] countArr) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (countArr[n] != 0) {
            return countArr[n];
        } else {
            countArr[n] = climbStairsHelper(n - 1, countArr) + climbStairsHelper(n - 2, countArr);
            return countArr[n];
        }
    }

    public int climbStairsRecursiveMemoized(int n) {
        int[] countArr = new int[n + 1];
        return climbStairsHelper(n, countArr);
    }


    public int climbStairsDP(int n) {
        if (n == 1)
            return 1;
        else if (n == 2)
            return 2;

        int[] table = new int[n + 1];

        table[0] = 1;
        table[1] = 1;
        table[2] = 2;

        for (int i = 3; i < n + 1; i++) {
            table[i] = table[i - 1] + table[i - 2];
        }
        return table[n];
    }

    public static int climbStairsDPO1(int n) {
        if (n == 1)
            return 1;
        else if (n == 2)
            return 2;


        int first = 1;
        int second = 1;
        int third = first + second;

        for (int i = 2; i <= n; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }
}
