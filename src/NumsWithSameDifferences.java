// LeetCode 967. Numbers With Same Consecutive Differences
// Return all non-negative integers of length N such that the absolute different between every 2 consecutive digits is K.
// All numbers cannot have leading 0's except for 0 itself.
// The answer can be returned in any order
//
// Intuition:
// We can think of this as a BFS problem, where we build the number level by level. First we start with the left-most place
// and then build the next number if it is within 0 <= onesPlace < 10. We need to account for a few edge case, such as
// when K = 0 (potentially double count) and when N = 1 and K = 0 (we should include 0 as a single digit)
//
// Growth functions:
// O(N * 2^N) runtime: Because we are using integers, the maximum amount of places we can have is 9. In the worst case,
//                     we use all 9 digits and there are 2 possibilities for each (onesPlace + K, onesPlace - K).
//                     Therefore we have N * 2^(N - 1)
// O(2^N) space: Because we use a list to store all elements, the worst case is if we have N = 9 and we have 2 options
//               for each place. Therefore space is O(2^N)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumsWithSameDifferences {

    public int[] numsWithSameConsecDiff(int N, int K) {
        // Must include zero to account for edge case where N = 1 and K = 0
        List<Integer> currentResult = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        for (int i = 2; i <= N; i++) {
            List<Integer> nextResult = new ArrayList<>();
            for (int num : currentResult) {
                int onesPlace = num % 10;
                // num > 0 to check that we don't create a number like 070
                if (num > 0 && onesPlace + K < 10)
                    nextResult.add(num * 10 + onesPlace + K);
                // K > 0 so we do not double add the same number.
                // Ex. when N = 2 and K = 0, we may double add 11, 22, 33, etc. without the K > 0 check
                if (num > 0 && K > 0 && onesPlace - K >= 0)
                    nextResult.add(num * 10 + onesPlace - K);
            }
            // The next list to check
            currentResult = nextResult;
        }
        // Convert list to array
        return currentResult.stream().mapToInt(j -> j).toArray();
    }
}
