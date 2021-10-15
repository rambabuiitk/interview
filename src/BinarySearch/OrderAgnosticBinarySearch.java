package BinarySearch;

import java.util.Arrays;

public class OrderAgnosticBinarySearch {

    public static void main(String[] args) {
        int[] nums = new int[]{7, 6, 5, 4, 3, 2, 1};
        int target = 5;
        System.out.println("Input: " + Arrays.toString(nums));
        System.out.println("target: " + target);
        int output = search(nums, target);
        System.out.println("Output: " + output);
        System.out.println("---");
        nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        target = 5;
        System.out.println("Input: " + Arrays.toString(nums));
        System.out.println("target: " + target);
        output = search(nums, target);
        System.out.println("Output: " + output);
    }

    private static int search(final int[] arr, final int target) {
        int start = 0;
        int end = arr.length - 1;
        boolean isAscending = arr[start] < arr[end]; // compare first and last to see sort order
        while (start <= end) {
            // calculate the middle of the current range
            // if we take mid = (start + end)/2 and if start is Integer.MAX then mid is out of bound.
            // so mid should always be calculated below way.
            int mid = start + (end - start) / 2;

            if (target == arr[mid]) {
                return mid;
            }

            if (isAscending) { // ascending order
                if (target < arr[mid]) {
                    end = mid - 1; // the 'target' can be in the first half
                } else { // target > arr[mid]
                    start = mid + 1; // the 'target' can be in the second half
                }
            } else { // descending order
                if (target > arr[mid]) {
                    end = mid - 1; // the 'target' can be in the first half
                } else { // target < arr[mid]
                    start = mid + 1; // the 'target' can be in the second half
                }
            }
        }
        return -1; // element not found
    }
}
