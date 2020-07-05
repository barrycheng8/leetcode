// LeetCode 461. Hamming Distance
// Find the Hamming Distance between two numbers (the # of places where the bits are different)
// Intuition: Mask each bit out in the LSB spot and XOR to check for any differences
// Use 'dist' to count total differences
// O(1) runtime: There are 32 bits to check
// O(1) space: The space does not grow with input size

package BitManipulation;

public class HammingDistance {

    public int hammingDistance(int x, int y) {
        int dist = 0;

        for (int i = 0; i < 32; i++) {
            if ((((x >> i) & 1) ^ ((y >> i) & 1)) != 0)
                dist++;
        }
        return dist;
    }
}
