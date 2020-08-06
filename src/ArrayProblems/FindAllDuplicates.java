// LeetCode 442. Find All Duplicates in an Array
// Given an array of integers, 1 <= a[i] <= n (where n is the size of the array), some elements appear twice
// and others appear once. Find all elements that appear twice.
//
// Intuition:
// Naive approaches would include sorting (O(nlogn)) and constructing a hashmap to store number frequencies (O(n) runtime with O(n) space).
// To solve this in O(n) time using constant space, we need to take advantage of the range of numbers, which is between
// 1 and n. This guarantees that every element in the array points to a valid index if we subtract 1 from it.

package ArrayProblems;

import java.util.*;

public class FindAllDuplicates {

    // O(n) solution in 1 pass, O(1) space
    // Each element references an index in the array, i - 1
    // If num[index] is positive, that is the first time we saw the number. Invert it to negative
    // If num[index] is negative, that's the second time we saw it. Add it to the resulting list
    public List<Integer> findDuplicatesOptimal(int[] nums) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0)
                res.add(index + 1);
            else
                nums[index] = -nums[index];
        }
        return res;
    }

    // O(n) solution in 2 passes, O(n) space using hashmap
    public List<Integer> findDuplicates(int[] nums){
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 2)
                res.add(entry.getKey());
        }
        return res;
    }

    // O(nlogn) solution using sorting. O(1) space
    public List<Integer> findDuplicatesV2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums.length < 2) return res;

        Arrays.sort(nums);
        int prev = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (prev == nums[i])
                res.add(nums[i]);
            prev = nums[i];
        }
        return res;
    }
}
