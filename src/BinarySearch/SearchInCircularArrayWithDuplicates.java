import java.util.Arrays;

public class SearchInCircularArrayWithDuplicates {
    public static void main(String[] args) {
        int[] nums = {1, 3, 1, 1, 1};
        int target = 3;
        System.out.println("Input: " + Arrays.toString(nums));
        System.out.println("target: " + target);
        int output = search(nums, target);
        System.out.println("Output: " + output);
    }

    // O(N) worst case if no match is found
    // best case : O(log N)
    private static int search(final int[] nums, final int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            //  left side is sorted
            if (nums[start] < nums[mid]) {
                if (target >= nums[start] && target < nums[mid]) { // if target is greater then start but less then mid
                    end = mid - 1; // target is in left side .
                } else {
                    start = mid + 1; // target is in right side of mid
                }
            } else if (nums[start] > nums[mid]) { //  right side is sorted
                if (target > nums[mid] && target <= nums[end]) { // similar condition as above but comparing with nums[mid]
                    start = mid + 1; // target is in left side.
                } else {
                    end = mid - 1; // target is in right side
                }
            } else {
                //If we get here, that means nums[start] == nums[mid] == nums[end], then shifting out
                //any of the two sides won't change the result but can help remove duplicate from
                //consideration, but start++ works too
                start++;
            }
        }
        return -1;
    }
}
