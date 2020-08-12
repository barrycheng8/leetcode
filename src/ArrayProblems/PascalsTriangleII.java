// LeetCode 119. Pascal's Triangle II
// Given a non-negative index k, where k <= 33, return the k-th index row of Pascal's Triangle.
// The row index starts from 0.
//
// Pascal's Triangle:
//              1
//             1 1
//            1 2 1     1 + 1 = 2
//           1 3 3 1    1 + 2 = 3
//          1 4 6 4 1   1 + 3 = 4, 3 + 3 = 6
//
// Intuition:
// To calculate the next row of Pascal's Triangle, we add up the index values from the previous rows.
//
// O(k) runtime: Runtime scales with rowIndex
// O(k) space: Because we replace the previous row values when we calculate a new row, we never incur more than O(k) space

package ArrayProblems;

import java.util.LinkedList;
import java.util.List;

public class PascalsTriangleII {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new LinkedList<>();
        res.add(1); // Initialize triangle

        if (rowIndex == 0) return res;

        for (int i = 1; i <= rowIndex; i++) {
            res.add(0, 1); // Add a 1 at the beginning of each new row
            for (int j = 1; j < res.size() - 1; j++) {
                // Replace current position's number with itself + the next value
                res.set(j, res.get(j) + res.get(j + 1));
            }
        }
        return res;
    }
}
