package disjointset;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TimeWhenAllAreFriends {
    public static void main(String[] args) {
        int[][] logs = new int[][]{
                {20190101, 0, 1},
                {20190104, 3, 4},
                {20190107, 2, 3},
                {20190211, 1, 5},
                {20190224, 2, 4},
                {20190301, 0, 3},
                {20190312, 1, 2},
                {20190322, 4, 5}
        };
        int N = 6;
        System.out.println("N: " + N);
        System.out.println("logs: " + Arrays.deepToString(logs));
        long output = earliestTime(logs, N);
        System.out.println("Earliest Time: " + output);
    }

    private static long earliestTime(int[][] logs, int vertices) {
        Graph graph = new Graph(vertices);
        // as the logs are not sorted by timestamp.
        // sort the logs by timestamp so we have earliest time first
        Arrays.sort(logs, (a, b) -> a[0] - b[0]);
        for(int[] log : logs) {
            int a = log[1];
            int b = log[2];
            if(graph.find(a) != graph.find(b)) {
                graph.union(a, b);
                //when union happens, we decrement one parent
                graph.vertices--;
                if (graph.vertices == 1) {
                    return log[0];
                }
            }
        }

        // found no time
        return -1;
    }

    private static class Graph {
        int vertices;
        Map<Integer, ParentNode> childToParentMap;

        public Graph(int vertices) {
            this.vertices = vertices;
            childToParentMap = new HashMap<>();

            // 1. Initializing the childToParentMap
            // for initialization each vertex will parent of itself with rank 0
            for (int i = 0; i < vertices; i++) {
                childToParentMap.put(i, new ParentNode(i, 0));
            }
        }

        public int find(int child) {
            // if the parent of current vertex is not same meaning its parent is set
            if(childToParentMap.get(child).parent != child) {
                // recursively find its root parent and update that as parent of current node
                int parentVal = find(childToParentMap.get(child).parent);
                childToParentMap.get(child).parent = parentVal;
            }
            return childToParentMap.get(child).parent;
        }

        public void union(int a, int b) {
            int aRoot = find(a); // find root parent of a
            int bRoot = find(b); // find root parent of b

            // smaller rank will be merged into higher rank
            if(childToParentMap.get(aRoot).rank < childToParentMap.get(bRoot).rank) {
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
