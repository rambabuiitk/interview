package karat;

import java.util.*;

/**
 * You are running a classroom and suspect that some of your students are passing around the answers to multiple choice questions disguised as random strings.
 *
 * Your task is to write a function that, given a list of words and a string, finds and returns the word in the list that is scrambled up inside the string,
 * if any exists. There will be at most one matching word. The letters don't need to be contiguous.
 *
 * Example:
 * words = ['cat', 'baby', 'dog', 'bird', 'car', 'ax']
 * string1 = 'tcabnihjs'
 * find_embedded_word(words, string1) -> cat
 *
 * string2 = 'tbcanihjs'
 * find_embedded_word(words, string2) -> cat
 *
 * string3 = 'baykkjl'
 * find_embedded_word(words, string3) -> None
 *
 * string4 = 'bbabylkkj'
 * find_embedded_word(words, string4) -> baby
 *
 * string5 = 'ccc'
 * find_embedded_word(words, string5) -> None
 *
 * string6 = 'nbird'
 * find_embedded_word(words, string6) -> bird
 *
 * n = number of words in words
 * m = maximal string length of each word
 */

public class ScrambledWords {

    public static String findWord(String[] words, String scrambledString) {
        HashSet<Character> scrambledChars = new HashSet<>();
        for (int i = 0; i < scrambledString.length(); i++) {
            scrambledChars.add(scrambledString.charAt(i));
        }
        for (String word : words) {
            boolean found = true;
            for (int i = 0; i < word.length(); i++) {
                if (!scrambledChars.contains(word.charAt(i))) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return word;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(findWord(new String[]{"cat", "baby", "dog", "bird", "car", "ax"}, "tcabnihjs"));
    }
}
