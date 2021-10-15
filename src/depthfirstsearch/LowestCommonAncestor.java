package depthfirstsearch;

public class LowestCommonAncestor {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        int value1 = 1;
        int value2 = 9;
        System.out.println("Input: [12, 7, 1, 9, null, 10, 5]");
        System.out.println("value1: " + value1);
        System.out.println("value2: " + value2);
        TreeNode ancestor = lowestCommonAncestor(root, value1, value2);
        System.out.println("Output: " + ancestor.val);
    }

    private static TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if (root == null || root.val == p || root.val == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
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
