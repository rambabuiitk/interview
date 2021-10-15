package karat;

public class Nonogram {

    public boolean isValidNonogram(int[][] matrix, int[][] rows, int[][] cols) {
        if (matrix == null || rows == null || cols == null) {
            return false;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        if (n == 0 || n != rows.length || m != cols.length) {
            return false;
        }
        return (
                isNonogramRowsValid(matrix, rows, n, m) &&
                        isNonogramColsValid(matrix, cols, n, m)
        );
    }

    public boolean isNonogramRowsValid(int[][] matrix, int[][] rows, int n, int m) {
        for (int i = 0; i < n; i++) {
            int rowIndex = 0;
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    if (rows[i].length == 0) {
                        return false;
                    }
                    for (int k = 0; k < rows[j][rowIndex]; k++) {
                        if (j + k >= m || matrix[i][j + k] != 0) {
                            return false;
                        }
                    }
                    j += rows[i][rowIndex++];
                }
            }
            if (rowIndex != rows[i].length) {
                return false;
            }
        }
        return true;
    }

    public boolean isNonogramColsValid(int[][] matrix, int[][] cols, int n, int m) {
        for (int i = 0; i < m; i++) {
            int colIndex = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[j][i] == 0) {
                    if (cols[i].length == 0) {
                        return false;
                    }
                    for (int k = 0; k < cols[j][colIndex]; k++) {
                        if (j + k >= n || matrix[j + k][i] != 0) {
                            return false;
                        }
                    }
                    j += cols[i][colIndex++];
                }
            }
            if (colIndex != cols[i].length) {
                return false;
            }
        }
        return true;
    }
}
