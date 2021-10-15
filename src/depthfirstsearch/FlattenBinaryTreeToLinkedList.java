package depthfirstsearch;

public class FlattenBinaryTreeToLinkedList {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Input: [12, 7, 1, 9, null, 10, 5]");
        flatten(root);
        System.out.println("Output: ");
        TreeNode curr = root;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.right;
        }
    }

    private static void flatten(TreeNode root) {
        // here do not return the value returned by flattenUsingRecursion
        // as this is just the rightTail node
        flattenUsingRecursion(root);
    }

    // similar to dfs we use recursion here.
    private static TreeNode flattenUsingRecursion(TreeNode node) {
        // Handle the null scenario
        if (node == null) {
            return null;
        }
        // For a leaf node, we simply return the node as is.
        if (node.left == null && node.right == null) {
            return node;
        }
        //Recursively flatten the left subtree
        TreeNode leftTail = flattenUsingRecursion(node.left);
        //Recursively flatten the right subtree
        TreeNode rightTail = flattenUsingRecursion(node.right);

        // everytime we put the left subtree between node and its right side
        if (leftTail != null) {
            leftTail.right = node.right; // left subtree between node and its right side
            node.right = node.left;
            node.left = null;
        }

        // we want to return the right tail if exists so that recursively we will use this tail to join the current node
        return rightTail == null ? leftTail : rightTail;
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
