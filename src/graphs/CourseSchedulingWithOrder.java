package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CourseSchedulingWithOrder {

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println("numCourses: " + numCourses);
        System.out.println("prerequisites: " + Arrays.deepToString(prerequisites));
        List<Integer> output = findOrder(numCourses, prerequisites);
        System.out.println("Output: " + output);
        System.out.println("---");
        numCourses = 2;
        prerequisites = new int[][]{{0, 1}, {1, 0}};
        System.out.println("numCourses: " + numCourses);
        System.out.println("prerequisites: " + Arrays.deepToString(prerequisites));
        output = findOrder(numCourses, prerequisites);
        System.out.println("Output: " + output);
    }

    public static List<Integer> findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> sortedOrder = new ArrayList<>();
        // no courses to complete
        if (numCourses <= 0 && prerequisites.length == 0) {
            return sortedOrder;
        }

        // a. Initialize the graph
        // count of incoming prerequisites for every vertex
        // key is vertex and value is total incoming edge to that vertex
        HashMap<Integer, Integer> inDegree = new HashMap<>();
        // generate adjacency list graph with key as parent and values as list of child vertex
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            inDegree.put(i, 0); // initialize all vertex with 0
            graph.put(i, new ArrayList<>()); // initialize graph
        }

        // b. Build the graph
        for (int i = 0; i < prerequisites.length; i++) {
            int parent = prerequisites[i][0]; // source or parent
            int child = prerequisites[i][1]; // child
            graph.get(parent).add(child); // put the child into it's parent's list
            inDegree.put(child, inDegree.get(child) + 1); // increment child's inDegree
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
            int vertex = sources.poll();
            // here since we prerequisite list. It should be entered at the top
            sortedOrder.add(0, vertex);
            List<Integer> children = graph.get(vertex); // get the node's children to decrement their in-degrees
            for (int child : children) {
                inDegree.put(child, inDegree.get(child) - 1); // decrement the inDegree by 1 as parent src is used.
                if (inDegree.get(child) == 0) {
                    sources.add(child);
                }
            }
        }

        // topological sort is not possible as the graph has a cycle
        if (sortedOrder.size() != numCourses) {
            return new ArrayList<>();
        }

        return sortedOrder;
    }

}
