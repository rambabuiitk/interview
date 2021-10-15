package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFSTraversalForUndirectedGraphUsingAdjacencyList {

    public static void main(String[] args) {
        int vertices = 5;
        int[][] edges = new int[][]{{2, 3}, {0, 3}, {0, 2}, {1, 2}};
        System.out.println("Vertices: " + vertices);
        System.out.println("Edges: " + Arrays.deepToString(edges));
        List<Integer> output = traverseUsingBFS(vertices, edges);
        System.out.println("BFS Traversal Order : " + output);

    }

    private static List<Integer> traverseUsingBFS(int vertices, int[][] edges) {
        List<Integer> traversedOrder = new ArrayList<>();

        // build the graph using adjacency list.
        final Graph graph = new Graph(vertices, edges);

        // Mark all the vertices as not visited(By default set as false)
        boolean[] visited = new boolean[vertices];
        for (int vertex = 0; vertex < vertices; vertex++) {
            if (!visited[vertex]) {
                // Create a queue for BFS
                Queue<Integer> queue = new LinkedList<>();
                queue.add(vertex);
                // while queue is not empty
                while (!queue.isEmpty()) {
                    // Dequeue a vertex from queue and print it
                    int source = queue.poll();
                    visited[source] = true;
                    traversedOrder.add(source);
                    // Get all adjacent vertices of the dequeued vertex source
                    // If a adjacent has not been visited, then mark it visited and enqueue it
                    List<Integer> neighbours = graph.map.get(source);
                    for (int neighbour : neighbours) {
                        if (!visited[neighbour]) {
                            visited[neighbour] = true;
                            queue.add(neighbour);
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
