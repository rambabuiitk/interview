package graphs;

import java.util.LinkedList;
import java.util.Queue;

public class ISGraphBipartiteWithAdjacencyMatrix {
    // if we find any adjacent nodes with same color meaning graph cannot be devided into 2 sub grapsh and it is not bipartite.
    // also if there is a cycle in graph it is not bipartite
    
    public boolean isBipartite(int[][] graph) {
        int vertices = graph.length;
        // 0 means not visited 
        // 1 is visted
        // 2 means neighbour is 1
        int[] visited = new int[vertices];

        for (int vertex = 0; vertex < vertices; vertex++) {
            // if the vertex is not visited
            if (visited[vertex] == 0) {
                // add the vertex to the queue
                Queue<Integer> queue = new LinkedList<>();
                queue.add(vertex);
                // visit the vertex0
                visited[vertex] = 1;
                while (!queue.isEmpty()) {
                    int source = queue.poll();
                    for (int neighbour : graph[source]) {
                        // if neighbour is not visited.
                        if (visited[neighbour] == 0) {
                            // if the source is 1 than neighbour is 2 else neighbour will be 1
                            visited[neighbour] = visited[source] == 1 ? 2 : 1;
                            queue.add(neighbour);
                        } else { // if it is already visited and has same color as source
                            // invalid input and graph is bipartite
                            if (visited[neighbour] == visited[source]) {
                                return false;
                            }
                        }
                    }

                }
            }
        }
        return true;
    }
}