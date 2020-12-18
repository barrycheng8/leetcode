package HashSetProblems;

import java.util.HashMap;
import java.util.Map;

public class FourSumII {

    // O(2n^2) time complexity
    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();

        // add counts of all sums from values from C and D
        for (int c : C) {
            for (int d : D) {
                int sum = c + d;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int count = 0;

        // Check if the negation of a+b exists in the map that constitutes sums of c+d
        // If so, add it to the total count
        for (int a : A) {
            for (int b : B) {
                int sum = a + b;
                count += map.getOrDefault(-sum, 0);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] A = {1, 2};
        int[] B = {-2, -1};
        int[] C = {-1, 2};
        int[] D = {0, 2};

        System.out.println(fourSumCount(A, B, C, D));
    }
}
