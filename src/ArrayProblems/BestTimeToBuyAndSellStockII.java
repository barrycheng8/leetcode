public class BestTimeToBuyAndSellStockII {
/*
    public static int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;

        int max = 0;
        int buy = prices[0];
        int sell, profit;

        for (int i = 1; i < prices.length; i++) {
            sell = prices[i];
            profit = sell - buy;
            if (profit > 0) {
                int j = i;
                while (j < prices.length) {
                    if (prices[j] - sell > profit) {
                        i = j;
                        break;
                    }
                    else j++;
                }
                max += prices[i] - buy;
            }
            else {
                buy = prices[i];
            }
        }
        return max;
    }*/

    public static int maxProfit(int[] prices) {
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i - 1] > 0)
                profit += prices[i] - prices[i - 1];
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int[] arr2 = {7,1,5,3,6,4};
        int[] arr3 = {7,6,4,3,1};
        System.out.println(maxProfit(arr2));
    }
}
