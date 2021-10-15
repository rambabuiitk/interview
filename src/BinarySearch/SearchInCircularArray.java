import java.util.Arrays;

public class SearchInCircularArray {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        System.out.println("Input: " + Arrays.toString(nums));
        System.out.println("target: " + target);
        int output = search(nums, target);
        System.out.println("Output: " + output);
    }

    private static int search(final int[] nums, final int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[start] <= nums[mid]) { // left side is sorted in ascending order
                if (target >= nums[start] && target < nums[mid]) { // if target is greater then start but less then mid
                    end = mid - 1; // target is in left side .
                } else {
                    start = mid + 1; // target is in right side of mid
                }
            } else { //right side is sorted in ascending order
                if (target > nums[mid] && target <= nums[end]) { // similar condition as above but comparing with nums[mid]
                    start = mid + 1; // target is in left side.
                } else {
                    end = mid - 1; // target is in right side
                }
            }
        }
        return -1;
    }
}
