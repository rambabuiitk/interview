package trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board. Each word must be
 * constructed from letters of sequentially adjacent cell, where “adjacent” cells are those horizontally or
 * vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 * Before understanding this problem we need to also understand Search Word in Matrix and How Trie works.
 *
 *
 * Input:
 * [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * words = ["oath", "pea", "eat", "rain"]
 *
 * Output: ["eat", "oath"]
 */

public class AllWordSearchInMatrixBoggleProblem {

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        System.out.println("grid: " + Arrays.deepToString(grid));
        // Dictionary can be actually be implemented using TRIE.
        String[] words = new String[]{"oath", "pea", "eat", "rain"};
        System.out.println("dictionary: " + Arrays.toString(words));
        AllWordSearchInMatrixBoggleProblem bp = new AllWordSearchInMatrixBoggleProblem();
        List<String> output = bp.findAllWords(grid, words);
        System.out.println("Output: " + output);
    }

    private Trie trie;

    private List<String> findAllWords(char[][] grid, String[] words) {
        trie = new Trie(words);
        Set<String> result = new HashSet<>();
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] state = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                findWordsRecursive(state, grid, i, j, "", result);
            }
        }
        return new ArrayList<String>(result);
    }

    private void findWordsRecursive(boolean[][] state, char[][] grid,
                                    int i, int j,
                                    String currentString, Set<String> result) {

        if (i < 0 || i >= grid.length
                || j < 0 || j >= grid[i].length
                || state[i][j]
                || !trie.startsWith(currentString)) {
            return;
        }

        currentString = currentString + grid[i][j];

        if (trie.search(currentString)) {
            result.add(currentString);
        }

        state[i][j] = true;
        findWordsRecursive(state, grid, i - 1, j, currentString, result);
        findWordsRecursive(state, grid, i + 1, j, currentString, result);
        findWordsRecursive(state, grid, i, j - 1, currentString, result);
        findWordsRecursive(state, grid, i, j + 1, currentString, result);
        state[i][j] = false;
    }
}
