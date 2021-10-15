package breadthfirstsearch;

/**
 * Given a binary tree, connect each node with its level order successor. The last node of each level should point to the first node of the next level.
 *
 * Input: [12, 7, 1, 9, null, 10, 5]
 * Output:
 * 12 -> 7 -> 1 -> 9 -> 10 > 5
 */
import java.util.LinkedList;
import java.util.Queue;

public class ConnectAllLevelOrderSiblings {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Input: [12, 7, 1, 9, null, 10, 5]");
        connect(root);

        TreeNode current = root;
        System.out.println("Output: ");
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
    }

    private static void connect(final TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode previous = null;
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            if (previous != null) {
                previous.next = currentNode;
            }
            previous = currentNode;

            if (currentNode.left != null) {
                queue.offer(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.offer(currentNode.right);
            }
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode next;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
            next = null;
        }
    }
}

// Time Complexity: O(N) where N is number of nodes the Tree
// Space Complexity: O(N) where N is number of nodes the Tree
