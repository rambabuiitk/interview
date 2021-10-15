package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreakAllPossibleWords {
    public static void main(String[] args) {
        List<String> dictionary = new ArrayList<>(Arrays.asList("cat", "cats", "and", "sand", "dog"));
        System.out.println("dictionary: " + dictionary);
        String word = "catsanddog";
        System.out.println("word: " + word);
        WordBreakAllPossibleWords wb = new WordBreakAllPossibleWords();
        System.out.println("using recursion: " + wb.wordBreakUsingRecursion(word, dictionary));
        System.out.println("using topdown dp: " + wb.wordBreakUsingTopDownDP(word, dictionary));
        System.out.println("using bottomup dp: " + wb.wordBreakUsingBottomUpDP(word, dictionary));
    }

    private List<String> wordBreakUsingRecursion(String word, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        if (wordDict == null || wordDict.isEmpty()) {
            return result;
        }
        Set<String> dictionary = new HashSet<>(wordDict);
        wordBreakRecursive(word, dictionary, 0, result, new ArrayList<>());
        return result;
    }

    private void wordBreakRecursive(String word, Set<String> dictionary,
                                    int start, List<String> result, List<String> currentWords) {
        if (start == word.length()) {
            result.add(String.join(" ", currentWords));
            return;
        }
        for (int end = start + 1; end <= word.length(); end++) {
            String startWord = word.substring(start, end);
            boolean foundWordStart = dictionary.contains(startWord);
            if (foundWordStart) {
                currentWords.add(startWord);
                wordBreakRecursive(word, dictionary, end, result, new ArrayList<>(currentWords));
                currentWords = new ArrayList<>();
            }
        }
    }

    // time: (N^2 + 2^N + W)
    // space: (N^2 + (2^N * N) + W)
    private List<String> wordBreakUsingTopDownDP(String word, List<String> wordDict) {
        if (wordDict == null || wordDict.isEmpty()) {
            return new ArrayList<>();
        }
        // if the wordDict has duplicates remove it by putting it in set
        Set<String> dictionary = new HashSet<>(wordDict);
        Map<String, List<String>> dp = new HashMap<>();
        return wordBreakTopDownDP(dp, word, dictionary);
    }

    private List<String> wordBreakTopDownDP(Map<String, List<String>> dp,
                                            String word, Set<String> dictionary) {

        List<String> result = new ArrayList<>();
        // if word is empty of null return result;
        if (word == null || word.length() == 0) {
            return result;
        }
        // if map contains the word return the result from map
        if (dp.containsKey(word)) {
            return dp.get(word);
        }
        // if dictionary contains the word add it to the result
        if (dictionary.contains(word)) {
            result.add(word);
        }
        // for each index break the word in left and right
        for (int i = 1; i < word.length(); i++) {
            String leftWord = word.substring(0, i);
            String rightWord = word.substring(i);
            // if dictionary contains right word check recursively for left
            if (dictionary.contains(rightWord)) {
                // get the list for leftWord and for each string append rightWord
                List<String> temp = wordBreakTopDownDP(dp, leftWord, dictionary);
                if (!temp.isEmpty()) {
                    for (String t : temp) {
                        result.add(t + " " + rightWord);
                    }
                }
            }
        }
        // store it in the dp for next run
        dp.put(word, result);
        return result;
    }

    private void updateCharSet(String s, Set<Character> charSet) {
        for (int i = 0; i < s.length(); i++) {
            charSet.add(s.charAt(i));
        }
    }

    private List<String> wordBreakUsingBottomUpDP(String word, List<String> wordDict) {

        // wordCharSet: keep all the chars from word
        Set<Character> wordCharSet = new HashSet<>();
        updateCharSet(word, wordCharSet);

        // dictCharSet: store all the chars from whole wordDict
        Set<Character> dictCharSet = new HashSet<>();
        // all words from wordDict to set
        Set<String> dictSet = new HashSet<>();

        // all the characters from both word and wordDict in dictCharSet
        for (String w : wordDict) {
            dictSet.add(w);
            updateCharSet(w, dictCharSet);
        }

        // quick check on the sets of characters
        // if dictionary is missing a char which is in input word: return from there
        // example: word = abc  and dict = [cart, hello] --> a and b are missing in dict
        if (!dictCharSet.containsAll(wordCharSet)) {
            return new ArrayList();
        }

        List<List<String>> dp = new ArrayList<>(word.length() + 1);
        for (int i = 0; i < word.length() + 1; i++) {
            dp.add(new ArrayList<>());
        }

        dp.get(0).add("");

        for (int endIndex = 1; endIndex < word.length() + 1; endIndex++) {
            List<String> sublist = new ArrayList<String>();

            // fill up the values in the dp array.
            for (int startIndex = 0; startIndex < endIndex; startIndex++) {
                String rightWord = word.substring(startIndex, endIndex);
                if (dictSet.contains(rightWord)) {
                    for (String t : dp.get(startIndex)) {
                        sublist.add((t + " " + rightWord).trim());
                    }
                }
            }
            dp.set(endIndex, sublist);
        }
        return dp.get(word.length());
    }
}
