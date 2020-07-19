// LeetCode 347. Top K Frequent Elements
// Given a non-empty array of integers, return the k most frequent elements
// Ex. [1, 1, 1, 1, 2, 2, 2, 3, 3, 4] k = 3
// Answer = [1, 2, 3] Answer can be returned in any order
// * Time complexity must be faster than O(n log n), where n is the size of the array. Basically, we are not allowed to sort the array
// Intuition: Scan the array in one pass and store the frequency of each number in a hashmap.
// Store each map entry <int, int> into the max heap. Poll the top k elements and return as an array
// O(n log k) run time: the priority queue (max heap) is the bottleneck. It takes O(n) time to add elements to priority queue,
// and O(log k) time to extract max k times and rebuild the pq.
// O(n) space: We use a hashmap and pq to aid us in processing the elements. Each data structure stores values given in nums

package ArrayProblems;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length <= 1) return nums;

        int[] ans = new int[k];
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(k, (a, b) -> b.getValue() - a.getValue());

        for (int n : nums) {
            if (!map.containsKey(n))
                map.putIfAbsent(n, 1);
            else
                map.put(n, map.get(n) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet())
            pq.offer(entry);

        for (int i = 0; i < k; i++)
            ans[i] = pq.poll().getValue();

        return ans;
    }
}
