package dynamicprogramming;

public class ClimbingStairs {
    public static void main(String[] args) {
        int stairs = 3;
        System.out.println("Input: " + stairs);
        ClimbingStairs cs = new ClimbingStairs();
        System.out.println("using recursion: " + cs.waysUsingRecursion(stairs));
        System.out.println("using topdown dp: " + cs.waysUsingTopDown(stairs));
        System.out.println("using bottomup dp: " + cs.waysUsingBottomUp(stairs));
    }

    private int waysUsingRecursion(int n) {
        return waysRecursive(0, n);
    }

    private int waysRecursive(int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        int way1 = waysRecursive(i + 1, n);
        int way2 = waysRecursive(i + 2, n);
        return way1 + way2;
    }

    private int waysUsingTopDown(int n) {
        Integer[] dp = new Integer[n];
        return waysTopDown(dp, 0, n);
    }

    private int waysTopDown(Integer[] dp, int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (dp[i] != null) {
            return dp[i];
        }
        int way1 = waysRecursive(i + 1, n);
        int way2 = waysRecursive(i + 2, n);
        dp[i] = way1 + way2;
        return dp[i];
    }


    private int waysUsingBottomUp(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        // dp[0] is 0 for no stairs there is no way
        // for 1 stair there is only 1 way and so count is 1
        dp[1] = 1;
        // for 2 stairs there are total 2 ways 1+1 or direct 2
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            // taking single step on ith step
            // taking 2 step on ith step
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
