package DP;

import java.util.Arrays;

public class UniquePaths {

//    Recursive solution exceeds time limit
//    public static int uniquePaths(int m, int n) {
//        return uniquePaths(m, n, 1, 1);
//    }
//
//    public static int uniquePaths(int m, int n, int currM, int currN) {
//        if (currM == m && currN == n) return 1;
//        if (currM > m || currN > n) return 0;
//        return uniquePaths(m, n, currM + 1, currN) + uniquePaths(m, n, currM, currN + 1);
//    }

    /* Use dynamic programming to figure out total viable paths from left top corner to bottom right corner */
    public static int uniquePaths(int m, int n) {
        int[][] f = new int[m + 1][n + 1];
        Arrays.fill(f[1], 1);

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (j == 1) f[i][j] = 1;
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m][n];
    }

    /* O(n) space solution */
    public static int uniquePathsV2(int m, int n) {
        int[] f = new int[n];
        Arrays.fill(f, 1);

        // The first iteration of m is already satisfied with Arrays.fill(f, 1)
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[j] += f[j - 1];
            }
        }
        return f[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePathsV2(23, 12));
        System.out.println(uniquePaths(23, 12));
    }
}

// There is only 1 solution given a 1x1 grid, 1x2 grid, etc.
// m and n do not matter. The board can be rotated 90 degrees to achieve the same result
// The new total solution is the sum of total solutions traveling a grid m x n is (m - 1)x(n) + (m)x(n - 1)
// Runtime O(mn): Must run through each element of the m x n array and cache results
// Space O(mn): m x n array used to store previous results
//
// This can further be optimized to 2 rows O(n) space, and even 1 row O(n) space
// O(2n) swap new values up to row 1 after computing
// 1 1 1 1  -> 1 2 3 4 -> 1 2 3 4 -> 1 2 3 4 -> 1 2 3 4
// 1 2 3 4  -> 1 1 1 1 -> 1 3 1 1 -> 1 3 6 1 -> 1 3 6 10
//
// O(n) add previous f[j] and updated f[j - 1] before it
// 1 1 1 1 1 1 -> 1 2 3 4 5 6 (m = 2)
// 1 2 3 4 5 6 -> 1 3 3 4 5 6 -> 1 3 6 4 5 6 -> 1 3 6 10 5 6 -> 1 3 6 10 15 6 -> 1 3 6 10 15 21 (m = 3)
//
// Example grid
// 1 1 1 1
// 1 2 3 4
// 1 3 6 10