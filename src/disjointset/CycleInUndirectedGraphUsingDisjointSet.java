package disjointset;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CycleInUndirectedGraphUsingDisjointSet {
    public static void main(String[] args) {
        int vertices = 3;
        int[][] edges = new int[][]{{0, 1}, {1, 2}, {2, 0}};
        System.out.println("Vertices: " + vertices);
        System.out.println("Edges: " + Arrays.deepToString(edges));
        // Here the graph is already given with vertices and edges
        // not creating adjacency list or adjacency matrix for graph as it is not needed.
        final Graph graph = new Graph(vertices, edges);
        // we need to write isCycle, find and union methods
        boolean isCycle = isCycle(graph);
        System.out.println("Cycle: " + isCycle);
    }

    private static boolean isCycle(Graph graph) {
        // creating a map for parent to child relationship for each vertices


        // Iterate through all edges of graph, find subset of both
        // vertices of every edge, if both subsets are same, then
        // there is cycle in graph.
        for (int i = 0; i < graph.edges.length; ++i) {
            int x = graph.find(graph.edges[i][0]);
            int y = graph.find(graph.edges[i][1]);

            // if both subsets are same, then there is cycle in graph.
            if (x == y) {
                // for edge 2-0 : parent of 0 is 2 == 2 and so we detected a cycle
                return true;
            }
            // keep doing union/merge of sets
            graph.union(x, y);
        }
        return false;
    }

    private static class Graph {
        int vertices;
        int[][] edges;
        Map<Integer, Integer> childToParentMap;

        // We are not creating adjacency list or adjacency matrix for this problem
        public Graph(int vertices, int[][] edges) {
            this.vertices = vertices;
            this.edges = edges;
            childToParentMap = new HashMap<>();

            // initialize map
            for (int i = 0; i < vertices; i++) {
                // put parent as null for each of the vertices
                childToParentMap.put(i, null);
            }
        }

        // A utility function to find the subset of an child element
        int find(int child) {
            if (childToParentMap.get(child) == null) {
                return child;
            }

            // recursively do dfs upwards to find the topmost parent
            return find(childToParentMap.get(child));
        }

        // A utility function to do union of two subsets
        void union(int a, int b) {
            int aParent = find(a); // find parent of a
            int bParent = find(b); // find parent of b

            // here we are everytime setting the parent of a_set as b_set in union
            // set the parent of all a as parent of b
            childToParentMap.put(aParent, bParent);
        }
    }
}
