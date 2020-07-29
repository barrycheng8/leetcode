// LeetCode 260. Single Number III
// Griven an array of nums, exactly 2 elements appear only once, and all other elements appear exactly twice.
// Find the two elements that appear only once.
// Ex. [1, 2, 1, 3, 2, 5] Ans: [3, 5]
//
// Intuition: Naive solution would be to sort all the numbers and check sequentially to see which numbers only appear once.
// In order to run in linear time, we can store each element in a HashMap/HashSet in one traversal.
// Traverse the map's entries and check which ones only appear once, and add it to the answer array. This would take O(n) space.
// In order to use constant space, we will need to do some bit manipulation.
// The optimal solution:
// 1. Traverse through the array XORing all elements. This will result in number that is an XOR of the two unique elements
// 2. The XOR of the two unique elements (let's call it diff) will have 1's in the bit positions where the two nums differ
// 3. All we need to know is the right most bit that differs. Then we can segregate the numbers into two groups.
// 4. We use diff &= -diff to reveal that right most bit
// 5. We then separate the numbers into two categories, nums where that right most bit is set, and nums that don't have that right most bit set
// 6. XOR all the nums in those separate categories. The duplicates will cancel each other out, and we will be left with the singlets in each group.
//
// O(n) runtime: 2 traversals through the array
// O(1) space: space used is independent of input

package ArrayProblems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SingleNumberIII {

    public int[] singleNumberV2(int[] nums) {
        int[] ans = new int[2];
        int bits = 0;

        for (int n : nums) {
            bits ^= n;
        }

        bits &= -bits;

        for (int n : nums) {
            if ((bits & n) == 0)
                ans[0] ^= n;
            else
                ans[1] ^= n;
        }

        return ans;
    }

    public int[] singleNumber(int[] nums) {
        int[] ans = new int[2];
        Arrays.fill(ans, -1);
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int n : nums)
            map.put(n, map.getOrDefault(n, 0) + 1);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == -1)
                ans[0] = entry.getKey();
            else
                ans[1] = entry.getKey();
        }
        return ans;
    }


}
