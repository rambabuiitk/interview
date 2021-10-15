package breadthfirstsearch;

/*
Given a binary tree, connect each node with its level order successor. The last node of each level should point to a null node.

Input: [12, 7, 1, 9, null, 10, 5]
Output:
12
7 1
9 10 5
 */
import java.util.LinkedList;
import java.util.Queue;

public class ConnectLevelOrderSiblings {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Input: [12, 7, 1, 9, null, 10, 5]");
        connect(root);
        System.out.println("Level order traversal using 'next' pointer: ");
        root.printLevelOrder();
    }

    private static void connect(final TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // Initialize previousNode at each level.
            // Store the previousNode so that we can point previousNode to currentNode
            TreeNode previousNode = null;
            int levelSize = queue.size();
            // connect all nodes of this level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                // if previousNode is null we reached end of level
                // if previous node is not null point previous node to currentNode
                if (previousNode != null) {
                    previousNode.next = currentNode;
                }
                // make currentNode as previous for upcoming node
                previousNode = currentNode;

                // insert the children of current node in the queue
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
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
            left = right = next = null;
        }

        // wrapper class just for printing the tree.
        // level order traversal using 'next' pointer
        public void printLevelOrder() {
            TreeNode nextLevelRoot = this;
            while (nextLevelRoot != null) {
                TreeNode current = nextLevelRoot;
                nextLevelRoot = null;
                while (current != null) {
                    System.out.print(current.val + " ");
                    if (nextLevelRoot == null) {
                        if (current.left != null)
                            nextLevelRoot = current.left;
                        else if (current.right != null)
                            nextLevelRoot = current.right;
                    }
                    current = current.next;
                }
                System.out.println();
            }
        }
    }
}

// Time Complexity: O(N) where N is number of nodes the Tree
// Space Complexity: O(N) where N is number of nodes the Tree
