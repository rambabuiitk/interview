package BinarySearch;

import java.util.Arrays;

public class FindPeakIndexInMountainArray {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 8, 4, 3};
        System.out.println("Input: " + Arrays.toString(nums));
        int output = peakIndexInMountainArray(nums);
        System.out.println("Output: " + output);
    }

    private static int peakIndexInMountainArray(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            // nums[i] < nums[i +1] means we are still in increasing sequence and need to check right.
            if (nums[mid] < nums[mid + 1]) {
                start = mid + 1;
            } else { // we are in decreasing sequence and need to check left.
                end = mid;
            }
        }
        return start;
    }
}
