// LeetCode 66. Plus One
// Given a non-empty array of digits representing a non-negative integer, add 1 to the integer.
// The number is stored with the most significant digit at the end.
// Ex. [1, 2, 3] + 1 = 123 + 1 = 124 = [1, 2, 4]
// Intuition: Essentially want to increment the last digit by 1. If it is a 9, we need to increment the next digit to the left
// by 1. If the entire array is 9's, we need to initialize a new array of larger size to hold the new number
// O(n) runtime: worst case we run through the entire array if all numbers are 9
// O(n) space: worst case we run through the entire array and initialize a new array to hold the result

package ArrayProblems;

public class PlusOne {

    public int[] plusOne(int[] digits) {
        int i = digits.length - 1;

        while (i >= 0) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            else {
                digits[i] = 0;
                i--;
            }
        }

        // If we reached here, that means all digits were 9.
        // Create a new larger array to store the new number
        int[] ans = new int[digits.length + 1];
        ans[0] = 1;

        return ans;
    }
}
