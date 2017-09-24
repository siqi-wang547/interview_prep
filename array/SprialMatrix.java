package array;
import java.util.*;

public class SprialMatrix {

    /**
     * LC54. Spiral Matrix
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if (matrix.length == 0 || matrix[0].length == 0) return res;
        int m = matrix.length, n = matrix[0].length;
        // System.out.printf("m = %d, n = %d\n", m, n);

        int startRow = 0, endRow = m - 1;
        int startCol = 0, endCol = n - 1;

        while (startRow <= endRow && startCol <= endCol) {
            for (int i = startCol; i <= endCol; i++) res.add(matrix[startRow][i]);
            startRow++;
            for (int i = startRow; i <= endRow; i++) res.add(matrix[i][endCol]);
            endCol--;
            if (startRow <= endRow) { // important, otherwise for [[1,2,3]], it will print 12321
                for (int i = endCol; i >= startCol; i--) res.add(matrix[endRow][i]);
                endRow--;
            }
            if (startCol <= endCol) {
                for (int i = endRow; i >= startRow; i--) res.add(matrix[i][startCol]);
                startCol++;
            }
        }

        return res;
    }

    /**
     * LC59. Spiral Matrix II
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        int startRow = 0, endRow = n - 1;
        int startCol = 0, endCol = n - 1;
        int ele = 1;
        while (startRow <= endRow && startCol <= endCol) {
            for (int i = startCol; i <= endCol; i++) matrix[startRow][i] = ele++;
            startRow++;
            for (int i = startRow; i <= endRow; i++) matrix[i][endCol] = ele++;
            endCol--;
            if (startRow <= endRow) {
                for (int i = endCol; i >= startCol; i--) matrix[endRow][i] = ele++;
                endRow--;
            }
            if (startCol <= endCol) {
                for (int i = endRow; i >= startRow; i--) matrix[i][startCol] = ele++;
                startCol++;
            }
        }
        return matrix;
    }
}
