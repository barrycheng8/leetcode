// LeetCode 70. Climbing Stairs
// Climbing a staircase, you can either climb 1 or 2 steps. Find the # of distinct ways to reach the top.
// Intuition: This is essentially a Fibonacci sequence. We initialize the cache with our base cases,
// 0 ways to climb 0 stairs
// 1 way to climb 1 stair
// 2 ways to climb 2 stairs (1 + 1 and 2)
// To find the amount of ways to climb n stairs, we add the previous 2 results.
// O(n) runtime: We need to previous results to calculate the n-th result
// O(n) space: array of size n to store previous results

package DP;

public class ClimbingStairs {

    public static int climbStairs(int n) {
        int[] f = new int[n + 3];
        f[0] = 0; f[1] = 1; f[2] = 2;

        for (int i = 3; i <= n; i++)
            f[i] = f[i - 2] + f[i - 1];

        return f[n];
    }

    public static int climbStairsV2(int n) {
        if (n == 1) return 1;

        int first = 1;
        int second = 2;

        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }
}
