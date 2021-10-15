package floodfill;

import java.util.Arrays;

public class WordSearchInMatrix {
    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        System.out.println("grid: " + Arrays.deepToString(grid));
        System.out.println("word: " + word);
        WordSearchInMatrix ws = new WordSearchInMatrix();
        boolean output = ws.searchWord(grid, word);
        System.out.println("Output: " + output);
    }

    private boolean searchWord(char[][] grid, String word) {
        // initialize state 2d array with all false.
        boolean[][] state = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (word.charAt(0) == grid[i][j]
                        && findWordRecursive(state, grid, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean findWordRecursive(boolean[][] state, char[][] grid,
                                      int i, int j,
                                      String word, int currentIndex) {
        if (currentIndex == word.length()) {
            return true;
        }
        if (i < 0 || i >= grid.length
                || j < 0 || j >= grid[i].length
                || grid[i][j] != word.charAt(currentIndex)
                || state[i][j]) {
            return false;
        }
        // set the state of current character to be visited
        state[i][j] = true;
        boolean output1 = findWordRecursive(state, grid, i - 1, j, word, currentIndex + 1);
        boolean output2 = findWordRecursive(state, grid, i + 1, j, word, currentIndex + 1);
        boolean output3 = findWordRecursive(state, grid, i, j - 1, word, currentIndex + 1);
        boolean output4 = findWordRecursive(state, grid, i, j + 1, word, currentIndex + 1);

        if (output1 || output2 || output3 || output4) {
            return true;
        }
        // reset it back to original false so we can use it again in between other characters.
        state[i][j] = false;
        return false;
    }
}
