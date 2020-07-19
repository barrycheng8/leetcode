package BitManipulation;// LeetCode 190. Reverse Bits
// Given a 32 bit unsigned integer, reverse the bits.
// Intuition: We will mask each bit from n, and bitwise-OR it with the answer, shifted to the position starting from 31.
// O(1) runtime: We will have a maximum of 32 iterations of the loop
// O(1) space: one integer used to hold the return value

public class ReverseBits {

    public static int reverseBits(int n) {
        return Integer.valueOf(new StringBuilder(String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0')).reverse().toString(), 2);
    }

    public static int reverseBitsV2(int n) {
        int ans = 0; int pow = 31; int curr = 0;

        while (curr < 32) {
            ans |= ((n >> curr) & 1) << pow;
            pow--;
            curr++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(reverseBitsV2(-3));
    }
}
