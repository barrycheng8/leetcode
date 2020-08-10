// LeetCode 994. Rotting Oranges
// Given a grid, each cell can represent one of 3 values
// 0 = empty cell       1 = fresh orange        2 = rotten orange
// Every minute, a fresh orange that is adjacent (up, down, left, right) becomes rotten.
// Return the minimum number of minutes that must elapse so that no cell has a fresh orange. If this is impossible, return -1;
//
// Intuition:
// Use BFS to modify the grid and determine the configuration after each day. When all fresh oranges a rotten, we return the answer
//
// Growth Functions:
// O(n) runtime: each cell is visited a maximum of 2 times. Once to check if the orange is rotten or fresh. A second time
//               to process the rotten orange and change a fresh orange to rotten.
// O(n) space: The amount of cells in the queue scales with the size of the grid

package BFS;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {

    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        int freshCount = 0;

        // Figure out which cells have rotten oranges and add them to the queue
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2)
                    q.offer(new int[] {i, j});
                // Count how many fresh oranges we need to rot
                else if (grid[i][j] == 1)
                    freshCount++;
            }
        }

        // Edge case where everything was already rotten
        if (freshCount == 0) return 0;
        int time = 0;
        // Possible directions we can go in x/y
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        // Keep iterating if there are new rotten oranges we have not processed
        while (!q.isEmpty()) {
            time++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int coord[] = q.poll(); // The coordinate where the rotten orange is
                for (int[] dir : directions) {
                    // Calculate the position we need to rot.
                    // x is actually the row axis and y is the column axis
                    int x = coord[0] + dir[0];
                    int y = coord[1] + dir[1];

                    // If coordinate violates bounds or is not a fresh orange, skip it
                    if (x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] != 1) continue;

                    grid[x][y] = 2; // Rot the orange
                    q.offer(new int[] {x, y}); // Add the new rotten orange to the queue to rot its neighbors
                    freshCount--;
                    if (freshCount == 0) return time; // All oranges are rotten. Short-circuit and return
                }
            }
        }
        // If we reach this case, then freshCount > 0. It is impossible to rot all oranges
        return -1;
    }
}
