// LeetCode 463. Island Perimeter
// Given a 2-D array where 1 represents land and 0 represents water. The grid cells are only connected vertically and horizontally.
// There is only 1 island and the grid is completely surrounded by water. This implies that there are no lakes (bodies of water that
// are encapsulated by the island and are not connected to the surrounding water).
// Determine the perimeter of the island. width <= 100, height <= 100
//
// Intuition: When a 1 is detected, add its full perimeter. Check above, left, below, and right below that grid space
// and subtract 1 from the total perimeter if that area is in bounds and contains a 1. If a 0 is detected, move on.
// O(n^2) runtime: each element must be touched in the 2-D array
// O(1) space: 1 variable is used to hold the answer

public class IslandPerimeter {

    public static int islandPerimeter(int[][] grid) {
        int ans = 0;
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    ans += 4;

                    if (i - 1 >= 0 && grid[i - 1][j] == 1) // above
                        ans -= 1;
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) // left
                        ans -= 1;
                    if (i + 1 < n && grid[i + 1][j] == 1) // below
                        ans -= 1;
                    if (j + 1 < m && grid[i][j + 1] == 1) // right
                        ans -= 1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] arr = {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
        System.out.println(islandPerimeter(arr));
    }
}
