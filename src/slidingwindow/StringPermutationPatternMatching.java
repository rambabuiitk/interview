package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class StringPermutationPatternMatching {
    public static void main(String[] args) {
        String str = "dcnbag";
        String pattern = "ab";
        System.out.println("Input: " + str + " pattern: " + pattern);
        boolean output = findPermutation(str, pattern);
        System.out.println("Output: " + output);
    }

    private static boolean findPermutation(final String str, final String pattern) {
        int windowStart = 0;
        int charMatched = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : pattern.toCharArray()) {
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);
        }
        for(int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if(charFrequencyMap.containsKey(rightChar)) {
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                if(charFrequencyMap.get(rightChar) == 0) { // particular character is a match in str.
                    charMatched = charMatched + 1;
                }
            }
            // if charMatched same aas map size meaning all characters are matched from pattern.
            if(charMatched == charFrequencyMap.size()) {
                return true;
            }
            // if we are moving outside of pattern length start start shrinking
            if(windowEnd >= pattern.length() -1) { // shrink the window by one character
                char leftChar = str.charAt(windowStart);
                if(charFrequencyMap.containsKey(leftChar)) {
                    if(charFrequencyMap.get(leftChar) == 0) {
                        charMatched = charMatched - 1;// before putting the character back, decrement the charMatched count
                    }
                    // put the character back for matching else original pattern will disturbed for next loop.
                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
                }
                windowStart = windowStart + 1;
            }
        }
        return false;
    }
}
