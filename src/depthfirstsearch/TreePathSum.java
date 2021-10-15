package depthfirstsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreePathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Input: [12, 7, 1, 9, null, 10, 5]");
        System.out.println("Output: " + hasPathRecursive(root, 23));
        System.out.println("---");
        System.out.println("Output: " + hasPathUsingStack(root, 23));

    }

    // DFS Using Recursion
    private static boolean hasPathRecursive(final TreeNode root, final int sum) {
        if (root == null) {
            return false;
        }
        // if the root value matches the remaining sum and it is the leaf node
        if (root.val == sum && root.left == null && root.right == null) {
            return true;
        }
        // recursively call to traverse the left and right sub-tree
        // return true if any of the two recursive call return true
        return hasPathRecursive(root.left, sum - root.val) || hasPathRecursive(root.right, sum - root.val);
    }

    // DFS Using Stack
    private static boolean hasPathUsingStack(final TreeNode root, final int sum) {
        if (root == null) {
            return false;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.pop();
            if (currentNode.val == sum && currentNode.left == null && currentNode.right == null) {
                return true;
            }
            if (currentNode.right != null) {
                currentNode.right.val = currentNode.val + currentNode.right.val;
                stack.push(currentNode.right);
            }
            if (currentNode.left != null) {
                currentNode.left.val = currentNode.val + currentNode.left.val;
                stack.push(currentNode.left);
            }
        }
        return false;
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
