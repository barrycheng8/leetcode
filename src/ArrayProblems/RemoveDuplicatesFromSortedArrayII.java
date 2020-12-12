package ArrayProblems;

// LeetCode 80. Remove Duplicates from Sorted Array II (medium)
public class RemoveDuplicatesFromSortedArrayII {

    public int removeDuplicates(int[] nums) {
        if (nums.length < 3) return nums.length;

        // m = end of the array
        int count = 1, m = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                // assign next number to position m if not a triplicate
                if (count < 2) nums[m++] = nums[i];
                // otherwise keep incrementing count, keeping m at same position where the next
                // number should substitute
                count++;
            }
            else {
                // number was different, reset counter
                count = 1;
                // Assign number starting from where m was
                nums[m++] = nums[i];
            }
        }
        return m;
    }
}
