package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AllCourseSchedulingWithOrder {

    public static void main(String[] args) {
        int numOfCourses = 4;
        int[][] prerequisites = new int[][]{{3, 2}, {3, 0}, {2, 0}, {2, 1}};
        System.out.println("numOfCourses: " + numOfCourses);
        System.out.println("prerequisites: " + Arrays.deepToString(prerequisites));
        List<List<Integer>> output = findOrder(numOfCourses, prerequisites);
        System.out.println("Output: " + output);
        System.out.println("---");
        numOfCourses = 2;
        prerequisites = new int[][]{{0, 1}, {1, 0}};
        System.out.println("numOfCourses: " + numOfCourses);
        System.out.println("prerequisites: " + Arrays.deepToString(prerequisites));
        output = findOrder(numOfCourses, prerequisites);
        System.out.println("Output: " + output);
    }

    public static List<List<Integer>> findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> sortedOrder = new ArrayList<>();
        // no courses to complete
        if (numCourses <= 0 && prerequisites.length == 0) {
            return result;
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
            int parent = prerequisites[i][1]; // source or parent
            int child = prerequisites[i][0]; // child
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

        printAllTopologicalSorts(graph, inDegree, sources, sortedOrder, result);

        return result;
    }


    private static void printAllTopologicalSorts(HashMap<Integer, List<Integer>> graph,
                                                 HashMap<Integer, Integer> inDegree,
                                                 Queue<Integer> sources,
                                                 List<Integer> sortedOrder,
                                                 List<List<Integer>> result) {
        if (!sources.isEmpty()) {
            for (Integer vertex : sources) {
                sortedOrder.add(vertex);
                Queue<Integer> sourcesForNextCall = cloneQueue(sources);
                // only remove the current source, all other sources should remain in the queue for the next call
                sourcesForNextCall.remove(vertex);
                List<Integer> children = graph.get(vertex); // get the node's children to decrement their in-degrees
                for (int child : children) {
                    inDegree.put(child, inDegree.get(child) - 1);
                    if (inDegree.get(child) == 0)
                        sourcesForNextCall.add(child); // save the new source for the next call
                }

                // recursive call to print other orderings from the remaining (and new) sources
                printAllTopologicalSorts(graph, inDegree, sourcesForNextCall, sortedOrder, result);

                // backtrack, remove the vertex from the sorted order and put all of its children back to consider
                // the next source instead of the current vertex
                sortedOrder.remove(vertex);
                for (int child : children)
                    inDegree.put(child, inDegree.get(child) + 1);
            }
        }

        // if sortedOrder doesn't contain all tasks, either we've a cyclic dependency between tasks, or
        // we have not processed all the tasks in this recursive call
        if (sortedOrder.size() == inDegree.size()) {
            result.add(new ArrayList<>(sortedOrder));

        }
    }

    // makes a deep copy of the queue
    private static Queue<Integer> cloneQueue(Queue<Integer> queue) {
        Queue<Integer> clone = new LinkedList<>();
        for (Integer num : queue) {
            clone.add(num);
        }
        return clone;
    }

}
