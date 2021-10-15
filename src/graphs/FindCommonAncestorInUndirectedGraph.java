package graphs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindCommonAncestorInUndirectedGraph {

    public static void main(String[] args) {
        int[][] parentChildPairs = new int[][]{
                {1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7},
                {4, 5}, {4, 8}, {8, 9}
        };
        System.out.println("Input: " + Arrays.deepToString(parentChildPairs));
        int a = 7;
        int b = 9;
        int output = getCommonAncestor(parentChildPairs, a, b);
        System.out.println("Common Ancestor : " + output);
    }

    static int lowestInteger = -1;

    // Graph (childToParent and childInDegree) + DFS
    private static int getCommonAncestor(int[][] parentToChildPairs, Integer a, Integer b) {
        Graph graph = new Graph(parentToChildPairs);
        hasCommonAncestorHelper(graph.childToParent, a, b);
        return lowestInteger;

    }

    private static boolean hasCommonAncestorHelper(Map<Integer, Set<Integer>> childToParent, Integer a, Integer b) {
        if (a == b) {
            return true;
        }

        Set<Integer> parentsOfA = childToParent.get(a);
        Set<Integer> parentsOfB = childToParent.get(b);
        // if parentOfA contains B meaning --> B is parent of A
        if (parentsOfA.contains(b)) {
            lowestInteger = a;
            return true;
        }
        // if parentOfB contains A meaning --> A is parent of B
        if (parentsOfB.contains(a)) {
            System.out.println(b);
            lowestInteger = b;
            return true;
        }
        // else we get all the parentsOfA and parentsOfB and do a DFS Traversal.
        for (int nodeA : parentsOfA) {
            for (int nodeB : parentsOfB) {
                if (nodeA == nodeB) {
                    System.out.println(nodeA);
                    lowestInteger = nodeA;
                    return true;
                } else {
                    return hasCommonAncestorHelper(childToParent, nodeA, nodeB);
                }
            }
        }
        return false;
    }

    private static class Graph {
        Map<Integer, Set<Integer>> childToParent;
        // Here we do not need childInDegree so we can remove that map

        public Graph(int[][] edges) {
            this.childToParent = new HashMap<>();
            // 1. Initialize the graph
            for (int i = 0; i < edges.length; i++) {
                int parent = edges[i][0];
                int child = edges[i][1];
                childToParent.put(parent, new HashSet<>());
                childToParent.put(child, new HashSet<>());
            }

            // 2. Generate the graph
            for (int i = 0; i < edges.length; i++) {
                int parent = edges[i][0];
                int child = edges[i][1];
                this.addEdge(parent, child);
            }
        }

        void addEdge(int parent, int child) {
            childToParent.get(child).add(parent);
        }
    }
}
