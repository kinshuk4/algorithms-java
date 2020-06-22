package com.vaani.dsa.algo.paradigm.dp;

import org.junit.Assert;

import static org.junit.Assert.assertEquals;

public class NumWaysToRollDice {
    public static int countRecursive(int d, int f, int target) {
        // if desired sum is reached with n dices
        if (d == 0) {
            return (target == 0) ? 1 : 0;
        }

        // desired sum can't be reached with current configuration
        if (target < 0 || f * d < target || d > target) {
            return 0;
        }

        int res = 0;

        // recur for all possible solutions
        for (int i = 1; i <= f; i++) {
            res += countRecursive(d - 1, f, target - i);
        }

        return res;
    }


    public static int countRecursiveMemo(int d, int f, int target) {
        // create a lookup table to store solutions of sub-problems
        // lookup[i][j] stores number of ways to achieve sum j
        // with j throws of given dice.
        int[][] lookup = new int[d + 1][target + 1];
        return countRecursiveMemoUtil(d, f, target, lookup);
    }

    // Function to calculate total number of ways to achieve given
    // sum with n throws of dice having k faces
    private static int countRecursiveMemoUtil(int d, int f, int target, int[][] lookup) {
        // if desired sum is reached with n dices
        if (d == 0) {
            return (target == 0) ? 1 : 0;
        }

        // desired sum can't be reached with current configuration
        if (target < 0 || f * d < target || d > target) {
            return 0;
        }

        // if sub-problem is seen for the first time, solve it and
        // store its result in an array or map
        if (lookup[d][target] == 0) {
            // recur for all possible solutions
            for (int i = 1; i <= f; i++) {
                lookup[d][target] += countRecursiveMemoUtil(d - 1, f, target - i, lookup);
            }
        }

        // return solution to current sub-problem
        return lookup[d][target];
    }


    public static long countDp1(int d, int f, int target) {
        long[][] table = new long[d + 1][target + 1];

        /* Table entries for only one dice */
        for (int j = 1; j <= f && j <= target; j++)
            table[1][j] = 1;


        for (int i = 2; i <= d; i++) {
            for (int j = 1; j <= target; j++) {
                for (int k = 1; k < j && k <= f; k++)
                    table[i][j] += table[i - 1][j - k];
            }
        }

        return table[d][target];
    }

    public static long countDp2(int f, int d, int s) {
        // Create a table to store results of subproblems.  One extra
        // row and column are used for simpilicity (Number of dice
        // is directly used as row index and sum is directly used
        // as column index).  The entries in 0th row and 0th column
        // are never used.
        long mem[][] = new long[d + 1][s + 1];
        // Table entries for no dices
        // If you do not have any data, then the value must be 0, so the result is 1
        mem[0][0] = 1;
        // Iterate over dices
        for(int i=1; i<=d; i++) {
            // Iterate over sum
            for(int j=i; j<=s; j++) {
                // The result is obtained in two ways, pin the current dice and spending 1 of the value,
                // so we have mem[i-1][j-1] remaining combinations, to find the remaining combinations we
                // would have to pin the values ??above 1 then we use mem[i][j-1] to sum all combinations
                // that pin the remaining j-1's. But there is a way, when "j-f-1> = 0" we would be adding
                // extra combinations, so we remove the combinations that only pin the extrapolated dice face and
                // subtract the extrapolated combinations.
                mem[i][j] = mem[i][j-1] + mem[i-1][j-1];
                if(j-f-1 >= 0)
                    mem[i][j] -= mem[i-1][j-f-1];
            }
        }
        return mem[d][s];
    }



    public static void main(String[] args) {
        assertEquals(6, countRecursive(2, 6, 7));
        assertEquals(6, countRecursiveMemo(2, 6, 7));
        assertEquals(6, countDp1(2, 6, 7));
        assertEquals(6, countDp2(2, 6, 7));
    }
}
