// LeetCode 67. Add Binary
// Given two binary strings return their sum as a binary string
// Ex. a = "11", b = "1" output = "100"
// Loop over all bits for strings a and b. Extract the value and add it to the running sum at each place (one's, two's, etc.)
// Because we only have 4 options, we add sum % 2 to the answer, building from left to right.
// Set the residual carry value to what is leftover from the sum after adding it to the answer string
// O(Max(a, b)) runtime: the run time is determined by whichever string has a longer length.
// StringBuilder append has an amortized time complexity of O(1). It is a char[] under the cover.
// O(Max(a, b)) space: whichever bit string is longer will determine the size of answer

package BitManipulation;

public class AddBinary {

    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0, sum;

        while (i >= 0 || j >= 0) { // Iterate over all numbers
            sum = carry; // Add in carry from previous iteration
            if (i >= 0) sum += a.charAt(i--) - '0'; // Add the binary value if it exists
            if (j >= 0) sum += b.charAt(j--) - '0';

            // Only 4 options here:
            // sum = 0, put a 0, carry = 0
            // sum = 1, put a 1, carry = 0
            // sum = 1 + 1, put a 0, carry = 1
            // sum = 1 + 1 + 1, put a 1, carry = 1
            ans.append(sum % 2);
            carry = sum / 2;
        }

        // If we finish adding everything and we have a residual carry
        if (carry == 1) ans.append(1);

        return ans.reverse().toString();
    }
}
