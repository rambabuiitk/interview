package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestNonRepeatingSubstring {

    public static void main(String[] args) {
        String input = "abccde";
        System.out.println("Input: " + input);
        int output = findLength(input);
        System.out.println("Output: " + output);
    }

    private static int findLength(final String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int windowStart = 0;
        int maxLength = 0;
        // using hashmap to remember the last index of each character.
        Map<Character, Integer> charIndexMap = new HashMap();
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            // if the map already contains the 'rightChar',
            // meaning we got a repeating character so shrink the window from the beginning.
            // so that we have only one occurrence of 'rightChar'
            if (charIndexMap.containsKey(rightChar)) {
                // in the current window, we will not have any 'rightChar' after its previous index
                // and if 'windowStart' is already ahead of the last index of 'rightChar', we'll keep 'windowStart'
                windowStart = Math.max(windowStart, charIndexMap.get(rightChar) + 1);
            }
            charIndexMap.put(rightChar, windowEnd);
            int windowLength = windowEnd - windowStart + 1;
            maxLength = Math.max(maxLength, windowLength);
        }
        return maxLength;
    }

}
