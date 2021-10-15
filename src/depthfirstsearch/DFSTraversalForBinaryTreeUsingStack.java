package depthfirstsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFSTraversalForBinaryTreeUsingStack {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Input: [12, 7, 1, 9, null, 10, 5]");
        List<Integer> output = dfsUsingStack(root);
        System.out.println("Output: " + output);
    }

    // Using Stack
    private static List<Integer> dfsUsingStack(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.pop();
            if (currentNode.right != null) {
                stack.push(currentNode.right);
            }
            if (currentNode.left != null) {
                stack.push(currentNode.left);
            }
            result.add(currentNode.val);
        }
        return result;
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
