// LeetCode. 274. H-Index
// Given an array of citations (non-negative integers) of a researcher, write a function to compute the h-index.
// The h-index is calculated with hte following definition:
// 1. At least h of the N papers must have at least h citations
// 2. The other N - h papers have no more than h citations
//
// Example:
// Input: [3, 0, 6, 1, 5]       Output: 3
// The researcher has at least 3 papers that have more than 3 citations (3, 5, 6)
// The researcher has no more than 3 papers with less than 3 citations (0, 1)
// ***FINISH EXPLANATION LATER*** 8/11/2020

package ArrayProblems;

import java.util.Arrays;

public class HIndex {

    // Sort the citations from low to high
    //
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int N = citations.length;
        int index = 1;

        while (index <= N) {
            if (citations[N - index] >= index)
                index++;
            else
                break;
        }
        return index - 1;
    }

    public int hIndexBucket(int[] citations) {
        int N = citations.length;
        int[] buckets = new int[N + 1];
        for (int c : citations) {
            if (c >= N)
                buckets[N]++;
            else
                buckets[c]++;
        }

        int count = 0;
        for (int i = N; i >= 0; i--) {
            count += buckets[i];
            if (count >= i)
                return i;
        }
        return 0;
    }
}
