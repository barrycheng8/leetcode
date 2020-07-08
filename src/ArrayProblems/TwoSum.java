// LeetCode 1. Two Sum
// Given an array of integers, return the indices of the numbers that add up to the specific target
// There will only be exactly 1 solution, and the element in the same index may not be used twice
// Ex. [2, 7, 11, 15], target 9: return [0, 1] (2 + 7 = 9)
// Intuition: 

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

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
