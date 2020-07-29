package com.vaani.dsa.algo.ds.array.stock;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * 309. Best Time to Buy and Sell Stock with Cooldown
 * Medium
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 * <p>
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * Example:
 * <p>
 * prices = [1, 2, 3, 0, 2]
 * maxProfit = 3
 * transactions = [buy, sell, cooldown, buy, sell]
 */
public class BestTimeToBuyAndSellStockWithCooldown {

    // DP - O(1) - https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75927/Share-my-thinking-process
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int sell = 0, prevSell = 0, buy = Integer.MIN_VALUE, prevBuy;
        for (int price : prices) {
            prevBuy = buy;
            buy = Math.max(prevSell - price, prevBuy);
            prevSell = sell;
            sell = Math.max(prevBuy + price, prevSell);
        }
        return sell;
    }

    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/240097/Come-on-in-you-will-not-regret-most-general-java-code-like-all-other-DP-solutions
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        int[] dp = new int[n];
        int maxDiff = Integer.MIN_VALUE; // M[j - 2] - prices[j]
        for (int i = 0; i < n; i++) {
            if (i < 2) {
                maxDiff = Math.max(maxDiff, -prices[i]);
            }
            if (i == 0) {
                dp[0] = 0;
            } else if (i == 1) {
                dp[1] = Math.max(prices[1] - prices[0], 0);
            } else {
                dp[i] = Math.max(dp[i - 1], maxDiff + prices[i]);
                maxDiff = Math.max(maxDiff, dp[i - 2] - prices[i]);
            }
        }

        return dp[n - 1];
    }

    // easiest - https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/76000/Easy-Understand-Java-DP-solution-with-comments
    public int maxProfit3(int[] prices) {
        if(prices == null || prices.length < 2){
            return 0;
        }
        int n = prices.length;
        int[] sell = new int[n]; //sell[i] means must sell at day i
        int[] cooldown = new int[n]; //cooldown[i] means day i is cooldown day
        sell[1] = prices[1] - prices[0];
        for(int i = 2; i < prices.length; ++i){
            cooldown[i] = Math.max(sell[i - 1], cooldown[i - 1]);
            sell[i] = prices[i] - prices[i - 1] + Math.max(sell[i - 1], cooldown[i - 2]);
        }
        return Math.max(sell[n - 1], cooldown[n - 1]);
    }

}
