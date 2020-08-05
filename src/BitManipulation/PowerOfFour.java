// LeetCode 342. Power of Four
// Given an integer (signed 32 bits), create a function that determines if the number is a power of 4.
//
// Intuition:
// This is easy using a loop or recursion. We simply need to check if num % 4 == 0. If num has a factor of 4, we divide
// num by 4. We keep doing this until we hit num % 4 != 0 (return false), or num == 1 (return true).
//
// Creating a O(1) solution without loops will require some creativity.
// Essentially we want to do 2 bit checks based on the pattern of how multiples of 4 are represented in bits.
// 1 = 0001, 4 = 0100, 16 = 0001 0000, 64 = 0100 0000
// Notice how each power of 4 has exactly 1 bit in an even position. We can use this as our constant time check to
// determine if the number is a power of 4.
//
// Growth functions:
// O(1) runtime: optimal solution only requires 3 checks
// O(1) space: number of operations does not change with input size

package BitManipulation;

public class PowerOfFour {

    // O(log base 4 of n) runtime: we divide the problem by 4 each time. Worst case is if we have a very large number
    // that is a power of 4, we need to continue our recursion until we reach 1.
    public boolean isPowerOfFour(int num) {
        if (num == 0) return false;
        if (num == 1) return true; // Base case

        // Recurse if current number is a factor of 4
        if (num % 4 == 0) return isPowerOfFour(num/4);

        return false;
    }

    // O(1) runtime: Function performs 3 checks
    public boolean isPowerOfFourV2(int num) {
        return num > 0 && (num & (num - 1)) == 0 && ((num & 0x55555555) != 0);
        // num <= 0 cannot be a power of 4
        // Check if there is only 1 bit that has value of 1. Only 1 in the 4's position is allowed to be set
        // For example, 5 would fail the test: 101 & 100 = 1
        // For example, 4 would pass the test: 100 & 011 = 0
        // ((num & 0x55555555) != 0): Check to make sure that that 1 bit is in the proper position. A single 1 in an even
        // digit bit position guarantees the number is a power of 4.
    }
}
