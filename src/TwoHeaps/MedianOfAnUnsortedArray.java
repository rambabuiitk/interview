package TwoHeaps;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an unsorted array of integers having length N, return the median of this unsorted array.
 *
 * Median of a sorted array of size N is defined as the middle element when n is odd and average of middle two elements when n is even.
 *
 * Input: {12, 3, 5, 7, 4, 19, 26}
 * Output: 7
 * Explanation:
 * Sorted sequence of given array arr[] = {3, 4, 5, 7, 12, 19, 26}
 * Since the number of elements is odd, the median is 4th element in the sorted sequence of given array arr[], which is 7
 *
 * Input: {12, 3, 5, 7, 4, 26}
 * Output: 6
 * Explanation:
 * Since number of elements are even, median is average of 3rd and 4th element in sorted sequence of given array arr[], which means (5 + 7)/2 = 6
 *
 * Complexity : O(NlogN)
 */
public class MedianOfAnUnsortedArray {
    public static void main(String[] args) {
        int[] input = new int[]{3, 4, 5, 7, 12, 19, 26};
        System.out.println("Input: " + Arrays.toString(input));
        int output = findMedian(input);
        System.out.println("Output: " + output);
    }

    private static int findMedian(int[] input) {
        // containing smallest half of numbers
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        // containing largest half of numbers
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < input.length; i++) {
            addNum(input[i], maxHeap, minHeap);
        }
        // if odd max-heap will have 1 more element than min-heap
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }
        return (maxHeap.peek() + minHeap.peek()) / 2;
    }

    private static void addNum(final int num, PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        // if maxHeap is empty or num smaller than peak of maxHeap
        if (maxHeap.isEmpty() || maxHeap.peek() >= num) {
            maxHeap.add(num);
        } else { // if maxHeap is not empty and number greater than
            minHeap.add(num);
        }

        // either both the heaps will have equal number of elements or max-heap will have one
        // more element than the min-heap
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }
}
