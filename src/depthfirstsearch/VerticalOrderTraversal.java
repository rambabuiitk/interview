package depthfirstsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class VerticalOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        List<List<Integer>> result = verticalOrder(root);
        System.out.println("Input: [12, 7, 1, 9, null, 10, 5]");
        System.out.println("Output: " + result);
    }

    // map <column treemap<row, values>>
    private static Map<Integer, List<Integer>> map = new HashMap<>();
    // min column
    private static int minX = 0;
    // max column
    private static int maxX = 0;

    public static List<List<Integer>> verticalOrder(TreeNode root) {
        dfs(root, 0, 0);
        System.out.println(map);
        List<List<Integer>> list = new ArrayList<>();
        for (int i = minX; i <= maxX; i++) {
            list.add(new ArrayList(map.get(i)));
        }
        return list;
    }

    private static void dfs(TreeNode node, int col, int row) {
        if (node == null) {
            return;
        }
        minX = Math.min(minX, col);
        maxX = Math.max(maxX, col);
        if (map.get(col) == null) {
            map.put(col, new ArrayList<>());
        }
        map.get(col).add(node.val);
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
