package slidingwindow;

import java.util.Arrays;

public class LongestSubarrayAfterReplacingKZero {
    public static void main(String[] args) {
        int[] input = new int[] { 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1 };
        int K = 2;
        System.out.println("Input: "+ Arrays.toString(input) + " K: " + K);
        int output = findLength(input, 2);
        System.out.println("Output: " + output);
    }

    private static int findLength(final int[] arr, final int k) {
        int windowStart = 0;
        int maxLength = 0;
        int maxOneCount = 0;
        for(int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            if(arr[windowEnd] == 1) {
                maxOneCount++;
            }
            // we are allowed to replace max k 0's.
            // So if the windowLength - maxOneCount > k
            // meaning we have reached our max replacement 0's  and
            // now we have shrink the window from the leftSide and if leftSide is 1 also reduce maxOneCount.
            int windowLength = windowEnd - windowStart + 1;
            if(windowLength - maxOneCount > k) {
                if(arr[windowStart] == 1) {
                    maxOneCount--;
                }
                windowStart++;
            }
            // updated windowSize with new windowStart
            windowLength = windowEnd - windowStart + 1;
            maxLength = Math.max(maxLength, windowLength);
        }
        return maxLength;
    }
}