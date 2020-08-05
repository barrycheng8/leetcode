// LeetCode 309. Best Time to Buy and Sell Stock with Cooldown
// Given an array of prices, where the i-th element is the price on a given day
// You can buy and sell as many times as you want, but you cannot perform multiple transactions on the same day (buy and sell on the same day)
// After you sell a stock, you cannot buy on the next day (1 day cooldown)
// Ex. [1, 2, 3, 0, 2] maxProfit = 3 , [buy, sell, rest, buy, sell]
//
// Intuition:
// Use DP and a greedy approach to determine if its best to choose one of these possible states:
// 1. Currently have no stock. Buy today
// 2. Currently have stock. The buy price today is better than yesterday, so we scrap yesterday's result
// 3. Currently have stock. Sell - (previous buy price results in better profit) VS (holding stock)
// 4. Currently don't have stock, but we check the maxProfit from 2 days ago to see if buying today is good
//
// Growth functions:
// O(n) runtime: We must traverse through the entire array to determine the max possible profit
// O(n) space: 2 arrays to hold the best buy and sell prices. This can be optimized to O(1) space if we only store the value
//             from the previous iteration

package DP;

public class BestTimeToBuyAndSellStockCooldown {

    // Another method:
    // dp[i][0]: not holding any stock on day i (rest or sold)
    // dp[i][1]: bought stock on day i
    // Case 1: Bought stock on day i, dp[i][1] is the max of:
    // - I bought it today
    //   dp[i-2][0] - prices[i] take best price before cool down after selling stock
    // - I am holding the stock
    //   dp[i-1][1]   I am still holding the stock so my profit remains the same as the previous day
    // Case 2: We don't have stock on day i. d[i][0] is the max of:
    // - I sold it today
    //   dp[i-1][1] + prices[i] we take the previous best profit and add our current selling price to the profit
    // - I am holding the stock
    //   dp[i-1][0]
    public int maxProfit(int[] prices) {
        if(prices.length < 2) return 0;
        int len = prices.length;

        int[] s0 = new int[len]; // resting and can buy stock
        int[] s1 = new int[len]; // holding stock, can sell
        int[] s2 = new int[len]; // sold the stock, must rest next day

        s0[0] = 0; // we start off with no stock
        s1[0] = -prices[0]; // if we buy the stock on this day, this is our total cost
        s2[0] = Integer.MIN_VALUE; // profit base case. selling the stock at any time will beat this initial case

        for (int i = 1; i < len; i++) {
            s0[i] = Math.max(s0[i - 1], s2[i - 1]); // we continue to hold no stock or we recover from cooldown
            s1[i] = Math.max(s1[i - 1], s0[i - 1] - prices[i]); // Compare buying price today and yesterday. Choose the better value
            s2[i] = s1[i - 1] + prices[i]; // Use yesterdays buying price and see how much profit we get if we sold today
        }

        return Math.max(s0[len - 1], s2[len - 1]);
    }

    public int maxProfitEasy(int[] prices) {
        int len = prices.length;

        // If len < 2, we have no opportunity to buy or sell within time span. Max profit is 0.
        if (len < 2) return 0;

        // Stores previous best results
        int[] buy = new int[len];
        int[] sell = new int[len];

        // Max profit at day 0 if we buy
        buy[0] = -prices[0];
        // Max profit at day 0 if we sell. We currently have no stock, so it will be set to 0
        sell[0] = 0;
        // See if buying on day 1 results in a better (less negative) price
        buy[1] = Math.max(buy[0], -prices[1]);
        // Compare buying on day 0 and selling on day 1 beats simply doing nothing
        sell[1] = Math.max(sell[0], buy[0] + prices[1]);

        // Iterate and record best profit values using a Max (greedy) function
        for (int i = 2; i < len; i++) {
            // Is it better to (hold the current stock using old buy price) or (buy today after 1 day cooldown)
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            // Is it better to (wait with no stock) OR (sell today)?
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }

        // Eventually we want to sell to maximize our profit. The last element in the sell array will have this
        // value maximized
        return sell[len - 1];
    }

}
