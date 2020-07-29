package com.vaani.dsa.algo.ds.array;

import org.junit.Assert;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * 121. Best Time to Buy and Sell Stock
 * Easy
 * Say you have an array for which the i element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * Example 1:
 * <p>
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Not 7-1 = 6, as selling price needs to be larger than buying price.
 * <p>
 * Example 2:
 * <p>
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        BestTimeToBuyAndSellStock underTest = new BestTimeToBuyAndSellStock();
        int[] prices = {7, 1, 5, 3, 6, 4};
        Assert.assertEquals(5, underTest.maxProfit(prices));
        prices = new int[]{7, 6, 4, 3, 1};
        Assert.assertEquals(0, underTest.maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        int size = prices.length;

        if (size <= 1) {
            return 0;
        }

        int min = prices[0];
        int maxProfit = prices[1] - min;
        for (int i = 2; i < size; i++) {

            if (min > prices[i - 1]) {
                min = prices[i - 1];
            }

            int currProfit = prices[i] - min;
            maxProfit = Math.max(maxProfit, currProfit);
        }


        return Math.max(maxProfit, 0);
    }
}
