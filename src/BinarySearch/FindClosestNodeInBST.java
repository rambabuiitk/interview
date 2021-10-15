package BinarySearch;

public class FindClosestNodeInBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        System.out.println("Input: [4, 2, 6, 1, 3, 5, 7]");
        double target = 3.71428;
        System.out.println("target: " + target);
        int output = closestValue(root, target);
        System.out.println("Output: " + output);

    }

    // binary search in tree
    // time: O(H) where h is height of tree from root to leaf
    private static int closestValue(TreeNode root, double target) {
        int val;
        int closest = root.val; // for child tree defaulting closest to root
        while (root != null) {
            val = root.val;

            if (Math.abs(val - target) < Math.abs(closest - target)) {
                closest = val;
            }

            if (target < root.val) { // if given target is less than root
                root = root.left; // search on the left side of root
            } else {
                root = root.right; // search on the right side of root
            }
        }
        return closest;
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
