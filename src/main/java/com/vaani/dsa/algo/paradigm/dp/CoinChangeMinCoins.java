package com.vaani.dsa.algo.paradigm.dp;

import java.util.Arrays;

/**
 * Return the coins combination with the minimum number of coins.
 * Time complexity O(MN), where M is the target value and N is the number of distinct coins.
 * Space complexity O(M).
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
    public int[] minCoins(int value, int[] coins) {
        Arrays.sort(coins);
        int[] cache = new int[value + 1]; // stores the min number of coins
        Arrays.fill(cache, Integer.MAX_VALUE);
        int[] prev = new int[value + 1]; // stores the previous location
        cache[0] = 0;
        prev[0] = 0;

        for (int i = 0; i <= value; ++i) {
            for (int j = coins.length - 1; j >= 0; --j) {
                if (coins[j] <= i) {
                    if (1 + cache[i - coins[j]] < cache[i]) { // find better combination
                        cache[i] = 1 + cache[i - coins[j]];
                        prev[i] = i - coins[j];
                    }
                }
            }
        }

        int[] res = new int[cache[value]];
        int idx = 0;
        int remain = value;
        while (remain != 0) {
            int coin = remain - prev[remain];
            remain = prev[remain];
            res[idx] = coin;
            ++idx;
        }

        return res;
    }

}
