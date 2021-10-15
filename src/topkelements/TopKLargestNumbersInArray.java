package topkelements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class TopKLargestNumbersInArray {
    public static void main(String[] args) {
        int[] input = new int[]{5, 12, 11, -1, 12};
        int k = 3;
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("k : " + k);
        List<Integer> result = findKLargestNumbersUsingMin(input, k);
        System.out.println("Output: " + result);
        System.out.println("--");
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("k : " + k);
        result = findKLargestNumbers(new int[]{5, 12, 11, -1, 12}, 3);
        System.out.println("Output: " + result);
    }

    // Using just MinHeap O(K∗logK + (N−K)∗logK) == O(NlogK)
    private static List<Integer> findKLargestNumbersUsingMin(final int[] arr, final int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // default is minHeap
        // put k elements in minHeap
        for (int i = 0; i < k; i++) {
            minHeap.add(arr[i]);
        }

        // go through the remaining numbers of the array, if the number from the array is bigger than the
        // top (smallest) number of the min-heap, remove the top number from heap and add the number from array
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(arr[i]);
            }
        }
        // the heap has the top 'K' numbers, return them in a list
        return new ArrayList<>(minHeap);
    }

    // Using just MaxHeap  O(N*logN)
    private static List<Integer> findKLargestNumbers(final int[] arr, final int k) {
        List<Integer> result = new ArrayList<>(k);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a); // max heap
        for (int currentNum : arr) {
            maxHeap.add(currentNum);
        }
        int count = k;
        while (count > 0) {
            result.add(maxHeap.poll());
            count--;
        }
        return result;
    }
}
