// LeetCode 78. Backtracking.Subsets
// Given a set of distinct integers, return the power set. The solution set must not contain duplicates
// Intuition: Start from the empty set and start by adding elements, using an index pointer to keep track of where we are in the array
// Once index reaches the end, we backtrack, removing old elements and adding new ones. This ensures that we have distinct elements
// Recursion tree:             []
//                           /  |  \
//                        [1]  [2]  [3]
//                       /  \     \
//                  [1,2]   [1,3] [2,3]
//                   /
//              [1,2,3]
//
// O(2^n) runtime: A power set generates 2^n elements. Therefore our recursion stack will be at most size 2^n
// O(2^n) space: Because the power set generates 2^n elements, we need a 2-D list of similar size to hold all elements

package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        generateSubsets(ans, nums, new ArrayList<>(), 0);
        return ans;
    }

    public static void generateSubsets(List<List<Integer>> list, int[] arr, List<Integer> currSubset, int index) {
        list.add(new ArrayList<>(currSubset));

        for (int i = index; i < arr.length; i++) {
            currSubset.add(arr[i]);
            generateSubsets(list, arr, currSubset, i + 1);
            currSubset.remove(currSubset.size() - 1); // Backtrack
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println(subsets(arr));
    }
}

// Without backtracking
//[[], [1], [1, 2], [1, 2, 3], [1, 2, 3, 3], [1, 2, 3, 3, 2], [1, 2, 3, 3, 2, 3], [1, 2, 3, 3, 2, 3, 3]]

// Backtracking to remove elements
//[[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]