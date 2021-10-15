package slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllStringAnagramPatternMatching {
    public static void main(String[] args) {
        String input = "abbcabc";
        String pattern = "abc";
        System.out.println("str: " + input);
        System.out.println("pattern: " + pattern);
        List<Integer> output = findStringAnagrams(input, pattern);
        System.out.println("Output: " + output);
    }

    private static List<Integer> findStringAnagrams(final String str, final String pattern) {
        int windowStart = 0;
        int charMatched = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : pattern.toCharArray()) {
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);
        }
        List<Integer> resultIndices = new ArrayList<Integer>();
        // our goal is to match all the characters from the map with the current window
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            // decrement the frequency of the matched character
            if (charFrequencyMap.containsKey(rightChar)) {
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                if (charFrequencyMap.get(rightChar) == 0) {
                    charMatched = charMatched + 1;
                }
            }
            if(charMatched == charFrequencyMap.size()) { // we found the anagram
                resultIndices.add(windowStart);
            }
            // if we are moving outside of pattern length start start shrinking
            if(windowEnd >= pattern.length()-1) { // start shrinking
                char leftChar = str.charAt(windowStart);
                if (charFrequencyMap.containsKey(leftChar)) {
                    if (charFrequencyMap.get(leftChar) == 0) { // if map for char is empty char back so we d not screw pattern or next for loop.
                        charMatched = charMatched - 1; // before putting the character back, decrement the charMatched count
                    }
                    // put the character back
                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
                }
                windowStart = windowStart + 1;
            }
        }
        return resultIndices;
    }
}
