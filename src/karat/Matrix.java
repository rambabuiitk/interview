package karat;

import java.util.HashSet;
import java.util.Set;

public class Matrix {

    public boolean isValidMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            Set<Integer> rowSet = new HashSet<>();
            Set<Integer> colSet = new HashSet<>();
            int rowMin = Integer.MAX_VALUE, rowMax = Integer.MIN_VALUE;
            int colMin = rowMin, colMax = rowMax;
            for (int j = 0; j < n; j++) {
                if (!rowSet.contains(matrix[i][j])) {
                    rowSet.add(matrix[i][j]);
                    rowMin = Math.min(rowMin, matrix[i][j]);
                    rowMax = Math.max(rowMax, matrix[i][j]);
                } else {
                    return false;
                }
                if (!colSet.contains(matrix[j][i])) {
                    colSet.add(matrix[j][i]);
                    colMin = Math.min(colMin, matrix[j][i]);
                    colMax = Math.max(colMax, matrix[j][i]);
                } else {
                    return false;
                }
            }
            if (rowMin != 1 || colMin != 1 || rowMax != n || colMax != n) {
                return false;
            }
        }
        return true;
    }

}
