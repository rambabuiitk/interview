package disjointset;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CycleInUndirectedGraphUsingRankAndPathCompression {
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
        int[][] edges;
        int vertices;
        // creating a map for parent to child relationship for each vertices
        // here instead of creating child to parent mapping we create parent node
        // parent node has parent of child and also rank
        Map<Integer, ParentNode> childToParentMap;

        // We are not creating adjacency list or adjacency matrix for this problem
        public Graph(int vertices, int[][] edges) {
            this.vertices = vertices;
            this.edges = edges;
            childToParentMap = new HashMap<>();
            // initialize map
            for (int i = 0; i < vertices; i++) {
                // set the parent of all nodes as nodes itself and rank as 0
                childToParentMap.put(i, new ParentNode(i, 0));
            }
        }

        // A utility function to find the set using path compression technique
        public int find(int child) {
            // if the parent is not the current node meaning parent is set.
            if (childToParentMap.get(child).parent != child) {
                // so find the root parent of current node and set it as parent of current node
                int rootVal = find(childToParentMap.get(child).parent);

                childToParentMap.get(child).parent = rootVal;
            }

            // return the final parent
            return childToParentMap.get(child).parent;
        }

        // A utility function to do union of two subsets
        public void union(int a, int b) {
            int aRoot = find(a); // find root parent of a
            int bRoot = find(b); // find root parent of b

            // smaller rank will be merged into higher rank
            if (childToParentMap.get(aRoot).rank < childToParentMap.get(bRoot).rank) {
                childToParentMap.get(aRoot).parent = bRoot;
            } else if (childToParentMap.get(bRoot).rank < childToParentMap.get(aRoot).rank) {
                childToParentMap.get(bRoot).parent = aRoot;
            } else { // both the ranks are same
                // if both rank are same lets assume we will always make bRoot as parent of aRoot
                childToParentMap.get(aRoot).parent = bRoot;
                // also increment the rank of bRoot as we added aRoot
                childToParentMap.get(bRoot).rank++;
            }
        }
    }

    private static class ParentNode {
        int parent;
        int rank;

        ParentNode(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }

    }
}
