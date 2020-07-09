// LeetCode 1. Two Sum
// Given an array of integers, return the indices of the numbers that add up to the specific target
// There will only be exactly 1 solution, and the element in the same index may not be used twice
// Ex. [2, 7, 11, 15], target 9: return [0, 1] (2 + 7 = 9)
// Intuition: The operands in a sum can always be switched around, i.e. 2 + 7 = 7 + 2
// Using this idea, we traverse through the array scanning the map for a number that complements the current num to reach the target.
// If no such number exists, we add it to the map, mapping num to index. Once we find a matching pair, the index
// found with the map will always be less than the current index. therefore we set ans to [target-nums[i] index, curr index]
//
// O(n) runtime: worst case we traverse through the entire array once
// O(n) space: worst case we traverse through the array and add all elements to the map without obtaining an answer

package ArrayProblems;
import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

    // This can also be solved by sorting and using 2 pointers
    public static int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        // {value: position in array}
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                ans[0] = map.get(target - nums[i]);
                ans[1] = i;
                return ans;
            }
            map.put(nums[i], i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 4};
        System.out.println(Arrays.toString(twoSum(arr, 6)));
    }
}
