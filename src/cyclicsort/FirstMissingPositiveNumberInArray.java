import java.util.Arrays;

public class FirstMissingPositiveNumberInArray {

    public static void main(String[] args) {
        int[] input = new int[]{-3, 1, 5, 4, 2};
        System.out.println("Input: " + Arrays.toString(input));
        int firstMissingPositive = findNumber(input);
        System.out.println("Output: " + firstMissingPositive);
    }

    public static int findNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            // swap if number is positive and index is in array range and number is not at its right location
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            } else { // move to next number
                i++;
            }
        }

        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) { // find first mismatch and that is the missing positive number.
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}