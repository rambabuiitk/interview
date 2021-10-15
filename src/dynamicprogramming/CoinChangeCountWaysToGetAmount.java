package dynamicprogramming;

import java.util.Arrays;

public class CoinChangeCountWaysToGetAmount {
    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        int amount = 5;
        System.out.println("coins values:" + Arrays.toString(coins));
        System.out.println("amount:" + amount);
        CoinChangeCountWaysToGetAmount cc = new CoinChangeCountWaysToGetAmount();
        System.out.println("using recursion: " + cc.coinChangeUsingRecursion(coins, amount));
        System.out.println("using topdown dp: " + cc.coinChangeUsingTopDownDP(coins, amount));
        System.out.println("using bottomup dp: " + cc.coinChangeUsingBottomUpDP(coins, amount));
    }

    private int coinChangeUsingRecursion(int[] coins, int amount) {
        return coinChangeRecursive(coins, amount, 0);
    }

    private int coinChangeRecursive(int[] coins, int amount, int currentIndex) {
        // base logic
        // amount is 0 meaning there is only 1 way of using no coin
        if (amount == 0) {
            return 1;
        }
        // base logic if there are no ways returning -1
        if (coins.length == 0 || currentIndex >= coins.length) {
            return 0;
        }

        // choice logic
        int way1 = 0;
        if (coins[currentIndex] <= amount) {
            // since we are allowed to take same coins as many times as we want which is exactly same as
            // unbounded knapsack and so we can keep currentIndex as it is and not currentIndex + 1.
            way1 = coinChangeRecursive(coins, amount - coins[currentIndex], currentIndex);
        }
        // 2. if coin price process is less than the capacity but we choose to not add the item.
        // or if the coin of current item is greater than the amount
        int way2 = coinChangeRecursive(coins, amount, currentIndex + 1);

        // since we are asked min ways and so the output will be min of both.
        return way1 + way2;
    }

    // This is exactly @{@link UnboundedKnapsack}
    private int coinChangeUsingTopDownDP(int[] coins, int amount) {
        Integer[][] dp = new Integer[coins.length][amount + 1];
        return coinChangeTopDownDP(dp, coins, amount, 0);
    }

    private int coinChangeTopDownDP(Integer[][] dp, int[] coins, int amount, int currentIndex) {
        // base logic
        // amount is 0 meaning there is only 1 way of do it is to use no coin
        if (amount == 0) {
            return 1;
        }
        if (currentIndex == coins.length) {
            return 0;
        }
        if (dp[currentIndex][amount] != null) {
            return dp[currentIndex][amount];
        }

        // choice logic
        int way1 = 0;
        if (coins[currentIndex] <= amount) {
            // since we are allowed to take same coins as many times as we want which is exactly same as
            // unbounded knapsack and so we can keep currentIndex as it is and not currentIndex + 1.
            way1 = coinChangeTopDownDP(dp, coins, amount - coins[currentIndex], currentIndex);
        }
        // 2. if coin price process is less than the capacity but we choose to not add the item.
        // or if the coin of current item is greater than the amount
        int way2 = coinChangeTopDownDP(dp, coins, amount, currentIndex + 1);

        // since we are asked min ways and so the output will be min of both.
        dp[currentIndex][amount] = way1 + way2;
        return dp[currentIndex][amount];
    }

    private int coinChangeUsingBottomUpDP(int[] coins, int amount) {
        int n = coins.length;
        // if no amount meaning there is 1 way which is no coins.
        if (amount == 0) {
            return 1;
        }
        // if no coins there is no way
        if (n == 0) {
            return 0;
        }
        // row is coins and column is amount
        int[][] dp = new int[n][amount + 1];
        // with no amount there is only 1 way which is to use no coins
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i < coins.length; i++) {
            for (int s = 1; s <= amount; s++) {
                int way1 = 0;
                if (coins[i] <= s) {
                    // here we are taking the coin
                    way1 = dp[i][s - coins[i]];
                }
                // not taking the number
                // if we can get the sum 's' without the number at index 'i'
                int way2 = 0;
                if (i > 0) {
                    way2 = dp[i - 1][s];
                }
                dp[i][s] = way1 + way2;
            }
        }

        // the bottom-right corner will have our answer.
        return dp[coins.length - 1][amount];
    }


}
