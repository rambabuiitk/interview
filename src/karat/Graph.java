package karat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph {

    public static List<Integer> findNodesWithZeroOrOneParent(int[][] edges) {
        if (edges == null || edges.length == 0) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge: edges) {
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        for(Map.Entry<Integer, List<Integer>> entry: graph.entrySet()) {
            if (entry.getValue().size() == 0 || entry.getValue().size() == 1) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    public boolean hasCommonAncestor(int[][] edges, int x, int y) {
        if (edges == null || edges.length == 0) {
            return false;
        }

        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge: edges) {
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        Set<Integer> parentsOfX = findAllParents(graph, x);
        Set<Integer> parentsOfY = findAllParents(graph, y);

        if (parentsOfX.retainAll(parentsOfY)) {
            return true;
        }

        return false;
    }

    private Set<Integer> findAllParents(Map<Integer, List<Integer>> directParents, int x) {
        Set<Integer> result = new HashSet<>();
        Stack<Integer> st = new Stack<>();
        st.push(x);
        while (!st.isEmpty()) {
            int curr = st.pop();
            List<Integer> parents = directParents.get(curr);
            if (parents == null || parents.size() == 0) {
                continue;
            }
            for (int parent: parents) {
                if (result.add(parent)) {
                    st.push(parent);
                }
            }
        }

        return result;
    }

    private Integer findEarliestAncestor(int[][] edges, int x) {
        if (edges == null || edges.length == 0) {
            return -1;
        }

        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge: edges) {
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        Queue<Integer> currLevel = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> prevLayer = null;
        currLevel.offer(x);
        while (!currLevel.isEmpty()) {
            prevLayer = new LinkedList<>();
            int size = currLevel.size();
            while (size-- > 0) {
                Integer curr = currLevel.poll();
                prevLayer.offer(curr);
                List<Integer> parents = graph.get(curr);
                if (parents == null) {
                    continue;
                }
                for (int parent : parents) {
                    if (visited.contains(parent)) {
                        continue;
                    }
                    visited.add(parent);
                    currLevel.offer(parent);
                }
            }
        }
        return prevLayer.peek();
    }

    public static void main(String[] args) {
        int[][] edges = {{1,4}, {1,5}, {2,5}, {3,6}, {6, 7}};
        System.out.println(findNodesWithZeroOrOneParent(edges));
    }
}
