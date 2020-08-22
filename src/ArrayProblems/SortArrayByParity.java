// LeetCode 905. Sort Array By Parity
// Given an array A of non-negative integers, return an array consisting of all even elements of A at the front, and all
// odd elements at the back. Any combination that satisfies the condition will be accepted.
//
// Example:
// Input: [3, 1, 2, 4]
// Output: [2, 4, 3, 1] (other combinations of this format also work)
//
// Intuition:
// Using two pointers, we can traverse the array using two pointers.
// i starts from the front, j starts from the back. Swap if i is odd and j is even. Otherwise, decrement each pointer
// individually until the swap condition is met.


package ArrayProblems;

public class SortArrayByParity {

    // O(n) runtime: worst case is one pass through the array, converging towards the center with two pointers
    // O(1) space: 2 pointers used
    public int[] sortArrayByParityV2(int[] A) {
        int i = 0;
        int j = A.length - 1;

        while (i < j) {
            if (A[i] % 2 == 0)  // i is even. no need to swap it
                i++;
            else { // i is odd
                if (A[j] % 2 == 0) { // j is even. swap
                    swap(A, i, j);
                    i++;
                }
                j--;    // j is odd. decrement j to find the next even number
            }
        }
        return A;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // O(n^2) runtime: worst case all the odd numbers are at the front and all the even numbers are at the back.
    // Then we would have to search through half the array to find the next even number to swap to the front
    public int[] sortArrayByParity(int[] A) {
        if (A.length < 2) return A;

        for (int i = 0; i < A.length; i++) {
            int idx = i;
            if (A[i] % 2 != 0) { // if number is odd
                while (idx < A.length && A[idx] % 2 != 0) { // search for next even number ahead of odd number
                    idx++;
                }
                if (idx < A.length) { // swap it
                    int temp = A[i];
                    A[i] = A[idx];
                    A[idx] = temp;
                }
            }
        }
        return A;
    }
}
