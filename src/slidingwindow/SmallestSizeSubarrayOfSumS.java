package slidingwindow;

import java.util.Arrays;

public class SmallestSizeSubarrayOfSumS {

    public static void main(String[] args) {
        int[] input = new int[]{3, 3, 1, 6, 1, 7};
        int S = 7;
        System.out.println("Input: " + Arrays.toString(input) + " S: " + S);
        int output = findMinSubarray(S, input);
        System.out.println("Output: " + output);
    }

    private static int findMinSubarray(final int S, final int[] arr) {
        int windowSum = 0;
        int minLength = Integer.MAX_VALUE;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum = windowSum + arr[windowEnd];  
            while (windowSum >= S) {
                int windowLength = windowEnd - windowStart + 1;
                minLength = Math.min(minLength, windowLength);
                windowSum = windowSum - arr[windowStart]; 
                windowStart++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}