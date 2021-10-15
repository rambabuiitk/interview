package dynamicprogramming;

import java.util.Arrays;

public class EqualSubsetSumPartition {
    public static void main(String[] args) {

        int[] nums = new int[]{1, 5, 11, 5};
        System.out.println("Input: " + Arrays.toString(nums));
        EqualSubsetSumPartition ps = new EqualSubsetSumPartition();
        System.out.println("using recursion: " + ps.canPartitionUsingRecursion(nums));
        System.out.println("using topdown dp: " + ps.canPartitionUsingTopDownDP(nums));
        System.out.println("using bottomup dp: " + ps.canPartitionUsingBottomUpDP(nums));
    }

    private boolean canPartitionUsingRecursion(final int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
        }
        // if totalSum is odd meaning we cannot get subset with equal sum.
        if (sum % 2 != 0) {
            return false;
        }
        // here partition equal meaning total sum of array can be
        // divided into half and we need to find that parition with that sum
        return canPartitionRecursive(nums, sum / 2, 0);
    }

    // for each number 'i'
    // create a new set which INCLUDES number 'i' if it does not exceed 'S/2', and recursively process the remaining numsbers
    // create a new set WITHOUT number 'i', and recursively process the remaining items
    // return true if any of the above sets has a sum equal to 'S/2', otherwise return false
    private boolean canPartitionRecursive(int[] nums, int sum, int currentIndex) {
        // base logic
        // if sum is 0 meaning we can use empty subset.
        if (sum == 0) {
            return true;
        }

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

    // Using top down DP
    //Since we have two changing values (sum and currentIndex) in our recursive function canPartitionRecursive(),
    // we can use a two-dimensional array to store the results of all the solved sub-problems.
    // exactly same as recursion just store the final return value to matrix and return last value from matrix
    // since we need to store result for every subset and every possible sum so we will use dp[index][sum]
    private boolean canPartitionUsingTopDownDP(final int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
        }
        // if totalSum is odd meaning we cannot get subset with equal sum.
        if (sum % 2 != 0) {
            return false;
        }
        Boolean[][] dp = new Boolean[nums.length][sum / 2 + 1];
        return canPartitionTopDownDP(dp, nums, sum / 2, 0);
    }

    private boolean canPartitionTopDownDP(final Boolean[][] dp, final int[] nums, final int sum, final int currentIndex) {
        // base logic
        if (sum == 0) {
            return true;
        }

        if (nums.length == 0 || currentIndex >= nums.length) {
            return false;
        }

        if (dp[currentIndex][sum] != null) {
            return dp[currentIndex][sum];
        }

        // choice logic
        // if the sum of current item is less than sum
        // 1. if we choose the current item then we will reduce the sum and move to next item.
        boolean output1 = false;
        if (nums[currentIndex] <= sum) {
            output1 = canPartitionTopDownDP(dp, nums, sum - nums[currentIndex], currentIndex + 1);
        }

        boolean output2 = canPartitionTopDownDP(dp, nums, sum, currentIndex + 1);
        dp[currentIndex][sum] = output1 || output2;
        return dp[currentIndex][sum];
    }

    private boolean canPartitionUsingBottomUpDP(final int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
        }
        // if totalSum is odd meaning we cannot get subset with equal sum.
        if (sum % 2 != 0) {
            return false;
        }

        // we are trying to find a subset of given numbers that has a total sum of ‘sum/2’.
        int subsetSum = sum / 2;
        boolean[][] dp = new boolean[n][subsetSum + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        for (int s = 0; s <= subsetSum; s++) {
            dp[0][s] = (nums[0] == s ? true : false);
        }

        for (int i = 1; i < n; i++) {
            for (int s = 1; s <= subsetSum; s++) {
                boolean output1 = false;
                if (nums[i] <= s) {
                    // here we are taking the number
                    output1 = dp[i - 1][s - nums[i]];
                }
                // not taking the number
                // if we can get the sum 's' without the number at index 'i'
                boolean output2 = dp[i - 1][s];
                dp[i][s] = output1 || output2;
            }
        }
        // the bottom-right corner will have our answer.
        return dp[n - 1][subsetSum];
    }


}
