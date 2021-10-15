package slidingwindow;

import java.util.Arrays;

public class MaxSubarraySum {
    public static void main(String[] args) {
        int[] input = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Input: " + Arrays.toString(input));
        int output = maxSubArray(input);
        System.out.println("Output: " + output);
    }

    private static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int windowSum = 0;
        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            windowSum = windowSum + nums[windowEnd]; // add the next element
            // if the current element is greater than windowSum than start sum from currentElement
            windowSum = Math.max(nums[windowEnd], windowSum);
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }
}
