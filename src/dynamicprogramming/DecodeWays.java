package dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class DecodeWays {
    public static void main(String[] args) {
        DecodeWays dw = new DecodeWays();
        String input = "226";
        int ways = dw.findWaysUsingRecursion(input);
        System.out.println("using bottomup dp: " + ways);
        ways = dw.findWaysUsingTopDown(input);
        System.out.println("using bottomup dp: " + ways);
        ways = dw.findWaysUsingBottomUp(input);
        System.out.println("using bottomup dp: " + ways);
    }

    private int findWaysUsingRecursion(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return findWaysRecursion(str, 0);
    }

    private int findWaysRecursion(String str, int currentIndex) {
        // If you reach the end of the string
        // return 1 for success.
        if (currentIndex == str.length()) {
            return 1;
        }
        // If the string starts with a zero, it can't be decoded
        if (str.charAt(currentIndex) == '0') {
            return 0;
        }
        // if there is only 1 character then there is just 1 way
        // only 1 character is possible
        if (currentIndex == str.length() - 1) {
            return 1;
        }
        int ways = findWaysRecursion(str, currentIndex + 1);
        // if the next 2 characters fall less than 26 meaning there are double digits which can have more ways.
        if (Integer.parseInt(str.substring(currentIndex, currentIndex + 2)) <= 26) {
            ways = ways + findWaysRecursion(str, currentIndex + 2);
        }
        return ways;
    }

        // Time Complexity : O(N)
    private int findWaysUsingTopDown(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        // dp to store currentIndex and its ways
       Integer[] dp = new Integer[str.length()];
        return findWaysTopDown(dp, str, 0);
    }

    private int findWaysTopDown(Integer[] dp, String str, int currentIndex) {
        // If you reach the end of the string
        // Return 1 for success.
        if (currentIndex == str.length()) {
            return 1;
        }
        // If the string starts with a zero, it can't be decoded
        if (str.charAt(currentIndex) == '0') {
            return 0;
        }
        if (currentIndex == str.length() - 1) {
            return 1;
        }
        // Memoization is needed since we might encounter the same sub-string.
        if (dp[currentIndex] != null) {
            return dp[currentIndex];
        }

        int ways = findWaysTopDown(dp, str, currentIndex + 1);
        // if the next 2 characters fall less than 26 meaning there are double digits which can have more ways.
        if (Integer.parseInt(str.substring(currentIndex, currentIndex + 2)) <= 26) {
            ways = ways + findWaysTopDown(dp, str, currentIndex + 2);
        }

        // Save for memoization
        dp[currentIndex] = ways;
        return ways;
    }

    private int findWaysUsingBottomUp(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // DP array to store the subproblem results
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        // Ways to decode a string of size 1 is 1. Unless the string is '0'.
        // '0' doesn't have a single digit decode.
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i < dp.length; i += 1) {

            // Check if successful single digit decode is possible.
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }

            // Check if successful two digit decode is possible.
            int twoDigit = Integer.valueOf(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }

}
