package topkelements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentNumbers {
    
    public static void main(String[] args) {
        int[] input = new int[]{1, 3, 5, 6, 5, 5, 6};
        int K = 2;
        System.out.println("Input: " + Arrays.toString(input) + " K: " + K);
        List<Integer> result = findTopKFrequentNumbers(input, K);
        System.out.println("Output: " + result);
    }

    private static List<Integer> findTopKFrequentNumbers(final int[] nums, final int k) {
        // find the frequency of each number
        Map<Integer, Integer> numFrequencyMap = new HashMap<>();
        for (int num : nums) {
            numFrequencyMap.put(num, numFrequencyMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>((e1, e2) -> e1.getValue() - e2.getValue());

        for (Map.Entry entry : numFrequencyMap.entrySet()) {
            minHeap.add(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // now our minheap have all the k frequently occuring elements.
        List<Integer> topNumbers = new ArrayList<>(k);
        while (!minHeap.isEmpty()) {
            topNumbers.add(minHeap.poll().getKey());
        }
        return topNumbers;
    }
}