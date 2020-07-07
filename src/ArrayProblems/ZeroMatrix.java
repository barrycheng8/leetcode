// CTCI 1.7
// O(n^2) time: each element must be touched
// O(1) space: stores the zeros in place of the original matrix

package strings;

import java.util.ArrayList;

public class ZeroMatrix {

    public static void zeroMatrixV2(int[][] m) {
        boolean firstRowZero = false;
        boolean firstColZero = false;

        for (int i = 0; i < m[0].length; i++) {
            if(m[0][i] == 0) {
                firstRowZero = true;
                break;
            }
        }

        for (int i = 0; i < m.length; i++) {
            if (m[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < m[i].length; j++) {
                if (m[i][j] == 0) {
                    m[i][0] = 0;
                    m[0][j] = 0;
                }
            }
        }

        for (int i = 0; i < m.length; i++) {
            if (m[i][0] == 0)
                zeroRow(m, i);
        }

        for (int i = 0; i < m[0].length; i++) {
            if (m[0][i] == 0)
                zeroColumn(m, i);
        }

        if (firstRowZero)
            zeroRow(m, 0);

        if (firstColZero)
            zeroColumn(m, 0);
    }

    public static void zeroMatrix(int[][] m) {
        ArrayList<Index> zeros = new ArrayList<>();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == 0)
                    zeros.add(new Index(i, j));
            }
        }

        for (int i = 0; i < zeros.size(); i++) {
            zeroColumn(m, zeros.get(i).col);
            zeroRow(m, zeros.get(i).row);
        }
    }

    public static void zeroRow(int[][] m, int row) {
        for (int i = 0; i < m[row].length; i++)
            if(m[row][i] != 0) m[row][i] = 0;
    }

    public static void zeroColumn(int[][] m, int column) {
        for (int i = 0; i < m.length; i++)
            if (m[i][column] != 0) m[i][column] = 0;
    }

    public static void printMatrix(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] m = {{1, 2, 3, 4}, {1, 0, 2, 3}, {1, 2, 1, 0}};
        int[][] n = {{1, 2, 3, 4}, {1, 0, 2, 3}, {1, 2, 1, 0}, {1, 1, 1, 1}};
        printMatrix(m);
        System.out.println();
        zeroMatrix(m);
        printMatrix(m);

        System.out.println();

        printMatrix(n);
        System.out.println();
        zeroMatrixV2(n);
        printMatrix(n);
    }
}

class Index {
    int row;
    int col;

    public Index (int row, int col) {
        this.row = row;
        this.col = col;
    }
}