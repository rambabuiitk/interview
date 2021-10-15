package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AlienDictionary {
    public static void main(String[] args) {
        String[] input = new String[]{"ba", "bc", "ac", "cab"};
        System.out.println("Word: " + Arrays.toString(input));
        String output = alienOrder(input);
        System.out.println("Output: " + output);

        input = new String[]{"abc", "ab"};
        System.out.println("Word: " + Arrays.toString(input));
        output = alienOrder(input);
        System.out.println("Output: " + output);
    }

    private static String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        // a. Initialize the graph
        Map<Character, Integer> inDegree = new HashMap<>();
        Map<Character, List<Character>> graph = new HashMap<>();
        // iterate over each word
        for (String word : words) {
            // get each character from the word and initialize the graph
            for (char character : word.toCharArray()) {
                inDegree.put(character, 0);
                graph.put(character, new ArrayList<>());
            }
        }

        // b. Build the graph
        // Iterate only till second last word as we are taking 2 words at a time
        for (int i = 0; i < words.length - 1; i++) {
            String word_one = words[i];
            String word_two = words[i + 1];
            int minLength = Math.min(word_one.length(), word_two.length());
            for (int j = 0; j < minLength; j++) {
                char parent = word_one.charAt(j); // character j from first word is our parent
                char child = word_two.charAt(j); // character j from second word is our child
                // if both parent and child are same then move to next character (j++) in words.
                // if not same meaning we found a parent-child relationship and parent comes before child.
                // meaning there is dependency from parent to child
                if (parent != child) {
                    graph.get(parent).add(child); // put the child into it's parent's list
                    inDegree.put(child, inDegree.get(child) + 1);  // increment child's inDegree
                    break; // only first different character is needed to find other so we come out of for
                }

                // if we reached the end of second word and first word still exist meaning dictionary is not valid
                // example: first word 'abc' and second is 'ab' then it is not valid order as 'ab' should come before 'abc'
                if (j == word_two.length() - 1 && j < word_one.length() - 1) {
                    return "";
                }

                // if condition if the length of word_one is grater meaning not a valid dictionary
                // if the second word in dictionary is empty then it is also invalid dictionary
                if (word_two.length() == 0 && word_one.length() > 0) {
                    return "";
                }
            }
        }

        // c. Find all sources or vertices with 0 inDegree value
        Queue<Character> sources = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                sources.add(entry.getKey());
            }
        }

        // d. For each source, add it to the sortedOrder and subtract one from all of its children's in-degrees
        // if a child's in-degree becomes zero, add it to the sources queue
        // basically do a BFS
        StringBuilder sortedOrder = new StringBuilder();
        while (!sources.isEmpty()) {
            Character vertex = sources.poll();
            sortedOrder.append(vertex);
            // get the node's children to decrement their in-degrees
            List<Character> children = graph.get(vertex);
            // decrement each child inDegree by 1
            for (Character child : children) {
                inDegree.put(child, inDegree.get(child) - 1);
                if (inDegree.get(child) == 0) { // inDegree of any child is 0 add them to sources
                    sources.add(child);
                }
            }
        }
        // if sortedOrder doesn't contain all characters then there is a cyclic dependency between characters
        if (sortedOrder.length() != inDegree.size()) {
            return "";
        }
        return sortedOrder.toString();
    }


}
