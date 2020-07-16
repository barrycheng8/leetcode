// LeetCode 50. Pow(x, n)
// Implement pow(x,n) = x^n. The catch is we need to cover edge cases where the integer passed in is either MIN/MAX value.
// Intuition: We can break down the problem by multiplying the return value the amount of times it takes for power to reach 0.
// To do it optimally, we can break the problem in half at each recursive call, resulting in a log(n) solution
// O(log n) runtime: Power is divided by 2 at each recursive call
// O(1) space

public class Pow {

    // This is basically cheating
    public static double myPow(double x, int n) {
        return Math.pow(x, n);
    }

    // This recursive solution works, but is too slow when n is MAX_VALUE or MIN_VALUE
    // O(n) runtime, O(1) space
    public static double myPowV2(double x, int n) {
        double ans = 1.0;

        if (n == 0) return ans;

        if (n > 0) {
            ans *= x * myPow(x, n - 1);
        } else {
            ans /= x / myPow(x, n + 1);
        }

        return ans;
    }

    // Optimal recursive solution
    // O(log n) run time. Problem is divided by half at each recursive call
    // O(1) space
    public static double myPowV3(double x, int n) {
        if (n == 0) return 1.0;

        if (n < 0) {
            if (n == Integer.MIN_VALUE) { // -2147483648
                n = Integer.MAX_VALUE; // 2147483647. One iteration is effectively removed
                x *= x; // We use this operation to compensate for the one iteration removed upon conversion
            }
            else
                n = -n;
            x = 1/x;
        }

        return (n % 2 == 0) ? myPow(x * x, n/2) : x * myPow(x * x, n/2);
    }

    public static void main(String[] args) {
        //System.out.println(myPowV2(2, 10));
        //System.out.println(myPowV2(2.1, 3));
        //System.out.println(myPowV2(2, -2));
        //System.out.println(myPowV2(2.1, 0));
        System.out.println(myPowV2(-1, -2147483648));

        // 1024.0
        // 9.261000000000001
        // 0.25
        // 1.0
    }
}
