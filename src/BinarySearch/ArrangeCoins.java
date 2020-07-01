package BinarySearch;// LC 441
// Can naively be solved using recursion, which is a slow roughly O(logn) solution.
// A better implementation uses binary search

public class ArrangeCoins {

    // We must return the row of the full triangle made of coins that is less than or equal the amount of coins given n
    // Using Gauss' formula (to find the number of coins used to create the stair) and binary search (to find the # of
    // stairs less than or equal to the coins given), we can converge on the solution
    // Because we want to return the last row where the stair is FULL, return 'high', because that will always be lower
    // when we exit the loop.
    //
    // Values must be type long, because we are adding the two values and finding the mid. Otherwise we result in
    // overflow and infinite looping
    public static int arrangeCoins(int n) {
        long low = 0;
        long high = n;
        long mid, coins;

        while (low <= high) {
            mid = (low + high)/2;
            coins = (mid * (mid + 1))/2;

            if (coins == n) return (int)mid;

            if (coins < n) low = mid + 1;
            else high = mid - 1;
        }
        return (int)high;
    }

    public static void main(String[] args) {
        System.out.println(arrangeCoins(1804289383));
    }
}
