package dynamicprogramming;

import java.util.Arrays;

public class FindSubsetWithSum {

    public static void main(String[] args) {
        FindSubsetWithSum ss = new FindSubsetWithSum();
        int[] nums = {1, 2, 3, 7, 6};
        int sum = 6;
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("sum: " + sum);

        System.out.println("using recursion: " + ss.canPartitionUsingRecursion(nums, sum));
        System.out.println("using topdown dp: " + ss.canPartitionUsingTopDownDP(nums, sum));
        System.out.println("using bottomup dp: " + ss.canPartitionUsingBottomUp(nums, sum));
    }


    private boolean canPartitionUsingRecursion(final int[] nums, final int sum) {
        return canPartitionRecursive(nums, sum, 0);
    }

    private boolean canPartitionRecursive(int[] nums, int sum, int currentIndex) {
        // if sum is 0 meaning we can use empty subset.
        if (sum == 0) {
            return true;
        }

        // base logic
        if (nums.length == 0 || currentIndex >= nums.length) {
            return false;
        }

        // choice logic
        // if the sum of current item is less than sum
        // 1. if we choose the current item then we will reduce the sum and move to next item.
        boolean output1 = false;
        if (nums[currentIndex] <= sum) {
            output1 = canPartitionRecursive(nums, sum - nums[currentIndex], currentIndex + 1);
        }
        // 2. item is less than sum but we choose to not include
        // 3. also item is greater than the sum in both cases we choose to not include item.
        boolean output2 = canPartitionRecursive(nums, sum, currentIndex + 1);
        return output1 || output2;
    }

    private boolean canPartitionUsingTopDownDP(final int[] num, int sum) {
        Boolean[][] dp = new Boolean[num.length][sum + 1];
        return canPartitionTopDownDP(dp, num, sum, 0);
    }

    private boolean canPartitionTopDownDP(final Boolean[][] dp, final int[] num, final int sum, final int currentIndex) {
        // if sum is 0 meaning we can use empty subset.
        if (sum == 0) {
            return true;
        }

        // base logic
        if (num.length == 0 || currentIndex >= num.length) {
            return false;
        }

        if (dp[currentIndex][sum] != null) {
            return dp[currentIndex][sum];
        }

        // choice logic
        // if the sum of current item is less than sum
        // 1. if we choose the current item then we will reduce the sum and move to next item.
        boolean output1 = false;
        if (num[currentIndex] <= sum) {
            output1 = canPartitionTopDownDP(dp, num, sum - num[currentIndex], currentIndex + 1);
        }

        boolean output2 = canPartitionTopDownDP(dp, num, sum, currentIndex + 1);
        dp[currentIndex][sum] = output1 || output2;
        return dp[currentIndex][sum];
    }

    private boolean canPartitionUsingBottomUp(final int[] num, final int sum) {
        int n = num.length;
        boolean[][] dp = new boolean[n][sum + 1];
        // populate the sum=0 columns, as we can always form '0' sum with an empty set
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        // with only one number, we can form a subset only when the required sum is
        // equal to its value
        for (int s = 1; s <= sum; s++) {
            dp[0][s] = (num[0] == s ? true : false);
        }

        // process all subsets for all sums
        for (int i = 1; i < num.length; i++) {
            for (int s = 1; s <= sum; s++) {
                boolean output1 = false;
                if (num[i] <= s) {
                    // here we are taking the number vs not taking the number
                    output1 = dp[i - 1][s - num[i]];
                }
                // if we can get the sum 's' without the number at index 'i'
                boolean output2 = dp[i - 1][s];
                dp[i][s] = output1 || output2;
            }
        }

        // the bottom-right corner will have our answer.
        return dp[num.length - 1][sum];
    }

}
