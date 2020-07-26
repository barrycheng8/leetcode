// LeetCode 258. Add Digits
// Given a non-negative intiger, repeatedly add all its digits until the result only has 1 digit
// Ex. 38 -> 3 + 8 = 11 -> 1 + 1 = 2
//
// Intuition: The naive approach would be to perform this calculation recursively or iteratively.
//
// O(1) runtime: using the digital root formula, there is a constant number of calculations independent of input
// O(1) space

public class AddDigits {

    public int addDigits(int num) {
        return addDigits(num, 0);
    }

    // If we finish summing up the digits and the sum >= 10, recurse again with nums = sum
    // If we finish summing the digits and the sum is a single digit, return it
    // Otherwise we keep recursing until we add all the digits
    public int addDigits(int num, int sum) {
        if (num == 0 && sum >= 10) return addDigits(sum, 0);
        if (num == 0 && sum < 10) return sum;
        return addDigits(num/10, sum + num%10);
    }

    // This is the same concept, except laid out iteratively
    // Keep summing the digits and divide num by 10
    // If we reach a point where we added all digits and the sum >= 10,we start over
    public static int addDigitsIterative(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;

            if (num == 0 && sum >= 10) {
                num = sum;
                sum = 0;
            }
        }

        return sum;
    }

    // This constant time solution utilizes the Digital Root formula
    // To find the digital root of an integer, we have the following 3 scenarios
    // dr(n) = 0 if n == 0
    // dr(n) = 9 if n % 9 == 0
    // dr(n) = n % 9 if n % 9 != 0
    // The simplified formula is dr(n) = 1 + (n - 1) % 9
    // We can think about it as an input output function
    //  Input: 0 1 2 3 4 5 6 7 8 9 10 11
    // Output: 0 1 2 3 4 5 6 7 8 9  1  2
    // Because this sequence repeats, and after the first sequence 0 does not appear, we simply need to find the modulo
    // of the number and add 1 to figure out what the final sum will be
    public int addDigitsConstantTime(int num) {
        if (num == 0) return 0;
        if (num % 9 == 0) return 9;
        return num % 9;
    }

    public static void main(String[] args) {
        System.out.println(addDigitsIterative(38));
    }
}
