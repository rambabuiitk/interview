package TwoHeaps;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.
 *
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 *
 * Examples:
 *
 * [2,3,4] , the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Input:
 * [1, 3, -1, -3, 5, 3, 6, 7]
 * k = 3
 *
 * Output:
 * [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]
 *
 * Explanation:
 * Window position                       Median
 * ---------------                           -----
 * [1 3 -1] -3 5 3 6 7                        1
 * 1 [3 -1 -3] 5 3 6 7                       -1
 * 1 3 [-1 -3 5] 3 6 7                       -1
 * 1 3 -1 [-3 5 3] 6 7                        3
 * 1 3 -1 -3 [5 3 6] 7                        5
 * 1 3 -1 -3 5 [3 6 7]                        6
 *
 * So the output is [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]
 */

public class SlidingWindowMedian {
    //containing first half of numbers
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) ->  b - a); // reverse order
    //containing second half of numbers
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();


    public static void main(String[] args) {
        int[] input = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("k: " + k);
        double[] output = medianSlidingWindow(input, k);
        System.out.println("Output: " + Arrays.toString(output));
    }

    private static double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            // all elements greater than max peak will be in min else default is max
            if (maxHeap.size() == 0 || maxHeap.peek() >= nums[windowEnd]) {
                maxHeap.add(nums[windowEnd]);
            } else {
                minHeap.add(nums[windowEnd]);
            }

            rebalanceHeaps();

            // if we have at least 'k' elements in the sliding window
            // meaning if windowEnd >= k - 1
            windowStart = windowEnd - k + 1;

            if (windowStart >= 0) {
                // add the median to the the result array
                if (maxHeap.size() == minHeap.size()) {
                    // we have even number of elements, take the average of middle two elements
                    result[windowStart] = maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
                } else { // because max-heap will have one more element than the min-heap
                    result[windowStart] = maxHeap.peek();
                }

                // remove the the element going out of the sliding window
                // which was the old startWindow
                int startElement = nums[windowStart];
                if (startElement <= maxHeap.peek()) {
                    maxHeap.remove(startElement);
                } else {
                    minHeap.remove(startElement);
                }
                rebalanceHeaps();
            }
        }
        return result;
    }

    private static void rebalanceHeaps() {
        // either both the heaps will have equal number of elements or max-heap will have
        // one more element than the min-heap
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }
}
