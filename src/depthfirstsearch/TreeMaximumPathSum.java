package depthfirstsearch;

public class TreeMaximumPathSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Input: [12, 7, 1, 9, null, 10, 5]");
        int output = findMaximumPathSum(root);
        System.out.println("Maximum Path Sum: " + output);
    }

    private static int maxSum;

    private static int findMaximumPathSum(final TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        findMaximumPathSumRecursive(root);
        return maxSum;
    }

    private static int findMaximumPathSumRecursive(final TreeNode currentNode) {
        if (currentNode == null) {
            return 0;
        }
        int leftSum = findMaximumPathSumRecursive(currentNode.left);
        int rightSum = findMaximumPathSumRecursive(currentNode.right);
         // ignore paths with negative sums, since we need to find the maximum sum we should
        leftSum = Math.max(leftSum, 0); 
        rightSum = Math.max(rightSum, 0);
        // maximum path sum at the current node will be equal to the sum from the left subtree +
        // the sum from right subtree + val of current node
        int localMax = leftSum + rightSum + currentNode.val;
        // update the global maximum sum
        maxSum = Math.max(maxSum, localMax);
        return Math.max(leftSum, rightSum) + currentNode.val;
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
