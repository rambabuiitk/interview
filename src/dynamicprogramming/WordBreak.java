package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public static void main(String[] args) {
        List<String> dictionary = new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat"));
        System.out.println("dictionary: " + dictionary);
        String word = "catsanddog";
        System.out.println("word: " + word);
        WordBreak wb = new WordBreak();
        System.out.println("using recursion: " + wb.wordBreakUsingRecursion(word, dictionary));
        System.out.println("using topdown dp: " + wb.wordBreakUsingTopDownDP(word, dictionary));
        System.out.println("using bottomup dp: " + wb.wordBreakUsingBottomUpDP(word, dictionary));
    }

    private boolean wordBreakUsingRecursion(String word, List<String> wordDict) {
        if (wordDict == null || wordDict.isEmpty()) {
            return false;
        }
        Set<String> dictionary = new HashSet<>(wordDict);
        return wordBreakRecursive(word, dictionary, 0);
    }

    private boolean wordBreakRecursive(String word, Set<String> dictionary, int start) {
        if (start == word.length()) {
            return true;
        }
        boolean foundWord = false;
        for (int end = start + 1; end <= word.length(); end++) {
            String startWord = word.substring(start, end);
            boolean foundWordStart = dictionary.contains(startWord);
            if (foundWordStart) {
                boolean foundWordEnd = wordBreakRecursive(word, dictionary, end);
                if (foundWordEnd) {
                    foundWord = true;
                }
            }
        }
        return foundWord;
    }

    private boolean wordBreakUsingTopDownDP(String word, List<String> wordDict) {
        if (wordDict == null || wordDict.isEmpty()) {
            return false;
        }
        Set<String> dictionary = new HashSet<>(wordDict);
        Boolean[] dp = new Boolean[word.length() + 1];
        return wordBreakTopDownDP(dp, word, dictionary, 0);
    }

    private boolean wordBreakTopDownDP(Boolean[] dp, String word, Set<String> dictionary, int start) {
        if (start == word.length()) {
            return true;
        }
        if (dp[start] != null) {
            return dp[start];
        }
        boolean foundWord = false;
        for (int end = start + 1; end <= word.length(); end++) {
            String startWord = word.substring(start, end);
            boolean foundWordStart = dictionary.contains(startWord);
            if (foundWordStart) {
                boolean foundWordEnd = wordBreakTopDownDP(dp, word, dictionary, end);
                if (foundWordEnd) {
                    foundWord = true;
                }
            }
        }
        dp[start] = foundWord;
        return dp[start];
    }

    private boolean wordBreakUsingBottomUpDP(String word, List<String> wordDict) {
        Set<String> dictionary=new HashSet(wordDict);
        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;
        for (int end = 1; end <= word.length(); end++) {
            for (int start = 0; start < end; start++) {
                if (dp[start] && dictionary.contains(word.substring(start, end))) {
                    dp[end] = true;
                    break;
                }
            }
        }
        return dp[word.length()];
    }

}
