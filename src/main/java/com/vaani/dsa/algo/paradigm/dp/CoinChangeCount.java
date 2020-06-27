package com.vaani.dsa.algo.paradigm.dp;

public class CoinChangeCount {
    // Returns the count of ways we can
// sum S[0...m-1] coins to get sum n
    static int countRecursive(int S[], int m, int sum) {
        // If sum is 0 then there is 1 solution
        // (do not include any coin)
        if (sum == 0)
            return 1;

        // If n is less than 0 then no
        // solution exists
        if (sum < 0)
            return 0;

        // If there are no coins and sum
        // is greater than 0, then no
        // solution exist
        if (m <= 0 && sum >= 1)
            return 0;

        // Case 1. include current coin S[n] in solution and recur
        // with remaining change (N - S[n]) with same number of coins
        int include = countRecursive(S, m, sum - S[m]);

        // Case 2. exclude current coin S[n] from solution and recur
        // for remaining coins (n - 1)
        int exclude = countRecursive(S, m - 1, sum);

        // return total ways by including or excluding current coin
        return include + exclude;
    }
}
