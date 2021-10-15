package dynamicprogramming;

import java.util.Arrays;

public class CoinChangeCountMinCoinsToGetAmount {
    public static void main(String[] args) {
        int[] denominations = new int[]{1, 2, 5};
        int amount = 7;
        System.out.println("coins values:" + Arrays.toString(denominations));
        System.out.println("amount:" + amount);
        CoinChangeCountMinCoinsToGetAmount cc = new CoinChangeCountMinCoinsToGetAmount();
        System.out.println("using recursion: " + cc.coinChangeUsingRecursion(denominations, amount));
        System.out.println("using topdown dp: " + cc.coinChangeUsingTopDownDP(denominations, amount));
        System.out.println("using bottomup dp: " + cc.coinChangeUsingBottomUpDP(denominations, amount));

    }

    private int coinChangeUsingRecursion(int[] coins, int total) {
        int result = coinChangeRecursive(coins, total, 0);
        return (result == Integer.MAX_VALUE ? -1 : result);
    }

    private int coinChangeRecursive(int[] coins, int amount, int currentIndex) {
        if (amount == 0) {
            return 0;
        }

        if (coins.length == 0 || currentIndex >= coins.length) {
            return Integer.MAX_VALUE;
        }

        // recursive call after selecting the coin at the currentIndex
        // if the coin at currentIndex exceeds the total, we shouldn't process this
        int count1 = Integer.MAX_VALUE;
        if (coins[currentIndex] <= amount) {
            int res = coinChangeRecursive(coins, amount - coins[currentIndex], currentIndex);
            if (res != Integer.MAX_VALUE) {
                count1 = res + 1;
            }
        }
        // recursive call after excluding the coin at the currentIndex
        int count2 = coinChangeRecursive(coins, amount, currentIndex + 1);
        return Math.min(count1, count2);
    }


    private int coinChangeUsingTopDownDP(int[] coins, int amount) {
        Integer[][] dp = new Integer[coins.length][amount + 1];
        int result = coinChangeTopDownDP(dp, coins, amount, 0);
        return (result == Integer.MAX_VALUE ? -1 : result);
    }

    private int coinChangeTopDownDP(Integer[][] dp, int[] coins, int amount, int currentIndex) {
        if (amount == 0) {
            return 0;
        }
        if (coins.length == 0 || currentIndex >= coins.length) {
            return Integer.MAX_VALUE;
        }
        if (dp[currentIndex][amount] != null) {
            return dp[currentIndex][amount];
        }
        // recursive call after selecting the coin at the currentIndex
        // if the coin at currentIndex exceeds the total, we shouldn't process this
        int count1 = Integer.MAX_VALUE;
        if (coins[currentIndex] <= amount) {
            int res = coinChangeTopDownDP(dp, coins, amount - coins[currentIndex], currentIndex);
            if (res != Integer.MAX_VALUE) {
                count1 = res + 1;
            }
        }
        // recursive call after excluding the coin at the currentIndex
        int count2 = coinChangeTopDownDP(dp, coins, amount, currentIndex + 1);
        dp[currentIndex][amount] = Math.min(count1, count2);
        return dp[currentIndex][amount];
    }

    private int coinChangeUsingBottomUpDP(int[] coins, int amount) {
        int n = coins.length;
        // row is coins and column is amount
        int[][] dp = new int[n][amount + 1];

        // setting the whole matrix to be INTEGER.MAX_VALUE
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        // populate the amount=0 columns, as we don't need any coin to make zero total
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }

        // process all subsets for all sums
        // here we have to start from i= 0 as the first row is still Integer.MAX_VALUE and we have to fill those
        for (int i = 0; i < n; i++) {
            for (int s = 1; s <= amount; s++) {
                int count1 = Integer.MAX_VALUE;
                if (coins[i] <= s) {
                    int res = dp[i][s - coins[i]];
                    // include the coin so adding + 1 to it.
                    if (res != Integer.MAX_VALUE) {
                        count1 = res + 1;
                    }
                }
                // not taking the number
                // if we can get the amount 's' without the number at index 'i'
                int count2 = Integer.MAX_VALUE;
                if (i > 0) {
                    count2 = dp[i - 1][s];
                }
                dp[i][s] = Math.min(count1, count2);
            }
        }
        // the bottom-right corner will have our answer.
        return (dp[n - 1][amount] == Integer.MAX_VALUE ? -1 : dp[n - 1][amount]);
    }
}
