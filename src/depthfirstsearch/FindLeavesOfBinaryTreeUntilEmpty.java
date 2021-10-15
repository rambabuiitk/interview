package depthfirstsearch;

import java.util.ArrayList;
import java.util.List;

public class FindLeavesOfBinaryTreeUntilEmpty {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        List<List<Integer>> result = findLeaves(root);
        System.out.println("Input: [12, 7, 1, 9, null, 10, 5]");
        System.out.println("Output: " + result);
    }

    private static List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        findLeavesHelper(root, result);
        return result;
    }

    private static int findLeavesHelper(TreeNode root, List<List<Integer>> result) {
        if(root == null) {
            return -1;
        }
        // height of node on the left side
        int leftLevel = findLeavesHelper(root.left, result);
        // height of the node on right side
        int rightLevel = findLeavesHelper(root.right, result);
        // current height of the node is 1 + max(height(node.left), height(node.right))
        // th
        int level = 1+ Math.max(leftLevel, rightLevel);
        // if we found a new level add that to the list.
        if(result.size() < level + 1) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(root.val);
        root.left = null;
        root.right = null;
        return level;
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
