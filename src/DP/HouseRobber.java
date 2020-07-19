// LeetCode 198. House Robber
// Given a list of non-negative integers representing the money earned at each house, determine the max amount of money without alerting the police.
// You cannot rob houses that are adjacent to each other.
// Ex. [1000, 5, 2, 1005, 9]  The maximum would be by robbing 1000 + 1005 = 2005.
// Intuition: Using dynamic programming, we will hold a cache of best possible values as we visit each house.
// At each house, there are 2 options:
// a) rob the current house and keep the best loot from the house before previous
// b) loot the previous house and keep the best loot before that
// We initialize the array with 0 and the option of robbing the first house
// Upon visiting each house, we decide whether we want to rob the current one and discard the best from previous (f[i - 1] + nums[i])
// or we skip robbing the current one and keep our current profit (f[i])
// At the end we return f[num.length], which holds the answer after visiting all houses
// O(n) runtime: each element in the array is touched once to determine the max profit
// O(n) space: array of size n to cache previous best results

package DP;

public class HouseRobber {

    public static int rob(int[] nums) {
        if (nums.length == 0) return 0;

        int[] f = new int[nums.length + 1];
        f[0] = 0;
        f[1] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            f[i + 1] = Math.max(f[i], f[i - 1] + nums[i]);
        }

        return f[nums.length];
    }

    public static void main(String[] args) {
        int[] arr = {1000, 7, 9, 1005, 1};

        System.out.println(rob(arr));
    }
}
