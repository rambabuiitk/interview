package MiniMax;

/**
 * Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed
 * by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player.
 * This continues until all the scores have been chosen. The player with the maximum score wins.
 *
 * Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.
 *
 * Input: [1, 5, 2]
 *
 * Output: false
 *
 * Explanation:
 * Initially, player 1 can choose between 1 and 2.
 * If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2).
 * So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. Hence, player 1 will never be the winner and you need to return False.
 */
public class PlayerPickEndsGame {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 2};
        // here the prediction is for the player one who is starting the game
        System.out.println("using recursion: " + winnerUsingRecursion(nums));
        System.out.println("using topdown dp: " + winnerUsingTopDown(nums));
        System.out.println("using bottomup dp: " + winnerUsingBottomUp(nums));
    }

    // time: O(2^N)
    private static boolean winnerUsingRecursion(int[] nums) {
        int result = winnerRecursive(nums, 0, nums.length - 1);
        if (result >= 0) {
            return true;
        }
        return false;
    }

    private static int winnerRecursive(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        // if current player picks from the start. leaving the other player a choice from s+1 to e
        int a = nums[start] - winnerRecursive(nums, start + 1, end);
        // if current player picks from the end. leaving the other player a choice from s to e-1
        int b = nums[end] - winnerRecursive(nums, start, end - 1);

        return Math.max(a, b);
    }

    // O(N^2)
    private static boolean winnerUsingTopDown(int[] nums) {
        Integer[][] dp = new Integer[nums.length][nums.length];
        int result = winnerTopDown(dp, nums, 0, nums.length - 1);

        if (result >= 0) {
            return true;
        }
        return false;
    }

    private static int winnerTopDown(Integer[][] dp, int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }

        if (dp[start][end] != null) {
            return dp[start][end];
        }

        // if current player picks from the start. leaving the other player a choice from s+1 to e
        int a = nums[start] - winnerTopDown(dp, nums, start + 1, end);
        // if current player picks from the end. leaving the other player a choice from s to e-1
        int b = nums[end] - winnerTopDown(dp, nums, start, end - 1);

        dp[start][end] = Math.max(a, b);
        return dp[start][end];
    }

    // time: O(N^2)
    private static boolean winnerUsingBottomUp(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }

        for (int len = 1; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                int a = nums[i] - dp[i + 1][j];
                int b = nums[j] - dp[i][j - 1];
                dp[i][j] = Math.max(a, b);
            }
        }
        int res = dp[0][n - 1];
        if(res >= 0) {
            return true;
        }
        return false;
    }


}
