package slidingwindow;

import java.util.Arrays;

public class MaxConsecutiveOnesInArray {

    public static void main(String[] args) {
        int[] input = {1, 0, 1, 1, 1, 0, 1};
        System.out.println("Input: " + Arrays.toString(input));
        int output =  findMaxConsecutiveOnes(input);
        System.out.println("Output: " + output);
    }

    private static int findMaxConsecutiveOnes(int[] nums) {
        int windowLength = 0;
        int maxWindowLength = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) {
                // Increment the count of 1's by one.
                windowLength = windowLength + 1;
            } else {
                // Find the maximum till now.
                maxWindowLength = Math.max(maxWindowLength, windowLength);
                // Reset count of 1.
                windowLength = 0;
            }
        }
        return Math.max(maxWindowLength, windowLength);
    }
}