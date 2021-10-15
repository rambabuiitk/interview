package trie;

/**
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * void addWord(word)  Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 * Input:
 * DesignWordDictionary t = new DesignWordDictionary();
 * t.addWord(bad)
 * t.addWord(dad)
 * t.addWord(mad)
 * t.search(b..)
 *
 * Output:
 * t.search(b..): true
 *
 * Explanation:
 * WordDictionary t = new WordDictionary();
 * t.addWord("bad");
 * t.addWord("dad");
 * t.addWord("mad");
 * t.search("b.."); // return True
 */

public class DesignAddSearchWordDictionary {
    public static void main(String[] args) {
        DesignAddSearchWordDictionary t = new DesignAddSearchWordDictionary();
        System.out.println("t.addWord(bad)");
        t.addWord("bad");
        System.out.println("t.addWord(dad)");
        t.addWord("dad");
        System.out.println("t.addWord(dot)");
        t.addWord("dot");
        System.out.println("t.addWord(mad)");
        t.addWord("mad");
        System.out.println("t.search(b..): " + t.search("b.."));
    }

    private TrieNode root;

    private DesignAddSearchWordDictionary() {
        root = new TrieNode();
    }

    // Inserts a word into the
    // trie insert
    // time: O(N) where n is length of word
    private void addWord(String word) {
        TrieNode node = root;
        for(char ch : word.toCharArray()){
            if (node.child[ch - 'a'] == null){
                node.child[ch - 'a'] = new TrieNode();
            }
            node = node.child[ch - 'a'];
        }
        node.isWord = true;
    }

    // Returns if the word is in the
    private boolean search(String word) {
        return search(word, this.root, 0);
    }

    private boolean search(String word, TrieNode node, int index) {
        // if we reach end of word check if current node has isWord true
        if (index == word.length()) {
            return node.isWord;
        }
        char ch = word.charAt(index);
        if (ch != '.') {
            return (node.child[ch - 'a'] != null &&
                    search(word, node.child[ch - 'a'], index + 1));
        } else { // if it is a '.' do a recursive search for all child to find the match
            for (TrieNode n : node.child) {
                // if recursive search for next index returns true meaning we found word
                if (n != null && search(word, n, index + 1)) {
                    return true;
                }
            }
            return false;
        }
    }

    private class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isWord;
    }

    /**
     * Time Complexity:
     * addWord: O(N) were n is the length of the input word.
     * search:
     * without dots: O(N) where N is length of input word
     * with dots: O(K * 26N) where K is total words in trie and N is length of input word
     * Space Complexity:
     * addWord: O(N) were n is the length of the input word.
     * search: O(N) total characters including all the input words.
     */
}
