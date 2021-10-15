package floodfill;

import java.util.Arrays;

public class FloodFill {
    public static void main(String[] args) {
        int[][] input = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 1}
        };
        int startRow = 1;
        int startCol = 1;
        int newColor = 2;
        System.out.println("Input: " + Arrays.deepToString(input));
        System.out.println("Start Row: " + startRow);
        System.out.println("Start Column: " + startCol);
        System.out.println("New Color: " + newColor);
        int[][] output = floodFill(input, startRow, startCol, newColor);
        System.out.println("Output: " + Arrays.deepToString(output));
    }

    private static int[][] floodFill(int[][] grid, int startRow, int startCol, int newColor) {
        // if the grid is null or empty or if the selected pointed is already in newColor
        if (grid == null || grid.length == 0 || grid[startRow][startCol] == newColor) {
            return grid;
        }
        floodFillRecursive(grid, startRow, startCol, grid[startRow][startCol], newColor);
        return grid;
    }

    private static void floodFillRecursive(int[][] grid,
                                           int currentRow,
                                           int currentCol,
                                           int oldColor,
                                           int newColor) {

        if (currentRow < 0 || currentRow >= grid.length
                || currentCol < 0 || currentCol >= grid[0].length
                || grid[currentRow][currentCol] != oldColor) {
            return;
        }

        grid[currentRow][currentCol] = newColor;

        floodFillRecursive(grid, currentRow + 1, currentCol, oldColor, newColor);
        floodFillRecursive(grid, currentRow - 1, currentCol, oldColor, newColor);
        floodFillRecursive(grid, currentRow, currentCol + 1, oldColor, newColor);
        floodFillRecursive(grid, currentRow, currentCol - 1, oldColor, newColor);
    }

}
