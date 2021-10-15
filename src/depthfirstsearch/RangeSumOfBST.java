package depthfirstsearch;

import java.util.Stack;

public class RangeSumOfBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        int L = 3;
        int R = 5;
        System.out.println("Input: [4, 2, 6, 1, 3, 5, 7]");
        System.out.println("L: " + L);
        System.out.println("R: " + R);
        int output = rangeSumBST(root, L, R);
        System.out.println("Output: " + output);
    }
    // dfs traversal using stack
    private static int rangeSumBST(TreeNode root, int L, int R) {
        int sum = 0;
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                // if the node value falls in our range add the result
                if (L <= node.val && node.val <= R) {
                    sum = sum + node.val;
                }
                // also if the value is greater than L -> iterate its child
                if (L < node.val) {
                    stack.push(node.left);
                }
                // also if the value is smaller than R -> iterate its child
                if (node.val < R) {
                    stack.push(node.right);
                }
            }
        }
        return sum;
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
