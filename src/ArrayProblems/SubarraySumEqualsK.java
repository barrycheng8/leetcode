// LeetCode 560. Subarray Sum Equals K
// Given an array of integers and an integer k, find the total amount of continuous subarrays whose sum = k.
//
// Intuition:
// Naive approach would be to use 2 for loops and check all possible subarrays to see if they sum up to k
// A better approach would be to use O(n) space and cache previous sums in a hashmap. We then sum up to the current
// index and check if we can create a sum = k with previously cached results
//
// Growth functions:
// O(n) runtime: one pass through the array to find all possible sums from subarrays
// O(n) space: hashmap space to store possible sums scales with the size of the array

package ArrayProblems;
import java.util.HashMap;

public class SubarraySumEqualsK {

    public int subarraySumMap(int[] nums, int k) {
        if (nums.length == 0) return 0;

        int sum = 0; int result = 0;
        // { sum from [i, j]: freq }
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1); // empty array as a subarray is one solution

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; // the sum up until current index

            // k = currentSum - prevSum
            // prevSum = currentSum - k
            // We use sum - k to search for a previous sum in the array that would satisfy k
            // We add the total subarrays that added up to k
            if (preSum.containsKey(sum - k)) {
                result += preSum.get(sum - k);
            }
            // Cache current sum into hashmap
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }

        return result;
    }

    // O(n^2) runtime, O(1) space
    // 355 ms
    public int subarraySumNaive(int[] nums, int k) {
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k)
                    result++;
            }
        }
        return result;
    }

    // Looks like a sliding window approach won't work...
    // For test case {0, 0, 0, 0}, this approach will only detect 4 subarrays due to the outer for loop
    // The correct answer is actually 2^4 {0}, {0,0}, {0,0,0}, {0,0,0,0}, etc.
    public static int subarraySum(int[] nums, int k) {
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int total = k;
            if (nums[i] == k) {
                ans++;
                continue;
            }
            else
                total -= nums[i];

            while (total != 0 && j < nums.length) {
                total -= nums[j];
                j++;
                if (total == 0)
                    ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {100, 1, 2, 3, 100, 1, 2, 3, 4};
        int[] arr1 = {0, 0, 0, 0};
        System.out.println(subarraySum(arr1, 0));
    }
}
