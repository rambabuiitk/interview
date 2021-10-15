package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithKDistinct {

    public static void main(String[] args) {
        String input = "eceba";
        int K = 2;
        System.out.println("Input: " + input + " K: " + K);
        int output = findLongestLength(K, input);
        System.out.println("Output: " + output);
    }

    private static int findLongestLength(final int K, final String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int windowStart = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap();
        int maxLength = 0;
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            charFrequencyMap.put(rightChar, charFrequencyMap.getOrDefault(rightChar, 0) + 1);
            while (charFrequencyMap.size() > K) {
                char leftChar = str.charAt(windowStart);
                charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) - 1);
                if (charFrequencyMap.get(leftChar) == 0) {
                    charFrequencyMap.remove(leftChar);
                }
                windowStart++;
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }
}
