package depthfirstsearch;

public class TreeDiameter {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.left.left.left = new TreeNode(8);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Input: [12, 7, 1, 9, NULL, 10, 5, 8]");
        TreeDiameter diameter = new TreeDiameter();
        // recommened to not call static and not make treeDiameter static
        System.out.println("Output: " + diameter.findDiameter(root));
    }

    private int treeDiameter = 0;

    private int findDiameter(final TreeNode root) {
        calculateHeight(root);
        return treeDiameter;
    }

    private int calculateHeight(TreeNode currentNode) {
        // if root == null height of tree is 0
        if (currentNode == null) {
            return 0;
        }
        int leftTreeHeight = calculateHeight(currentNode.left);
        int rightTreeHeight = calculateHeight(currentNode.right);

        // diameter at the current node = height of left subtree + height of right subtree
        int diameter = leftTreeHeight + rightTreeHeight;
        treeDiameter = Math.max(treeDiameter, diameter);

        // height of the current node will be equal to the maximum of the hights of
        // left or right subtrees plus '1' for the current node
        return Math.max(leftTreeHeight, rightTreeHeight) + 1;
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
