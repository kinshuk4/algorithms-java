package com.vaani.dsa.algo.paradigm.dp;

public class CoinChangeCount {
    // Returns the count of ways we can
// amount coins[0...n-1] coins to get amount n
    public static int countRecursive(int coins[], int n, int amount) {
        // If amount is 0 then there is 1 solution
        // (do not include any coin)
        if (amount == 0)
            return 1;

        // If n is less than 0 then no
        // solution exists
        if (amount < 0)
            return 0;

        // If there are no coins and amount
        // is greater than 0, then no
        // solution exist
        if (n <= 0 && amount >= 1)
            return 0;

        // Case 1. include current coin coins[n] in solution and recur
        // with remaining change (N - coins[n]) with same number of coins
        int include = countRecursive(coins, n, amount - coins[n]);

        // Case 2. exclude current coin coins[n] from solution and recur
        // for remaining coins (n - 1)
        int exclude = countRecursive(coins, n - 1, amount);

        // return total ways by including or excluding current coin
        return include + exclude;
    }
}
