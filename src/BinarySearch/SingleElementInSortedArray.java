package BinarySearch;

import java.util.Arrays;

public class SingleElementInSortedArray {
    public static void main(String[] args) {
        int[] input = new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8};
        System.out.println("Input: " + Arrays.toString(input));
        int output = singleNonDuplicate(input);
        System.out.println("Output: " + output);
    }

    private static int singleNonDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            // We want the first element of the middle pair,
            // which should be at an even index if the left part is sorted.
            if (mid % 2 == 1) { // if num % 2 == 1 meaning number is odd
                mid--;
            }
            // We didn't find a pair. The single element must be on the left.
            if (nums[mid] != nums[mid + 1]) {
                end = mid;
            } else { // We found a pair. The single element must be on the right.
                start = mid + 2;
            }
        }
        return nums[start];
    }
}
