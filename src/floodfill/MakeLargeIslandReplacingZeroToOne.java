package floodfill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MakeLargeIslandReplacingZeroToOne {
    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 0}, {0, 1}};
        System.out.println("Input: " + Arrays.deepToString(grid));
        int output = largestIsland(grid);
        System.out.println("Output: " + output);
    }

    private static int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    // time : O(N^2)
    // using dfs
    private static int largestIsland(int[][] grid) {
        ArrayList<Integer> sizeArr = new ArrayList<>();
        int marker = 2;
        int max = 0;
        // replace all 1 with marker++
        // meaning first island of 1 in matrix will be replaced with island of 2
        // second island of 1 in matrix will be replaced with island of 3 and so on
        // so above matrix will become [2, 0][0, 3] when we are done iterating all elements
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 1) { // found a 1 so in that
                    int sizeNow = markIsland(grid, row, col, marker) + 1;
                    max = Math.max(max, sizeNow);
                    sizeArr.add(sizeNow);
                    marker++;
                }
            }
        }
        // this for loop will find an location that is not in island
        // meaning grid[i][j] = 0 there is chance to replace that location
        // to make bigger island
        // calculate max based out of this replacement
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    int sizeAfterAddn = 1;
                    Set<Integer> used = new HashSet<>();
                    for (int[] dir : dirs) {
                        int newI = i + dir[0];
                        int newJ = j + dir[1];

                        if (isValid(grid, newI, newJ)
                                && grid[newI][newJ] > 0
                                && !used.contains(grid[newI][newJ])) {

                            sizeAfterAddn += sizeArr.get(grid[newI][newJ] - 2);
                            used.add(grid[newI][newJ]);
                        }
                    }
                    max = Math.max(max, sizeAfterAddn);
                }
            }
        }
        return max;
    }

    private static boolean isValid(int[][] grid, int i, int j) {
        return (i >= 0 && i < grid.length && j >= 0 && j < grid[i].length);
    }

    private static int markIsland(int[][] grid, int row, int col, int marker) {
        if (grid[row][col] < 0) {
            return 0;
        }
        int sum = 0;
        grid[row][col] = -1; // -1 is visited so we do not revisit
        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (isValid(grid, newRow, newCol)
                    && grid[newRow][newCol] == 1) {
                sum += markIsland(grid, newRow, newCol, marker) + 1;
            }
        }
        // marker greater than 1
        grid[row][col] = marker;
        return sum;
    }

}
