package topkelements;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthSmallestNumber {

    public static void main(String[] args) {
        int[] input = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("k: " + k);
        int result = findKthSmallest(input, k);
        System.out.println("Output: " + result);

        System.out.println("---");

        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("k: " + k);
        result = findKthSmallest(input, k);
        System.out.println("Output: " + result);
    }

    private static int findKthSmallest(final int[] nums, final int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        // put k elements in maxHeap
        for(int i = 0; i < k; i++) {
            maxHeap.add(nums[i]);
        }
        // go through the remaining numbers of the array, if the number from the array is smaller than the
        // top (biggest) number of the heap, remove the top number from heap and add the number from array
        for(int i = k; i < nums.length; i++) {
            if(nums[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(nums[i]);
            }
        }
        // the root of the heap is the kth smallest number
        return maxHeap.peek();
    }

    private static int findKthSmallestUsingQuickSelect(final int[] nums, final int k) {
        if (nums == null || nums.length == 0) {
            return Integer.MAX_VALUE;
        }
        return findKthSmallest(nums, 0, nums.length - 1, nums.length - k);
    }

    private static int findKthSmallest(int[] nums, int start, int end, int k) {// quick select: kth smallest
        if (start > end) {
            return Integer.MAX_VALUE;
        }

        int pivot = nums[end];// Take A[end] as the pivot,
        int right = end;
        for (int i = start; i < end; i++) {
            if (nums[i] >= pivot) {// Put numbers > pivot to pivot's right
                swap(nums, i, right);
                right--;
            }
        }
        swap(nums, start, right);// Finally, swap A[end] with A[right]

        if (right == k) {// Found kth smallest number
            return nums[right];
        } else if (right > k) { // Check left part
            return findKthSmallest(nums, start, right - 1, k);
        } else { // Check right part
            return findKthSmallest(nums, right + 1, end, k);
        }
    }

    private static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
