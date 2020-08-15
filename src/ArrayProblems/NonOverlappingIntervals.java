// LeetCode 435. Non-overlapping Intervals
// Given a collection of intervals, find the minimum number of intervals ou need to remove to make the rest of the
// intervals non-overlapping
//
// Intuition:
// Sorting the intervals will allow us to sequentially figure out which intervals overlap each other
// Because we want to find out the minimum to remove, we can invert it and figure out the maximum amount of valid intervals
// we can find without overlap. We then subtract total intervals from the maxValidIntervals to find out minIntervalsToRemove.
//
// Growth Functions:
// O(nlogn) runtime: runtime is bound by sorting the interval array
// O(1) space: no other data structures are used

package ArrayProblems;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {

    public static int eraseOverlapIntervals(int[][] intervals) {
        // Sort by end of interval
        // This allows us to find the maximum amount of intervals that will be kept in the end
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));

        int hi = intervals[0][1];
        int validIntervals = 1; // First interval is always valid

        // Intervals that expand the current smallest interval are valid. We then save our expansion value
        // Intervals that overlap into a previous interval are not counted
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= hi) {
                hi = intervals[i][1];
                validIntervals++;
            }
        }

        // Subtract to find out how many intervals to remove
        return intervals.length - validIntervals;
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2}, {2, 3}, {3, 4}, {1, 3}}; // 1
        int[][] arr1 = {{1, 2}, {1, 2}, {1, 2}};        // 2
        int[][] arr2 = {{1, 2}, {2, 3}};                // 0

        // Need to sort by first element in subarray, then 2nd value in subarray
        int[][] arr3 = {{1, 100}, {11, 22}, {1, 11}, {2, 12}};  // 2
        System.out.println(eraseOverlapIntervals(arr3));
    }
}
