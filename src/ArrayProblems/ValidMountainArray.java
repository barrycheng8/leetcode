// LeetCode 941. Valid Mountain Array (Easy)
package ArrayProblems;

public class ValidMountainArray {

    // O(n) solution
    public boolean validMountainArray(int[] arr) {
        if (arr.length < 3) return false;

        int diff;
        boolean dec = false;
        boolean inc = false;

        for (int i = 1; i < arr.length; i++) {
            diff = arr[i] - arr[i - 1];

            if (diff == 0) return false;
            if (diff > 0) inc = true;
            if (!dec && diff < 0) dec = true;
            if (dec && diff > 0) return false;
        }
        return inc && dec;
    }

    // O(n/2) solution
    // 2 pointers at each end of the array
    // Increment/decrement until i and j converge, ensuring that:
    //      i is increasing
    //      j is decreasing
    // Final check ensures that pointers meet and they moved from original spot
    public static boolean validMountainArrayV2(int[] arr) {
        if (arr.length < 3) return false;

        int i = 0, j = arr.length - 1;
        while (i < j &&  arr[i] < arr[i + 1]) i++;
        while (j > i && arr[j] < arr[j - 1]) j--;
        return i == j && i != 0 && j != arr.length - 1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 5};

        System.out.println(validMountainArrayV2(arr));
    }
}
