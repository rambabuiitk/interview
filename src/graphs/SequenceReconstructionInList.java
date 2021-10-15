package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class SequenceReconstructionInList {
    public static void main(String[] args) {
        List<List<Integer>> sequence = new ArrayList<>();
        sequence.add(Arrays.asList(5, 2, 6, 3));
        sequence.add(Arrays.asList(4, 1, 5, 2));
        int[] originalSequence = new int[]{4, 1, 5, 2, 6, 3};
        System.out.println("prerequisites: " + sequence);
        boolean output = sequenceReconstruction(originalSequence, sequence);
        System.out.println("Output: " + output);
    }

    private static boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        List<Integer> sortedOrder = new ArrayList<>();
        if (seqs.size() == 0) {
            return false;
        }

        // a. Initialize the graph
        // count of incoming edges for every vertex
        // key is vertex and value is total incoming edge to that vertex
        HashMap<Integer, Integer> inDegree = new HashMap<>();
        // generate adjacency list graph with key as parent and values as list of child vertex
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (List<Integer> seq : seqs) {
            for (int i = 0; i < seq.size(); i++) {
                inDegree.putIfAbsent(seq.get(i), 0);
                graph.putIfAbsent(seq.get(i), new ArrayList<Integer>());
            }
        }

        // b. Build the graph here each
        // here each entry in sequence can have more numbers for example: [3, 1, 5]
        // meaning 3 is parent of 1 and 1 is parent of 5
        for(List<Integer> seq : seqs) {
            for (int i = 1; i < seq.size(); i++) {
                // This is slightly different compared to other Course Selection Problems
                // Here we are already given the sequence so (a, b, c, d)
                // so inorder to complete d we need to first complete c , b and a
                // a(parent) -> b -> c -> d(child)
                int parent = seq.get(i - 1);
                int child  = seq.get(i);
                graph.get(parent).add(child); // put the child into it's parent's list
                inDegree.put(child, inDegree.get(child) + 1); // increment child's inDegree
            }
        }
        // if we dont have ordering rules for all the numbers we wont be able to uniquely construct
        if (inDegree.size() != org.length) {
            return false;
        }

        // c. Find all sources i.e., all numCourses with 0 in-degrees and put them in the queue
        Queue<Integer> sources = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                sources.add(entry.getKey());
            }
        }

        // here we will do a BFS traversal
        // d. For each source, add it to the sortedOrder and subtract one from all of its children's in-degrees
        // if a child's in-degree becomes zero, add it to the sources queue
        while (!sources.isEmpty()) {
            // more than one sources mean, there is more than one way to reconstruct the sequence
            if (sources.size() > 1) {
                return false;
            }
            // the next source (or number) is different from the original sequence
            if (org[sortedOrder.size()] != sources.peek()) {
                return false;
            }
            int vertex = sources.poll();
            // here since we prerequisite list. It should be entered at the top
            sortedOrder.add(vertex);
            List<Integer> children = graph.get(vertex); // get the node's children to decrement their in-degrees
            for (int child : children) {
                inDegree.put(child, inDegree.get(child) - 1); // decrement the inDegree by 1 as parent src is used.
                if (inDegree.get(child) == 0) {
                    sources.add(child);
                }
            }
        }

        // if sortedOrder's size is not equal to original sequence's size, there is no unique way to construct
        return sortedOrder.size() == org.length;
    }
}
