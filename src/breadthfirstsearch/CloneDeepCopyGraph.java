package breadthfirstsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Input: [[2,4],[1,3],[2,4],[1,3]]
 *
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 *
 * Explanation:
 * There are 4 nodes in the graph. 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 */

public class CloneDeepCopyGraph {
    public static void main(String[] args) {
        Node root = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        root.neighbors = new ArrayList<>(Arrays.asList(two, four));
        two.neighbors = new ArrayList<>(Arrays.asList(root, three));
        three.neighbors = new ArrayList<>(Arrays.asList(two, four));
        four.neighbors = new ArrayList<>(Arrays.asList(root, three));
        System.out.println("Input: [[2,4],[1,3],[2,4],[1,3]]");
        Node clonedRoot = cloneGraphUsingDFS(root);
        System.out.println("using dfs: " + clonedRoot.val);
        System.out.println("---");
        clonedRoot = cloneGraphUsingBFS(root);
        System.out.println("using bfs: " + clonedRoot.val);
    }

    // time : O(N) number of nodes in graph
    // space: O(2N) map and recursive call
    private static Node cloneGraphUsingDFS(Node node) {
        Map<Node, Node> map = new HashMap<>();
        return cloneGraphDFS(node, map);
    }

    private static Node cloneGraphDFS(Node node, Map<Node, Node> visited) {
        if (node == null) {
            return node;
        }
        // If the node was already visited before.
        // Return the clone from the visited dictionary.
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        // Create a clone for the given node.
        // Note that we don't have cloned neighbors as of now, hence [].
        Node cloneNode = new Node(node.val, new ArrayList());
        // The key is original node and value being the clone node.
        visited.put(node, cloneNode);
        // Iterate through the neighbors to generate their clones
        // and prepare a list of cloned neighbors to be added to the cloned node.
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraphDFS(neighbor, visited));
        }
        return cloneNode;
    }


    // time : O(N) number of nodes in graph
    // space: O(2N) map and queue
    private static Node cloneGraphUsingBFS(Node node) {
        if (node == null) {
            return node;
        }
        // Hash map to save the visited node and it's respective clone
        // as key and value respectively. This helps to avoid cycles.
        Map<Node, Node> visited = new HashMap<>();
        // Put the first node in the queue
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        // Clone the node and put it in the visited dictionary.
        visited.put(node, new Node(node.val, new ArrayList<>()));
        // Start BFS traversal
        while (!queue.isEmpty()) {
            // Pop a node say "temp" from the from the front of the queue.
            Node temp = queue.poll();
            // Iterate through all the neighbors of the node "temp"
            for (Node neighbour : temp.neighbors) {
                if (!visited.containsKey(neighbour)) {
                    // Clone the neighbor and put in the visited, if not present already
                    visited.put(neighbour, new Node(neighbour.val, new ArrayList<>()));
                    // Add the newly encountered node to the queue.
                    queue.offer(neighbour);
                }
                // Add the clone of the neighbor to the neighbors of the clone node "temp".
                Node clonedTemp = visited.get(temp);
                Node clonedTempNeighbor = visited.get(neighbour);
                clonedTemp.neighbors.add(clonedTempNeighbor);
            }
        }
        return visited.get(node);
    }




    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }
}

/*
Time Complexity: O(N) where N is number of nodes in graph.
Space Complexity: O(2N) where N is number of nodes in graph.
 */