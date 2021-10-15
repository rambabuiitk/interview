package depthfirstsearch;

public class BSTToSortedLinkedList {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        BSTToSortedLinkedList bt = new BSTToSortedLinkedList();
        TreeNode head = bt.treeToDoublyList(root);
        TreeNode curr = head;
        System.out.println("Output: ");
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.right;
            if (curr.val == head.val) {
                break; // breaking the Circular DoublyLinkedList
            }
        }
    }

    TreeNode head = null; // do not use static here can lead to wrong when multiple inputs
    TreeNode tail = null; // do not use static here can lead to wrong when multiple inputs

    // here we are using dfs traversal
    // time: O(N) where N is numebr of nodes
    // space: O(N) for total recursive stacks
    private TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }

        inorderTraverse(root);
        // close DoublyLinkedList
        tail.right = head;
        head.left = tail;
        return head;
    }

    // Time: O(n) Space: O(n)
    private void inorderTraverse(TreeNode node) {
        if (node != null) {
            // left
            inorderTraverse(node.left);
            // node
            if (tail != null) {
                // link the previous node (last)
                // with the current one (node)
                tail.right = node;
                node.left = tail;
            } else {
                // keep the smallest node
                // to close DoublyLinkedList later on
                head = node;
            }
            tail = node;
            // right
            inorderTraverse(node.right);
        }
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
