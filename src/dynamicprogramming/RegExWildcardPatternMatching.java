package dynamicprogramming;

import java.util.Arrays;

public class RegExWildcardPatternMatching {
    public static void main(String[] args) {
        String input = "aab";
        String pattern = "c*a*b."; // for just "*a*b" wll throw false 
        System.out.println("input: " + input);
        System.out.println("pattern: " + pattern);
        RegExWildcardPatternMatching sre = new RegExWildcardPatternMatching();
        boolean output = sre.isMatchUsingRecursive(input, pattern);
        System.out.println("dp using recursion: " + output);
        output = sre.isMatchUsingTopDown(input, pattern);
        System.out.println("dp using topdown:  " + output);
        output = sre.isMatchUsingBottomUp(input, pattern);
        System.out.println("dp using bottomup: " + output);
    }



    private boolean isMatchUsingRecursive(String text, String pattern) {
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }
        boolean isFirstMatch = false;
        if (!text.isEmpty()) {
            isFirstMatch = pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.';
        }

        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            // considering by taking the char from pattern
            boolean match1 = isMatchUsingRecursive(text, pattern.substring(2));
            // considering by not taking the char pattern
            boolean match2 = isFirstMatch && isMatchUsingRecursive(text.substring(1), pattern);
            return match1 || match2;
        }
        return isFirstMatch && isMatchUsingRecursive(text.substring(1), pattern.substring(1));
    }

    private boolean isMatchUsingTopDown(String text, String pattern) {
        Boolean[][] dp = new Boolean[text.length() + 1][pattern.length() + 1];
        return isMatchTopDown(dp, 0, 0, text, pattern);
    }

    private boolean isMatchTopDown(Boolean[][] dp, int sIndex, int pIndex, String s, String p) {
        if (dp[sIndex][pIndex] != null) {
            return dp[sIndex][pIndex] == true;
        }
        boolean ans;
        if (pIndex == p.length()) {
            ans = sIndex == s.length();
        } else {
            boolean first_match = (sIndex < s.length() &&
                    (p.charAt(pIndex) == s.charAt(sIndex) ||
                            p.charAt(pIndex) == '.'));

            if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
                ans = (isMatchTopDown(dp, sIndex, pIndex + 2, s, p) ||
                        first_match && isMatchTopDown(dp, sIndex + 1, pIndex, s, p));
            } else {
                ans = first_match && isMatchTopDown(dp, sIndex + 1, pIndex + 1, s, p);
            }
        }
        dp[sIndex][pIndex] = ans ? true : false;
        return ans;
    }


    private boolean isMatchUsingBottomUp(String text, String pattern) {
        // if the pattern is null or empty just check the input string
        // if input string is null or empty return true else return false
        if (pattern == null || pattern.length() == 0) {
            return (text == null || text.length() == 0);
        }
        // row i has
        boolean dp[][] = new boolean[text.length() + 1][pattern.length() + 1];

        // with empty input and empty pattern there is a match
        dp[0][0] = true;

        // pattern is in the column so validate first row all columns
        for (int j = 2; j <= pattern.length(); j++) {
            // if the character is * and string before * was matching then current will match
            // as * can matching anything
            if (pattern.charAt(j - 1) == '*' && dp[0][j - 2]) {
                System.out.println(j);
                dp[0][j] = true;
            }
        }

        for (int i = 1; i <= text.length(); i++) {
            for (int j = 1; j <= pattern.length(); j++) {
                // current characters match or pattern has . then the result is determined by the previous state
                if (pattern.charAt(j - 1) == text.charAt(i - 1) || pattern.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pattern.charAt(j - 1) == '*') {
                    if (pattern.charAt(j-2) != text.charAt(i-1) && pattern.charAt(j-2) != '.') {
                        dp[i][j] = dp[i][j-2];  // in this case, pattern a* only counts as empty
                    } else {
                        // dp[i][j] = dp[i-1][j] --> in this case, pattern a* counts as multiple a
                        // here dp[i][j] = dp[i][j-1] --> in this case, pattern a* counts as single a
                        // dp[i][j] = dp[i][j-2] --> in this case, pattern a* counts as empty
                        dp[i][j] = (dp[i][j-1] || dp[i-1][j] || dp[i][j-2]);
                    }
                }
            }
        }
        return dp[text.length()][pattern.length()];
    }
}
