package slidingwindow;

import java.util.Arrays;

public class MaxSubarraySumOfSizeK {
    
    public static void main(String[] args) {
        int[] input = new int[]{-1, 3, 2, 5, 4, 1};
        int K = 3;
        System.out.println("Input: " + Arrays.toString(input) + " K: " + K);
        int output = findMaxSum(K, input);
        System.out.println("Output: " + output);

    }
    
    private static int findMaxSum(int K, int[] arr) {
        int maxSum = 0;
        int windowSum = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum = windowSum + arr[windowEnd];
            if (windowEnd >= K - 1) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum = windowSum - arr[windowStart]; 
                windowStart++;
            }
        }

        return maxSum;
    }
}
