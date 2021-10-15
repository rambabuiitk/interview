package breadthfirstsearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, determine if it is a complete binary tree.
 *
 * Introduction
 * A complete binary tree is a binary tree in which all the levels are completely filled except possibly the lowest one, which is filled from the left.
 *
 * A complete binary tree is just like a full binary tree, but with two major differences
 *
 * All the leaf elements must lean towards the left.
 * The last leaf element might not have a right sibling i.e. a complete binary tree doesn’t have to be a full binary tree.
 *
 * Input: [4, 2, 6, 1, 3, 5, 7]
 * Output: true
 * Explanation: The last level is not filled from left.
 *
 * Input: [12, 7, 1, 9, null, 10, 5]
 * Output: false
 * Explanation: The last level nodes are not lean towards left.
 */
public class ValidateCompletenessOfBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        System.out.println("Input: [4, 2, 6, 1, 3, 5, 7]");
        boolean output = checkComplete(root);
        System.out.println("Output: " + output);
    }

    // bfs level order traversal
    // time: O(N)
    // space: O(N)
    private static boolean checkComplete(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        // after the last node all the nodes in queue should be null
        boolean seenNull = false;
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                seenNull = true;
            } else { // current parent node is not equal to null
                // if parent is not null and we have seenNull before meaning tree not complete
                if (seenNull) {
                    return false;
                }
                queue.offer(curr.left);
                queue.offer(curr.right);
            }
        }
        while (!queue.isEmpty() && queue.peek() == null) {
            queue.poll();
        }
        return queue.isEmpty();
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}

/*
Time Complexity: O(N) where is number of nodes in in the tree
Space Complexity: O(N) where is number of nodes in in the tree which as same as max size of recursive stack.
 */
