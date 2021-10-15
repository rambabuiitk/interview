package trie;

import java.util.Arrays;

/**
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Input:
 * Input Array : [abc, bc, b, i, ib, s, bibs]
 * Word: bibs
 * Prefix: bi
 * Output:
 * Search Word: true
 * Valid Prefix: true
 * Explanation:
 * Word 'bibs' is in the trie and so we found the word.
 * Prefix 'bi' is found in the trie. There are words that start with 'bi' and so we found the prefix.
 *
 */

public class Trie {

    public static void main(String[] args) {
        String[] arr = {"abc", "bc", "b", "i", "ib", "s", "bibs"};
        System.out.println("Input Array : " + Arrays.toString(arr));
        Trie t = new Trie();
        for (int i = 0; i < arr.length; i++) {
            t.insert(arr[i]);
        }
        System.out.println("---");
        String search_word = "bibs";
        System.out.println("Word: " + search_word);
        System.out.println("Search Word: " + t.search(search_word));
        System.out.println("--");
        String prefix = "bi";
        System.out.println("Prefix: " + prefix);
        boolean isValid = t.startsWith(prefix);
        System.out.println("Valid Prefix: " + isValid);
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public Trie(String[] words) {
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
    public boolean search(String word) {
        TrieNode node = searchPrefix(word, this.root);
        return node != null && node.isWord;
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

    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix, this.root);
        return node != null;
    }

    private class TrieNode {
        private TrieNode[] links = new TrieNode[26];
        boolean isWord;
    }
}


/**
 * Time Complexity:
 * Insert: O(N) where N is the size of the wor
 * Search: O(N) where N is the size of the word
 * Search Prefix: O(K) where k is the size of the prefix.
 * Space Complexity: O(N * M) where M is number of words we added to the Trie and N is the size of max word.
 */
