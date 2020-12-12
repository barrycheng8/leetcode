package ArrayProblems;
// LeetCode 26. Remove Duplicates from Sorted Array

public class RemoveDuplicatesFromSortedArray {

    public static int removeDuplicates(int[] nums) {
        int i = 0; int j = 0;

        while (i < nums.length) {
            while (j < nums.length && nums[i] == nums[j]) {
                j++;
            }
            if (j == nums.length)
                break;
            nums[i + 1] = nums[j];
            i += 1;
        }
        return i + 1;
    }

    public static int removeDuplicatesV2(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int check = nums[0];
        int unique = 1;
        int size = 1;
        for (int i = 1; i < nums.length; i++) {
            if (check != nums[i]) {
                nums[unique] = nums[i];
                unique++;
                size++;
                check = nums[i];
            }
        }
        return size;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 1, 2, 2, 2, 3, 3, 4};
        int[] arr2 = {1, 2, 3};
        int len = removeDuplicatesV2(arr2);

        for (int i = 0; i < len; i++) {
            System.out.print(arr2[i] + " ");
        }
    }
}
