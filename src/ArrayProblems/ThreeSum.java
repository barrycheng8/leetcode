import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ThreeSum {

    // O(n^2) runtime, but exceeds time limit. We may need to reduce the amount of times we sort
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
        System.out.println(threeSum(arr));
    }
}
