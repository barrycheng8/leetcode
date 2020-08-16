// LeetCode 123. Best Time to Buy and Sell Stock III
// Given an array where the i-th element is the stock price for that day, design an algorithm to find the max profit.
// You may complete at most two transactions (buy + sell). You must sell the stock before you can buy again
//
// Intuition:
// We want to build upon the idea of Buy and Sell Stock I, which only allows us to perform one transaction in the time frame.
// Buy and Sell Stock I uses two variables: one to hold lowestPrice and profit to hold the maximum profit gained if sold at the current day.
// We can apply this same concept to performing two transactions.
// - oneBuy keeps track of the lowest price
// - oneBuyOneSell keeps track of the max profit we can get from the oneBuy buy price.
// - to calculate twoBuy's price, we use the profit from the first buy as a discount for the second buy.
// - apply the same concept to calculate the 2nd buy profit
//
// Growth functions:
// O(n) runtime: one pass through the array
// O(1) space: no data structures used

package DP;

public class BestTimeToBuyAndSellStockIII {

    public int maxProfit(int[] prices) {
        int oneBuy = Integer.MAX_VALUE;
        int oneBuyOneSell = 0;
        int twoBuy = Integer.MAX_VALUE;
        int twoBuyTwoSell = 0;

        for (int price : prices) {
            oneBuy = Math.min(oneBuy, price);                           // Find minimum price to buy for first buy
            oneBuyOneSell = Math.max(oneBuyOneSell, price - oneBuy);    // Find max profit from first buy
            twoBuy = Math.min(twoBuy, price - oneBuyOneSell);           // Use profit from first buy to determine 2nd buy price
            twoBuyTwoSell = Math.max(twoBuyTwoSell, price - twoBuy);    // Find max profit from second buy
        }

        return twoBuyTwoSell;
    }
}
