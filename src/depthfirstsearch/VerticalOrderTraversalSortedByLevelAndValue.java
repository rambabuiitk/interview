package depthfirstsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class VerticalOrderTraversalSortedByLevelAndValue {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        List<List<Integer>> result = verticalTraversal(root);
        System.out.println("Input: [4, 2, 6, 1, 3, 5, 7]");
        System.out.println("Output: " + result);
    }

    private static Map<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new HashMap<>();
    private static int minX = 0, maxX = 0;

    private static List<List<Integer>> verticalTraversal(TreeNode root) {
        dfs(root, 0, 0);
        List<List<Integer>> vertical = new ArrayList<>();
        for (int i = minX; i <= maxX; i++) {
            List<Integer> level = new ArrayList<Integer>();
            for (int key : map.get(i).keySet()) {
                while (!(map.get(i).get(key)).isEmpty()) {
                    level.add(map.get(i).get(key).poll());
                }
            }
            if(!level.isEmpty()) { // do not add empty list
                vertical.add(level);
            }
        }
        return vertical;
    }

    private static void dfs(TreeNode node, int col, int row) {
        if (node == null) return;
        minX = Math.min(minX, col);
        maxX = Math.max(maxX, col);
        if (map.get(col) == null) {
            map.put(col, new TreeMap<Integer, PriorityQueue<Integer>>());
        }
        if (map.get(col).get(row) == null) {
            map.get(col).put(row, new PriorityQueue<Integer>());
        }
        map.get(col).get(row).add(node.val);
        dfs(node.left, col - 1, row + 1);
        dfs(node.right, col + 1, row + 1);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
