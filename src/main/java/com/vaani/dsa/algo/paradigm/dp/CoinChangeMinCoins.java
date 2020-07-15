package com.vaani.dsa.algo.paradigm.dp;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/coin-change/
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * <p>
 */
public class CoinChangeMinCoins {

    public static int minCoinsRecursive(int[] coins, int V){
        // base case
        if (V == 0) return 0;

        // Initialize result
        int result = Integer.MAX_VALUE;

        // Try every coin that has smaller value than V
        for (int i=0; i<coins.length; i++) {
            if (coins[i] <= V) {
                int currResult = minCoinsRecursive(coins, V-coins[i]);

                // Check for INT_MAX to avoid overflow and see if
                // result can minimized
                if (currResult != Integer.MAX_VALUE && currResult + 1 < result) {
                    result = currResult + 1;
                }

            }
        }
        return result;
    }

    // Time complexity O(MN), where M is the target value and N is the number of distinct coins.
    // Space complexity O(M).
    public int[] minCoinsDP(int amount, int[] coins) {
        Arrays.sort(coins);
        int[] dp = new int[amount + 1]; // stores the min number of coins
        Arrays.fill(dp, amount + 1); // default integer value in array is not good, also Integer.MAX_VALUE causes overflow so, returned result will be wrong


        int[] prev = new int[amount + 1]; // stores the previous location
        dp[0] = 0;
        prev[0] = 0;

        for (int i = 0; i <= amount; ++i) {
            for (int j = coins.length - 1; j >= 0; --j) {
                if (coins[j] <= i) {
                    if (1 + dp[i - coins[j]] < dp[i]) { // find better combination
                        dp[i] = 1 + dp[i - coins[j]];
                        prev[i] = i - coins[j];
                    }
                }
            }
        }

        int[] res = new int[dp[amount]];
        int idx = 0;
        int remain = amount;
        while (remain != 0) {
            int coin = remain - prev[remain];
            remain = prev[remain];
            res[idx] = coin;
            ++idx;
        }

        return res;
    }

    public int minCoinsDP2(int amount, int[] coins) {
        int[] dp = new int[amount + 1]; // stores the min number of coins
        Arrays.fill(dp, Integer.MAX_VALUE); // default integer value in array is not good

        dp[0] = 0;
        for (int i = 0; i <= amount; ++i) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int minCoinsDP2Optimized(int amount, int[] coins) {
        Arrays.sort(coins);
        int[] dp = new int[amount + 1]; // stores the min number of coins
        Arrays.fill(dp, Integer.MAX_VALUE); // default integer value in array is not good

        dp[0] = 0;
        for (int i = 0; i <= amount; ++i) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                } else {
                    break; // coins array is already sorted
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
