package topkelements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TaskScheduler {

    public static void main(String[] args) {
        char[] tasks = new char[]{'a', 'a', 'a', 'b', 'c', 'c'};
        System.out.println("Input: " + Arrays.toString(tasks));
        int n = 2;
        System.out.println("n: " + n);
        int output = scheduleTasks(tasks, n);
        System.out.println("Output : " + output);
    }

    // time: O(N) where N is number of tasks to execute
    // space: O(1) which is constant size space to keep 26 characters in map
    private static int scheduleTasks(final char[] tasks, final int n) {
        int intervalCount = 0;
        Map<Character, Integer> taskFrequencyMap = new HashMap<>();
        for (char chr : tasks) {
            taskFrequencyMap.put(chr, taskFrequencyMap.getOrDefault(chr, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<Map.Entry<Character, Integer>>(
                (e1, e2) -> e2.getValue() - e1.getValue());
        // add all tasks to the max heap
        maxHeap.addAll(taskFrequencyMap.entrySet());

        while (!maxHeap.isEmpty()) {
            // After executing a task we decrease its frequency and put it in a waiting list.
            List<Map.Entry<Character, Integer>> waitList = new ArrayList<>();
            int k = n + 1; // try to execute as many as 'n+1' (current + next n) tasks from the max-heap
            while (k > 0 && !maxHeap.isEmpty()) {
                k--;
                intervalCount++;
                Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
                // if entry count is greater than 1 add reduce by 1 and add it to the waitlist.
                // else we are just using the entry as it is only of count 1.
                if (currentEntry.getValue() > 1) {
                    currentEntry.setValue(currentEntry.getValue() - 1);
                    waitList.add(currentEntry);
                }
            }
            maxHeap.addAll(waitList); // put all the waiting list back on the heap
            if (!maxHeap.isEmpty()) {
                intervalCount = intervalCount + k; // we'll be having 'k' idle intervals for the next iteration
            }
        }
        return intervalCount;
    }
}
