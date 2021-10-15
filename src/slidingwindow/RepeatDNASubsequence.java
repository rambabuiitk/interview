package slidingwindow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatDNASubsequence {
    public static void main(String[] args) {
        String input = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        System.out.println(findRepeatedDnaSequences(input));
    }

    private static List<String> findRepeatedDnaSequences(String s) {
        Set<String> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();
        int windowStart = 0;
        for (int windowEnd = 9; windowEnd < s.length(); windowEnd++) {
            // substring(beginIndex, endIndex)
            String word = s.substring(windowStart, windowEnd + 1);
            // add word to seen and also validate if we have already seen the word
            // set.add return true if set does not contain the word
            // set returns false if set has the word
            if (!seen.add(word)) {
                repeated.add(word); // set contains the word
            }
            windowStart++;
        }
        return new ArrayList(repeated);
    }
}
