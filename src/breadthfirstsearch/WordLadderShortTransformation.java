package breadthfirstsearch;
/**
 * Given two words (beginWord and endWord), and a dictionary’s word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadderShortTransformation {
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "dog", "lot", "log", "cog"));
        System.out.println("Intput: " + wordList);
        System.out.println("beginWord: " + beginWord);
        System.out.println("endWord: " + endWord);
        int output = ladderLength(beginWord, endWord, wordList);
        System.out.println(output);
    }

    // time: O(M^2 * N) here M is length of each word
    // N is total number of words in input word list
    // BFS using queue
    private static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList); // add dictionary to set to remove duplicates
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int result = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int j = 0; j < size; j++) { // for each word in queue
                String currWord = queue.poll(); // poll the word
                // take total length and replace that character
                for (int i = 0; i < beginWord.length(); i++) {
                    // loop over all the characters and setChar at i position
                    for (char letter = 'a'; letter <= 'z'; letter++) {
                        StringBuilder newWord = new StringBuilder(currWord);
                        newWord.setCharAt(i, letter);
                        // if the formed newWord is there in dictionary
                        if (set.contains(newWord.toString())) {
                            System.out.println(newWord);
                            // if the formed newWord is the endWord return result.
                            if (newWord.toString().equals(endWord)) {
                                return result + 1;
                            }
                            // else remove the newWord from set
                            set.remove(newWord.toString());
                            // add the newWord to the queue
                            queue.offer(newWord.toString());
                        }
                    }
                }
            }
            result++;
        }
        return 0;
    }
}

/*
Time Complexity: O(M2 * N) where M is length of each word and N is total number of words
Space Complexity: O(N)
 */
