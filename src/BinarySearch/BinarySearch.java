package BinarySearch;

import java.util.Arrays;

public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;
        System.out.println("Input: " + Arrays.toString(nums));
        System.out.println("Key: " + target);
        int output = binarySearchUsingIteration(nums, target);
        System.out.println("output : " + output);
        System.out.println("---");
        output = binarySearchUsingRecursion(nums, target);
        System.out.println("output : " + output);
    }

    // Binary Search Using Iteration
    private static int binarySearchUsingIteration(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            // just mid = start + end / 2 wont work because if end is integer.max
            // than start + end is integer out of bound. so always take start + (end - start) / 2
            int mid = start + (end - start) / 2;

            if (target < nums[mid]) {
                end = mid - 1; // the 'target' can be in the first half
            } else if(target > nums[mid]){ // target > arr[mid]
                start = mid + 1; // the 'target' can be in the second half
            } else { // found the element
                return mid;
            }
        }
		// element not found
        return -1;
    }

    // Binary Search Using Recursion
    private static int binarySearchUsingRecursion(int[] nums, int target) {
        return binarySearchRecursive(nums, target, 0, nums.length - 1);
    }

    private static int binarySearchRecursive(int[] nums, int target, int start, int end) {
        if (start > end) {
            return -1;
        }
        // just mid = start + end / 2 wont work because if end is integer.max
        // than start + end is integer out of bound. so always take start + (end - start) / 2
        int mid = start + (end - start) / 2;
        // if found the target return index
        if (target == nums[mid]) {
            return mid;
        }
        if (target < nums[mid]) {
            return binarySearchRecursive(nums, target, start, mid - 1); // the 'target' can be in the first half
        } else { // target > arr[mid]
            return binarySearchRecursive(nums, target, mid + 1, end); // the 'target' can be in the second half
        }
    }
}

