// LeetCode 1014. Best Sightseeing Pair
// Given an array of positive integers, A[i] represents the value at the i-th sightseeing spot.
// We want to evaluate the maximum score of a pair of sightseeing spots, where i < j, with the score
// calculation scheme: A[i] + i + A[j] - j.
// That is, we add up the values of each spot and subtract the distance between them as the score.
// Find the maximum score.

// Intuition: At each iteration (i), keep the maximum score obtained from visiting the site.
// This value is stored in max.
// As we move forward, save the maximum added score for visiting j, but decrement by 1 as a penalty for visiting a farther site.
// After iterating through the entire array, ans will contain the maximum score.
// O(n) runtime: one pass through the array
// O(1) space: 2 primitive variables that keep track of running maximum
package ArrayProblems;

import java.util.Arrays;

public class BestSightseeingPair {

    public static int maxScoreSightseeingPairV3(int[] A) {
        int ans = 0; int curr = 0;

        for (int i = 0; i < A.length; i++) {
            ans = Math.max(ans, curr + A[i]);
            curr = Math.max(curr, A[i]) - 1;
        }
        return ans;
    }

    public static int maxScoreSightseeingPairV2(int[] A) {
        int ans = A[0];
        int prevIndex = 0;

        for (int j = 1; j < A.length; j++) {
            ans = Math.max(ans, A[prevIndex] + prevIndex + A[j] - j);
            if (A[prevIndex] + prevIndex < A[j] + j)
                prevIndex = j;
        }
        return ans;
    }

    // Checking all pairs O(n^2) exceeds time limit
    public static int maxScoreSightseeingPair(int[] A) {
        int[] best = new int[A.length];

        for(int i = 0; i < A.length; i++) {
            int score = 0;
            for (int j = i + 1; j < A.length; j++) {
                score = A[i] + A[j] + i - j;
                best[i] = Math.max(best[i], score);
            }
        }
        return Arrays.stream(best).max().getAsInt();
    }

    public static void main(String[] args) {
        int[] scores = {8, 1, 5, 2, 6};

        System.out.println(maxScoreSightseeingPair(scores));
        System.out.println(maxScoreSightseeingPairV2(scores));
        System.out.println(maxScoreSightseeingPairV3(scores));
    }
}
