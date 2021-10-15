package dynamicprogramming;

import java.util.Arrays;

public class LongestIncreasingSubsequenceInArray {
    public static void main(String[] args) {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Input : " + Arrays.toString(arr));
        int longestSequenceLength = longSubSequenceUsingRecursion(arr);
        System.out.println("using recursion ---> " + longestSequenceLength);

        longestSequenceLength = longSubSequenceUsingTopDownDP(arr);
        System.out.println("using topdown dp ---> " + longestSequenceLength);

        longestSequenceLength = longSubSequenceUsingBottomUpDP(arr);
        System.out.println("using topdown dp ---> " + longestSequenceLength);
    }

    // time : O(2^N)
    // space: O(N^2)
    private static int longSubSequenceUsingRecursion(int[] arr) {
        return longSubSequenceRecursion(arr, arr.length - 1);
    }

    private static int longSubSequenceRecursion(int[] arr, int currentIndex) {
        if (currentIndex == 0) {
            return 1;
        }
        int max = 1;
        for (int i = 0; i < currentIndex; i++) {
            int tempLongestMax = longSubSequenceRecursion(arr, i);
            if (arr[i] < arr[currentIndex]) {
                tempLongestMax = tempLongestMax + 1;
            }
            max = Math.max(max, tempLongestMax);
        }
        return max;
    }

    // time: O(N^2)
    // space: O(N^2)
    private static int longSubSequenceUsingTopDownDP(final int[] arr) {
        Integer[] dp = new Integer[arr.length];
        return longSubSequenceTopDownDP(dp, arr, arr.length - 1);
    }

    private static int longSubSequenceTopDownDP(Integer[] dp, final int[] arr, int currentIndex) {
        if (currentIndex == 0) {
            return 1;
        }

        if (dp[currentIndex] != null) {
            return dp[currentIndex];
        }
        int max = 1;
        for (int i = 0; i < currentIndex; i++) {
            int tempLongestMax = longSubSequenceTopDownDP(dp, arr, i);
            if (arr[i] < arr[currentIndex]) {
                tempLongestMax = tempLongestMax + 1;
            }
            max = Math.max(max, tempLongestMax);
        }

        dp[currentIndex] = max;
        return max;
    }

    // time: O(N^2)
    // space: O(N)
    private static int longSubSequenceUsingBottomUpDP(final int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int[] dp = new int[arr.length];
        dp[0] = 1; // for only 1 value in input longest increasing sequence is 1
        int max = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
