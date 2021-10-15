package BinarySearch;

import java.util.Arrays;

public class FindRangeOfANumberInArray {

    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int target = 8;
        System.out.println("Input: " + Arrays.toString(nums));
        System.out.println("target: " + target);
        int[] output = findRange(nums, target);
        System.out.println("Output: " + Arrays.toString(output));
        System.out.println("---");
        nums = new int[]{5, 7, 7, 8, 8, 10};
        target = 6;
        System.out.println("Input: " + Arrays.toString(nums));
        System.out.println("target: " + target);
        output = findRange(nums, target);
        System.out.println("Output: " + Arrays.toString(output));
    }
    // time: O(log N)
    private static int[] findRange(final int[] nums, final int target) {
        int[] result = new int[]{-1, -1};
        // findLastIndex in search method decides whether we are finding first index or last.
        result[0] = search(nums, target, false);
        if (result[0] != -1) { // no need to search, if 'target' is not present in the input array
            result[1] = search(nums, target, true);
        }
        return result;
    }

    // binary search is O(log N)
    private static int search(final int[] nums, final int key, 
                              final boolean findLastIndex) {
        int keyIndex = -1;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key < nums[mid]) {
                end = mid - 1;
            } else if (key > nums[mid]) {
                start = mid + 1;
            } else { // found the element
                keyIndex = mid;
                if (findLastIndex) { // since we are finding last index move towards right
                    start = mid + 1; // search ahead to find the last index of 'key'
                } else {  // we are finding first index move towards left
                    end = mid - 1; // search behind to find the first index of 'key'
                }
            }
        }
        return keyIndex;
    }
}
