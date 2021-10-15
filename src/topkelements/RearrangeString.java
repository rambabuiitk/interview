package topkelements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

  
public class RearrangeString {
    public static void main(String[] args) {
        String input = "aappp";
        System.out.println("Input: " + input);
        System.out.println("Output: " + reorganizeString(input));
    }

    private static String reorganizeString(final String str) {
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : str.toCharArray()) {
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(
                (e1, e2) -> e2.getValue() - e1.getValue());
        maxHeap.addAll(charFrequencyMap.entrySet());

        Map.Entry<Character, Integer> previousEntry = null;
        StringBuilder resultString = new StringBuilder(str.length());
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
            // check if previous entry has value if it does add it to the maxheap.
            if (previousEntry != null && previousEntry.getValue() > 0) {
                maxHeap.offer(previousEntry);
            }
            resultString.append(currentEntry.getKey()); // append the key to the string.
            currentEntry.setValue(currentEntry.getValue() - 1); // reduce the value by 1 as we used 1 char.
            // make current entry as previous entry until next char from map is picked up.
            // this is to avoid same char coming next to each other and also putting it back for next turn.
            previousEntry = currentEntry;
        }
        // if we were successful in appending all the characters to the result string, return it
        return resultString.length() == str.length() ? resultString.toString() : "";
    }


}
