package strings;

public class RotateMatrix {

    public static void rotateMatrix(int[][] m) {
        int n = m.length;
        int temp;

        for (int level = 0; level < n/2; level++) {
            int end = n - level - 1;
            for (int element = level; element < end; element++) {
                temp = m[element][end]; // store UR into temp variable
                m[element][end] = m[level][element]; // UL -> UR
                m[level][element] = m[end - element][level]; // BL -> UL
                m[end - element][level] = m[end][end - element]; // BR -> BL
                m[end][end - element] = temp; // UR -> BR
            }
        }
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
        int[][] m = {{1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}};

        printMatrix(m);
        System.out.println();

        rotateMatrix(m);
        printMatrix(m);
    }
}
