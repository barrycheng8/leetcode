// LeetCode 154. Find Minimum in Rotated Sorted Array II
// Given an array in sorted ascending order, but is rotated at an unknown pivot point.
// That is, [0, 1, 2, 3, 4, 5, 6, 7] could become [4, 5, 6, 7, 0, 1, 2]
// Find the minimum element. The array may contain duplicates
// Ex. [2, 2, 2, 0, 1] Ans: 0
//
// Intuition: Because we know the array is sorted in ascending order with one pivot point, we know there must be one
// index where the previous element is greater than the next element. If we look at [4, 5, 6, 7, 0, 1, 2], we scan through the array
// until we hit prev = 7 and nums[i] = 0. This is the only point where prev > nums[i]. Therefore we return nums[i] as the answer
//
// O(n) runtime: worst case the pivot is at the end of the array and we traverse n elements before finding the min value
// O(1) space
//
// The binary search method is likely what is preferred by interviewers, however, the worst case time complexity is still O(n),
// as we may need to traverse the entire array with hi-- if the array is not pivoted at all.

package ArrayProblems;

public class FindMinRotatedSortedArrayII {

    // 4 cases:
    // nums[lo] > nums[mi] <= nums[hi]: min is within (lo, mi]. Because mid is less than high, we know that half is already sorted
    // nums[lo] <= nums[mi] > nums[hi]: min is within (mi, hi]. Because lo <= mi, the that half is already sorted
    // nums[lo] <= nums[mi] <= nums[hi]: return nums[lo]. Entire array is sorted without rotation
    // nums[lo] > nums[mi] > nums[hi]: impossible in a rotation scenario
    public static int findMinBinarySearch(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        int mi;

        while (lo < hi) {
            mi = (lo + hi)/2;
            if (nums[lo] > nums[mi]) { // min within (lo, mi]
                lo++;
                hi = mi;
            }
            else if (nums[mi] > nums[hi]) // min within (mi, hi]
                lo = mi + 1;
            else
                hi--;
        }
        return nums[lo];
    }

    public static int findMin(int[] nums) {
        int prev = nums[0], index = 0;

        for (int i = 0; i< nums.length; i++) {
            if (nums[i] < prev)
                return nums[i];

            else prev = nums[i];
        }

        return nums[index];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        System.out.println(findMinBinarySearch(arr));
    }
}
