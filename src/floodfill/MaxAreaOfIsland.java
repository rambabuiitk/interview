package floodfill;

import java.util.Arrays;

public class MaxAreaOfIsland {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}
        };
        System.out.println("Input: " + Arrays.deepToString(matrix));
        int output = maxAreaOfIsland(matrix);
        System.out.println("Output: " + output);
    }


    private static int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int totalRows = grid.length;
        int totalCols = grid[0].length;
        int maxArea = 0;
        // used to calculate total number of islands
        // iterate over all points of the grid and if is an island watch neighbouring
        for (int startRow = 0; startRow < totalRows; startRow++) {
            for (int startCol = 0; startCol < totalCols; startCol++) {
                if (grid[startRow][startCol] == 1) {
                    int currentArea = numIslandsRecursive(grid, startRow, startCol);
                    maxArea = Math.max(currentArea, maxArea);
                }
            }
        }
        return maxArea;
    }


    // Basically using Flood Fill Algorithm
    private static int numIslandsRecursive(int[][] grid,
                                           int currentRow,
                                           int currentCol) {

        if (currentRow < 0 || currentRow >= grid.length
                || currentCol < 0 || currentCol >= grid[0].length
                || grid[currentRow][currentCol] == 0) {
            return 0;
        }
        // resetting the visited point to 0 so that we do not revisit and re update

        grid[currentRow][currentCol] = 0;
        return 1 + numIslandsRecursive(grid, currentRow - 1, currentCol)
                + numIslandsRecursive(grid, currentRow + 1, currentCol)
                + numIslandsRecursive(grid, currentRow, currentCol - 1)
                + numIslandsRecursive(grid, currentRow, currentCol + 1);
    }


}
