import java.util.Arrays;

public class NextPermutation {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 1};
        System.out.println("Input: " + Arrays.toString(nums));
        nextPermutation(nums);
        System.out.println("Output: " + Arrays.toString(nums));
        System.out.println("---");
        nums = new int[]{3, 2, 1};
        System.out.println("Input: " + Arrays.toString(nums));
        nextPermutation(nums);
        System.out.println("Output: " + Arrays.toString(nums));
    }

    private static void nextPermutation(int[] nums) {
        int current = nums.length - 2;
        // from the end of array iterate till previous digit is greater than
        while (current >= 0 && nums[current + 1] <= nums[current]) {
            current--;
        }
        // here at this point our current is less than current + 1
        // if there are still more elements left in array left meaning
        // find lexicographically next greater number
        if (current >= 0) {
            // get the second most greater number than current.
            int largest = nums.length - 1;
            while (largest >= 0 && nums[largest] <= nums[current]) {
                largest--;
            }
            // swap largest with current
            // meaning 1131 will become 1311
            swap(nums, current, largest);
        }

        // Simply reverse the array from (current + 1) till end so we get sorted order
        reverse(nums, current + 1);
    }

    private static void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
