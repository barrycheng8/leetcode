// LeetCode 121. Best Time to Buy and Sell Stock
// Given an array where the i-th element is the price of a stock during day i.
// If you're only allowed one transaction, find the maximum profit possible. You cannot sell before you buy.
// Note: you cannot buy + sell on the same day
// Intuition: Find the highest selling point and lowest buying point before the selling point. This will result in the max profit.
// Start at the end of the array. We set the initial sell price at the end of the array
// Traverse the array backwards, calculating the profit earned. If we end up with negative profit, that means
// the sell price was lower than the buy price. Set the new sell price to the higher value.
// Once we reach the start of the array, we are done.
//
// O(n) runtime: one pass through the array
// O(1) space: 2 variables are used
// See: Kadane's Algorithm (maximum subarray)

package ArrayProblems;

public class BestTimeToBuyAndSellStock {

    public static int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;

        int sell = prices[prices.length - 1];
        int profit = 0;

        for (int i = prices.length - 1; i >= 0; i--) {
            profit = Math.max(profit, sell - prices[i]);
            if (sell < prices[i])
                sell = prices[i];
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] prices = {};
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfit(prices2));
    }
}
