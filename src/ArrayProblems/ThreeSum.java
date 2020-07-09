// LeetCode 15. Three Sum
// Given an array of nums, find elements a, b, c where a + b + c = 0. Add all unique triplets into a list of lists.
// Ex. [-1, 0, 1, 2, -1, -4] return: [[-1, 0, 1], [-1, -1, 2]]
// Intuition: Essentially we want to reduce this to a 2 sum problem.
// We have pointer i traverse through the array, and then two pointers left and right that traverse through the remaining elements.
// First, we want to ensure that each iteration of the outer loop checks new elements. This ensures that anything we add to ans is unique.
// If i + left + right == 0, we add it to ans. right-- and left++ while skipping duplicates.
// If i + left + right < 0, left++
// If i + left + right > 0, right--;
// O(n^2) runtime: traverse n - 2 elements with the outer loop, traverse n elements with the inner loop using 2 pointers
// O(n) space: The amount of solutions is some factor of the total elements

package ArrayProblems;

import java.util.*;

public class ThreeSum {

    public static List<List<Integer>> threeSumV2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length < 3) return ans;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                while (left < right) {
                     if (nums[i] + nums[left] + nums[right] == 0) {
                         ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                         while (left < right && nums[left + 1] == nums[left]) left++;
                         while (left < right && nums[right - 1] == nums[right]) right--;
                         right--;
                         left++;
                     }
                     else if (nums[i] + nums[left] + nums[right] < 0) left++;
                     else right--;
                }
            }
        }
        return ans;
    }


    // n iterations of outer loop
    // n iterations of inner loop
    // n iterations worst case to check if the result has previously recorded answers
    // Roughly O(n^3) amortized runtime. Time Limit Exceeded.
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length < 3) return ans;

        int value = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.get(nums[i]) - 1); // Decrement current num
            int searchVal = value - (nums[i]);
            for (int j = 0; j < nums.length; j++) {
                if (i == j) continue; // skip over the current num
                List<Integer> res = new ArrayList<>();
                map.put(nums[j], map.get(nums[j]) - 1); // decrement inner num
                if (map.containsKey(searchVal - nums[j]) && map.get(searchVal - nums[j]) > 0) {
                    res.add(nums[i]);
                    res.add(nums[j]);
                    res.add(searchVal - nums[j]);
                }
                Collections.sort(res);
                // This is an O(n) operation, which racks up the total runtime
                if (!ans.contains(res) && res.size() > 0) // ensure we do not add empty arrays as a result
                    ans.add(res);

                map.put(nums[j], map.get(nums[j]) + 1); // reincrement inner num
            }
            map.put(nums[i], map.get(nums[i]) + 1); // reincrement current num
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        int[] arr2 = {-2, 0, 0, 2, 2};
        //System.out.println(threeSumV2(arr));
        System.out.println(threeSumV2(arr2));
    }
}
