package com.vaani.dsa.algo.ds.array;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 * 188. Best Time to Buy and Sell Stock IV
 * Hard
 * <p>
 * Say you have an array for which the i-th element is the price of a given stock on day i.
 * <p>
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * <p>
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * <p>
 * Example 1:
 * <p>
 * Input: [2,4,1], k = 2
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * <p>
 * Example 2:
 * <p>
 * Input: [3,2,6,5,0,3], k = 2
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 * Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 */


public class BestTimeToBuyAndSellStock4 {
    /**
     * dp[i,j] represent the max profit with at most i transactions before time j
     * case 1: no sell at j
     * case 2: sell at j, i-1 transaction before k
     * dp[i,j]= Math.max(dp[i,j-1], profit[j] - (profit[t]- dp[i-1,t])) (0<=t<j)
     * = Math.max(dp[i,j-1], profit[j] - min(profit[t]- dp[i-1,t])
     * or = Math.max(dp[i,j-1], profit[j] + max(dp[i-1,t]-profit[t])
     * Init:
     * dp[0, j] = 0; 0 transactions makes 0 profit
     * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
     **/
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        if (k >= prices.length / 2) {
            return quickSolve(prices);
        }

        int maxProfit = 0;
        int[][] dp = new int[k + 1][prices.length];
        for (int i = 1; i <= k; i++) {
            int tmpMax = dp[i - 1][0] - prices[0];
            for (int j = 1; j < prices.length; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + tmpMax);
                tmpMax = Math.max(tmpMax, dp[i - 1][j] - prices[j]);
            }
        }
        return dp[k][prices.length - 1];
    }

    // BestTimeToBuyAndSellStock2 - maxprofit
    private int quickSolve(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxProfit += prices[i] - prices[i - 1];
        }
        return maxProfit;
    }
}
