package HashSetProblems;// LeetCode 957. Prison Cells After N Days
// Each day, an array of 8 cells gets rearranged with the following rules:
// 1. If a cell has 2 adjacent neighbors that are both occupied (1) or vacant (0), the cell becomes occupied
// 2. Otherwise, it becomes vacant. (The side 2 cells will always become vacant
//
// Intuition:
// A naive solution would scale with the size of N. If N was sufficiently large, we would have many iterations.
// There are only 2^6 (the edge cells will always end up as 0), or 64 configurations total.
// We create a HashSet that caches previously found configurations. If the same iteration is found, we know
// the resulting config will transform to another config already seen in the cached results.
// We record the length of the cycle and calculate N % cycleLength to determine where our answer lies
// in the cycle.
// O(1) runtime: The maximum loops iterations is 64
// O(1) space: the maximum amount of elements in the set is 64

import java.util.Arrays;
import java.util.HashSet;

public class PrisonAfterNDays {

    public static int[] prisonAfterNDays(int[] cells, int N) {
        if (N <= 0) return cells;

        HashSet<String> set = new HashSet<>();
        boolean hasCycle = false;
        int cycleLength = 0;

        for (int i = 0; i < N; i++) {
            int[] next = nextDay(cells);
            String nextString = Arrays.toString(next);

            if (!set.contains(nextString)) {
                set.add(nextString);
                cycleLength++;
            }
            else {
                hasCycle = true;
                break;
            }
            cells = next;
        }

        if (hasCycle) {
            N %= cycleLength;
            for (int i = 0; i < N; i++) {
                cells = nextDay(cells);
            }
        }

        return cells;
    }

    static int[] nextDay(int[] cells) {
        int[] next = new int[cells.length];

        for (int i = 1; i < next.length - 1; i++)
            next[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;

        return next;
    }


    // Iterative method too slow
    /*
    public static int[] prisonAfterNDays(int[] cells, int N) {

        for (int i = 0; i < N; i++) {
            nextDay(cells);
        }
        return cells;
    }

    static void nextDay(int[] cells) {
        int[] origCells = Arrays.copyOf(cells, 8);
        for (int i = 0; i < cells.length; i++) {
            if (i == 0 || i == 7) cells[i] = 0;
            else {
                if ((origCells[i - 1] == 1 && origCells[i + 1] == 1) || (origCells[i - 1] == 0 && origCells[i + 1] == 0)) {
                    cells[i] = 1;
                }
                else
                    cells[i] = 0;
            }
        }
    }
    */
    public static void main(String[] args) {
        int[] start = {0, 1, 0, 1, 1, 0, 0, 1};

        int[] ans = prisonAfterNDays(start, 7);

        System.out.println(Arrays.toString(ans));
    }
}
