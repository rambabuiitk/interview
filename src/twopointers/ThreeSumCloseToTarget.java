import java.util.Arrays;

public class ThreeSumCloseToTarget {

    public static void main(String[] args) {
        int[] input = new int[]{-1, 2, 1, -4};
        int targetSum = 1;
        System.out.println("Input: " + Arrays.toString(input) + " targetSum: " + targetSum);
        int output = searchTriplet(input, targetSum);
        System.out.println("Output: " + output);
    }

    private static int searchTriplet(final int[] nums, final int target) {
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException();
        }
        Arrays.sort(nums);
        int smallestDifference = Integer.MAX_VALUE;
        // looping from first till second last element
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                // comparing the sum of three numbers to the 'target' can cause overflow
                // so, we will try to find a target difference
                int targetDiff = target - nums[i] - nums[left] - nums[right];
                // if diff =0 means we have found the triplet with exact sum.
                if (targetDiff == 0) {
                    return target - targetDiff; // Return Sum of all the numbers
                }
                // the second part pf the if is to handle smallest sum when we have more than one triplets.
                // save the closest and the biggest difference
                if (Math.abs(targetDiff) < Math.abs(smallestDifference)
                        || Math.abs(targetDiff) == Math.abs(smallestDifference)
                        && targetDiff > smallestDifference) {
                    // save the closest and the biggest difference
                    smallestDifference = targetDiff;
                }
                if (targetDiff > 0) { // meaning we have smaller number that result in positive targetDiff, we need bigger numbers
                    left++;
                } else { // meaning we have too big numbers that result in negative targetDiff, we need smaller numbers
                    right--;
                }
            }
        }
        return target - smallestDifference;
    }
}