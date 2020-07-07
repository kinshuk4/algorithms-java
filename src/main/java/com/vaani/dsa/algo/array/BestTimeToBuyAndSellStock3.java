package com.vaani.dsa.algo.array;


/**
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete at most two transactions.
Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*/
public class BestTimeToBuyAndSellStock3 {
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        //highest profit in 0 ... i
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];

        // DP from left to right
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }

        // DP from right to left
        right[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            right[i] = Math.max(right[i + 1], max - prices[i]);
        }

        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            profit = Math.max(profit, left[i] + right[i]);
        }

        return profit;
    }

    public int maxProfit(int price[])
    {
        int n = price.length;
        // Create profit array and initialize it as 0
        int profit[] = new int[n];
        for (int i=0; i<n; i++)
            profit[i] = 0;

        /* Get the maximum profit with only one transaction
           allowed. After this loop, profit[i] contains maximum
           profit from price[i..n-1] using at most one trans. */
        int max_price = price[n-1];
        for (int i=n-2;i>=0;i--)
        {
            // max_price has maximum of price[i..n-1]
            max_price = Math.max(max_price, price[i]);

            // we can get profit[i] by taking maximum of:
            // a) previous maximum, i.e., profit[i+1]
            // b) profit by buying at price[i] and selling at
            //    max_price
            profit[i] = Math.max(profit[i+1], max_price-price[i]);
        }

        /* Get the maximum profit with two transactions allowed
           After this loop, profit[n-1] contains the result */
        int min_price = price[0];
        for (int i=1; i<n; i++)
        {
            // min_price is minimum price in price[0..i]
            min_price = Math.min(min_price, price[i]);

            // Maximum profit is maximum of:
            // a) previous maximum, i.e., profit[i-1]
            // b) (Buy, Sell) at (min_price, price[i]) and add
            //    profit of other trans. stored in profit[i]
            profit[i] = Math.max(profit[i-1], profit[i] +
                    (price[i]-min_price) );
        }
        int result = profit[n-1];
        return result;
    }
}