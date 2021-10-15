package topkelements;

import java.util.PriorityQueue;

public class KthLargestNumber {

    public static void main(String[] args) {
        int[] input = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println("Input: " + input);
        System.out.println("k: " + k);
        int result = KthLargestNumber.findKthLargest(input, k);
        System.out.println("Output: " + result);
    }

    private static int findKthLargest(final int[] nums, final int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // put k elements in maxHeap
        for(int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }
        // go through the remaining numbers of the array, if the number from the array is smaller than the
        // top (biggest) number of the heap, remove the top number from heap and add the number from array
        for(int i = k; i < nums.length; i++) {
            if(nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        // since this is the min_heap. The root will be kth largest in the heap 
        // k elements greater than root will be in the heap.
        return minHeap.peek();
    }
}
