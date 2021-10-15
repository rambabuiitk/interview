package disjointset;

import java.util.HashMap;
import java.util.Map;

public class IsAFriendOfB {
    public static void main(String[] args) {
        int vertices = 5;
        System.out.println("Vertices : " + vertices);
        Graph graph = new Graph(vertices);
        System.out.println("graph.union(0, 2)");
        graph.union(0, 2);
        System.out.println("graph.union(4, 2)");
        graph.union(4, 2);
        System.out.println("graph.union(3, 1)");
        graph.union(3, 1);

        // here we are writing find() and union() method for Graph
        // Check if 4 is a friend of 0
        boolean isFriend = isFriendOf(graph, 0, 4);
        System.out.println("IS 4 Friend of 0: " + isFriend);

    }

    private static boolean isFriendOf(Graph graph, int a, int b) {
        int aRoot = graph.find(a);
        int bRoot = graph.find(a);
        return aRoot == bRoot;
    }

    private static class Graph {
        int vertices;
        // to store child to parentNode relationship
        // where parentNode has parent vertex and its rank
        Map<Integer, ParentNode> childToParentMap;

        Graph(int vertices) {
            this.vertices = vertices;
            childToParentMap = new HashMap<>();

            // 1. Initializing childToParentMap with parent as same child and rank as 0
            for (int i = 0; i < vertices; i++) {
                childToParentMap.put(i, new ParentNode(i, 0));
            }
        }

        // A utility function to find the set using path compression technique
        // dfs traversal in upwards direction
        private int find(int child) {
            // if the parent is not the current node meaning parent is set.
            if (childToParentMap.get(child).parent != child) {
                // so find the root parent of current node and set it as parent of current node
                // here we are recursively finding parent like dfs
                int rootVal = find(childToParentMap.get(child).parent);

                childToParentMap.get(child).parent = rootVal;
            }

            // return the final parent
            return childToParentMap.get(child).parent;
        }

        // A utility function to do union of two subsets
        private void union(int a, int b) {
            int aRoot = find(a); // find root parent of a
            int bRoot = find(b); // find root parent of b

            // smaller rank will be merged into higher rank
            if (childToParentMap.get(aRoot).rank < childToParentMap.get(bRoot).rank) {
                childToParentMap.get(aRoot).parent = bRoot;
            } else if (childToParentMap.get(bRoot).rank < childToParentMap.get(aRoot).rank) {
                childToParentMap.get(bRoot).parent = aRoot;
            } else { // both the ranks are same
                // if both rank are same lets assume we will always make bRoot as parent of aRoot
                // doest not matter other way also.
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
