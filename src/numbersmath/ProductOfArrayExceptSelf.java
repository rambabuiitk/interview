package numbersmath;

import java.util.Arrays;

/**
 *  Input: [1, 2, 3, 4]
 * step 1 : 24, 24, 12, 4
 * step 2: 24, 12, 8, 4
 * step 3: 24, 12, 8, 6
 */
public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        System.out.println("Input : " + Arrays.toString(nums));
        int[] output = productExceptSelf(nums);
        System.out.println("Output: " + Arrays.toString(output));
    }
    // product of all the numbers to the right
    // all the numbers to the left of the index.
    // Multiplying these two individual products would give us the desired result as well.
    private static int[] productExceptSelf(int[] nums) {
        if (nums.length < 2) {
            return nums;
        }
        int[] result = new int[nums.length];

        int factor = 1;
        // this is same as right array
        // we will do the  multiplication of all the elements on the left.
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] = factor * nums[i];
            factor = result[i];
        }
        factor = 1;
        // this is same as left array
        // we will do the  multiplication of all the elements on the right of the element.
        // for the first element there are no elements on left and so the for first factor is 1 * result[i+1]
        for (int i = 0; i < nums.length - 1; i++) {
            result[i] = factor * result[i + 1];
            factor = factor * nums[i];
        }
        result[result.length - 1] = factor; // storing the last element
        return result;
    }
}
