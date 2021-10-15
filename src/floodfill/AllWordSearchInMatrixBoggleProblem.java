package floodfill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    private class Trie {
        private TrieNode root;

        private Trie(String[] words) {
            root = new TrieNode();
            for (String word : words) {
                insert(word);
            }
        }

        // Inserts a word into the trie.
        private void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (node.links[ch - 'a'] == null) {
                    node.links[ch - 'a'] = new TrieNode();
                }
                node = node.links[ch - 'a'];
            }
            node.isWord = true;
        }

        // Returns if the word is in the trie.
        private boolean search(String word) {
            TrieNode node = searchPrefix(word, this.root);
            return node != null && node.isWord;
        }

        private boolean startsWith(String word) {
            TrieNode node = searchPrefix(word, this.root);
            return node != null;
        }

        private TrieNode searchPrefix(String word, TrieNode node) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (node.links[ch - 'a'] != null) {
                    node = node.links[ch - 'a'];
                } else {
                    return null;
                }
            }
            return node;
        }

        private class TrieNode {
            private TrieNode[] links = new TrieNode[26];
            boolean isWord;
        }
    }
}
