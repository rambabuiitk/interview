package dynamicprogramming;

public class DecodeWaysWithCharacter {
    public static void main(String[] args) {
        DecodeWaysWithCharacter dw = new DecodeWaysWithCharacter();
        String input = "*1";
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
        return findWaysRecursion(str, str.length() - 1);
    }

    // Time Complexity : O(2^N) where N is length of string
    private int findWaysRecursion(String str, int currentIndex) {
        // mod is if the input is 10^5 characters we should return something like 10^9 + 7
        int M = 1000000007;  // 10^9+7 is a prime number

        if (currentIndex < 0) {
            return 1;
        }

        if (str.charAt(currentIndex) == '*') {
            long res = 9 * findWaysRecursion(str, currentIndex - 1);
            if (currentIndex > 0 && str.charAt(currentIndex - 1) == '1') {
                res = (res + 9 * findWaysRecursion(str, currentIndex - 2)) % M;
            } else if (currentIndex > 0 && str.charAt(currentIndex - 1) == '2') {
                res = (res + 6 * findWaysRecursion(str, currentIndex - 2)) % M;
            } else if (currentIndex > 0 && str.charAt(currentIndex - 1) == '*') {
                res = (res + 15 * findWaysRecursion(str, currentIndex - 2)) % M;
            }
            return (int) res;
        }
        long res = str.charAt(currentIndex) != '0' ? findWaysRecursion(str, currentIndex - 1) : 0;
        if (currentIndex > 0 && str.charAt(currentIndex - 1) == '1') {
            res = (res + findWaysRecursion(str, currentIndex - 2)) % M;
        } else if (currentIndex > 0 && str.charAt(currentIndex - 1) == '2' && str.charAt(currentIndex) <= '6') {
            res = (res + findWaysRecursion(str, currentIndex - 2)) % M;
        } else if (currentIndex > 0 && str.charAt(currentIndex - 1) == '*') {
            res = (res + (str.charAt(currentIndex) <= '6' ? 2 : 1) * findWaysRecursion(str, currentIndex - 2)) % M;
        }
        return (int) res;
    }

    // Time Complexity : O(N)
    private int findWaysUsingTopDown(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        // dp to store currentIndex and its ways
        Integer[] dp = new Integer[str.length()];
        return findWaysTopDown(dp, str, str.length() - 1);
    }

    private int findWaysTopDown(Integer[] dp, String str, int currentIndex) {
        // mod is if the input is 10^5 characters we should return something like 10^9 + 7
        int M = 1000000007;  // 10^9+7 is a prime number

        if (currentIndex < 0) {
            return 1;
        }
        if (dp[currentIndex] != null) {
            return dp[currentIndex];
        }
        if (str.charAt(currentIndex) == '*') {
            long res = 9 * findWaysTopDown(dp, str, currentIndex - 1);
            if (currentIndex > 0 && str.charAt(currentIndex - 1) == '1') {
                res = (res + 9 * findWaysTopDown(dp, str, currentIndex - 2)) % M;
            } else if (currentIndex > 0 && str.charAt(currentIndex - 1) == '2') {
                res = (res + 6 * findWaysTopDown(dp, str, currentIndex - 2)) % M;
            } else if (currentIndex > 0 && str.charAt(currentIndex - 1) == '*') {
                res = (res + 15 * findWaysTopDown(dp, str, currentIndex - 2)) % M;
            }
            dp[currentIndex] = (int) res;
            return dp[currentIndex];
        }
        long res = str.charAt(currentIndex) != '0' ? findWaysTopDown(dp, str, currentIndex - 1) : 0;
        if (currentIndex > 0 && str.charAt(currentIndex - 1) == '1') {
            res = (res + findWaysTopDown(dp, str, currentIndex - 2)) % M;
        } else if (currentIndex > 0 && str.charAt(currentIndex - 1) == '2' && str.charAt(currentIndex) <= '6') {
            res = (res + findWaysTopDown(dp, str, currentIndex - 2)) % M;
        } else if (currentIndex > 0 && str.charAt(currentIndex - 1) == '*') {
            res = (res + (str.charAt(currentIndex) <= '6' ? 2 : 1) * findWaysTopDown(dp, str, currentIndex - 2)) % M;
        }
        dp[currentIndex] = (int) res;
        return dp[currentIndex];
    }

    // Time Complexity : O(N) where N is length of string
    private int findWaysUsingBottomUp(String str) {
        // mod is if the input is 10^5 characters we should return something like 10^9 + 7
        int M = 1000000007;  // 10^9+7 is a prime number
        long[] dp = new long[str.length() + 1];

        // If the string starts with a zero, it can't be decoded
        dp[0] = 1;

        // If the string starts with a *,
        dp[1] = str.charAt(0) == '*' ? 9 : str.charAt(0) == '0' ? 0 : 1;

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == '*') { // if the current index is * we also check previous character
                // default is 9 * dp[i] and now we check the previous character
                dp[i + 1] = 9 * dp[i];
                if (str.charAt(i - 1) == '1') {  // if the previous char before * is 1 we have 9 * previous ways
                    dp[i + 1] = (dp[i + 1] + 9 * dp[i - 1]) % M;
                } else if (str.charAt(i - 1) == '2') {   // if the previous char before * is 1 we have 6 * previous ways
                    dp[i + 1] = (dp[i + 1] + 6 * dp[i - 1]) % M;
                } else if (str.charAt(i - 1) == '*') {   // if the previous char before * is * we have 15 * previous ways
                    dp[i + 1] = (dp[i + 1] + 15 * dp[i - 1]) % M;
                }
            } else { // if the current character is not '*'
                dp[i + 1] = str.charAt(i) != '0' ? dp[i] : 0;
                if (str.charAt(i - 1) == '1') { // if current is not '*' and previous is '1' meaning we current + previous
                    dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
                } else if (str.charAt(i - 1) == '2' && str.charAt(i) <= '6') { // if current is <= 6 and previous is 2 we can add ways
                    dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
                } else if (str.charAt(i - 1) == '*') { // if previous is * we need to check current
                    if (str.charAt(i) <= '6') { // if previous is * and current is <= 6 meaning previous can be 1 or 2
                        dp[i + 1] = (dp[i + 1] + 2 * dp[i - 1]) % M; // means previous can be ways can be 2 * previous
                    } else { // current is grater than 6
                        dp[i + 1] = (dp[i + 1] + dp[i - 1]) % M;
                    }
                }
            }
        }
        return (int) dp[str.length()];
    }

}
