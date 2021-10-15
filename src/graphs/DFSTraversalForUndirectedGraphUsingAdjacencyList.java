package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class DFSTraversalForUndirectedGraphUsingAdjacencyList {

    public static void main(String[] args) {
        int vertices = 5;
        int[][] edges = new int[][]{{2, 3}, {0, 3}, {0, 2}, {1, 2}};
        System.out.println("Vertices: " + vertices);
        System.out.println("Edges: " + Arrays.deepToString(edges));
        List<Integer> output = traverseUsingDFS(vertices, edges);
        System.out.println("DFS Traversal Order : " + output);

    }

    private static List<Integer> traverseUsingDFS(int vertices, int[][] edges) {
        List<Integer> traversedOrder = new ArrayList<>();

        // build the graph using adjacency list.
        final Graph graph = new Graph(vertices, edges);
        // Mark all the vertices as not visited(By default set as false)
        boolean[] visited = new boolean[vertices];
        for (int vertex = 0; vertex < vertices; vertex++) {
            if (!visited[vertex]) {
                // Create a stack for DFS
                Stack<Integer> stack = new Stack<>();
                stack.push(vertex);
                // while queue is not empty
                while (!stack.isEmpty()) {
                    // Dequeue a vertex from queue and print it
                    int source = stack.pop();
                    // Stack may contain same vertex twice.
                    // So we need to visit only if it is not visited.
                    if(!visited[source]) {
                        visited[source] = true;
                        traversedOrder.add(source);
                    }
                    // Get all adjacent vertices of the popped vertex s
                    // If a adjacent has not been visited, then push it to the stack.
                    List<Integer> neighbours = graph.map.get(source);
                    for (int neighbour : neighbours) {
                        if (!visited[neighbour]) {
                            stack.push(neighbour);
                        }
                    }
                }
            }
        }
        return traversedOrder;
    }

    private static class Graph {
        int vertices;
        // Here instead of HashMap<Integer, List<Integer>>
        // we can also use LinkedList[] or List<List<Integer>>
        Map<Integer, List<Integer>> map;

        public Graph(int vertices, int[][] edges) {
            this.vertices = vertices;
            this.map = new HashMap<>(vertices);
            // 1. Initialize the graph
            for (int i = 0; i < vertices; i++) {
                map.put(i, new ArrayList<>());
            }

            // 2. Generate the graph
            for (int i = 0; i < edges.length; i++) {
                this.addEdge(edges[i][0], edges[i][1]);
            }
        }

        void addEdge(int a, int b) {
            // since this is an undirected graph we have to add edge in both the vertex a and b
            map.get(a).add(b);
            map.get(b).add(a);
        }

    }

}
