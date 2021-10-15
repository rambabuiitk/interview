package slidingwindow;

import java.util.Arrays;

public class StringCompression {
    public static void main(String[] args) {
        char[] input = new char[]{'a','a','b','b','b','b','b','b','b','b','b','b','b','b','c'};
        System.out.println("Input: " + Arrays.toString(input));
        int output = compress(input);
        System.out.println("Output Array: " + Arrays.toString(input));
        System.out.println("Output Length: " + output);
    }

    // time: O(N * d) where d is the length of longest digit array
    // space O(N)
    private static int compress(char[] chars) {
        int windowStart = 0;
        int windowLength = 0;
        int lastPointer = 0;
        for (int windowEnd = 0; windowEnd < chars.length; windowEnd++) {
            if (chars[windowStart] == chars[windowEnd]) {
                windowLength++;
            } else {
                chars[lastPointer] = chars[windowStart];
                lastPointer++;
                if(windowLength != 1) {
                    char[] digits = String.valueOf(windowLength).toCharArray();
                    for(char digit : digits) {
                        chars[lastPointer] = digit;
                        lastPointer++;
                    }
                }
                windowStart = windowEnd;
                windowLength = 1;
            }
        }

        chars[lastPointer] = chars[windowStart];
        lastPointer++;
        if(windowLength != 1) {
            char[] digits = String.valueOf(windowLength).toCharArray();
            for(char digit : digits) {
                chars[lastPointer++] = digit;
            }
        }
        return lastPointer;
    }
}
