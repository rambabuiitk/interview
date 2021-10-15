package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstringPatternMatching {
    public static void main(String[] args) {
        String str = "abdcaabca";
        String pattern = "cbca";
        System.out.println("Input: " + str + " pattern: " + pattern);
        String output = findSubstring(str, pattern);
        System.out.println("Output: " + output);
    }

    private static String findSubstring(final String str, final String pattern) {
        int windowStart = 0;
        int charMatched = 0;
        int minLength = str.length() + 1; // just taking max as str.length +1 similar to Integer.MAX_INT
        int subStrStart = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : pattern.toCharArray()) {
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);
        }

        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            System.out.println(charFrequencyMap);
            char rightChar = str.charAt(windowEnd);
            if (charFrequencyMap.containsKey(rightChar)) {
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                // here we are doing >= 0 as we want to also match duplicate characters
                if (charFrequencyMap.get(rightChar) >= 0) {
                    charMatched++;
                }
            }
            // shrink the window if we can, finish as soon as we remove a charMatched character
            while (charMatched == pattern.length()) {
                int windowLength = windowEnd - windowStart + 1;
                if (minLength > windowLength) { // if minlength is bigger update the minlength.
                    minLength = windowLength;
                    subStrStart = windowStart;
                }

                char leftChar = str.charAt(windowStart);
                windowStart = windowStart + 1;
                if (charFrequencyMap.containsKey(leftChar)) {
                    // note that we could have redundant matching characters, therefore we'll decrement the
                    // charMatched count only when a useful occurrence of a charMatched character is going out of the window
                    if (charFrequencyMap.get(leftChar) == 0) {
                        charMatched--;
                    }
                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
                }
            }
        }
        return minLength > str.length() ? "" : str.substring(subStrStart, subStrStart + minLength);
    }
}
