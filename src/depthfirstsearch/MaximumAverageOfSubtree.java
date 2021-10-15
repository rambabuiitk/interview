package depthfirstsearch;

public class MaximumAverageOfSubtree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Input: [12, 7, 1, 9, null, 10, 5]");
        double result = maximumAverageSubtree(root);
        System.out.println("Output: " + result);
    }

    private static double maximumAverageSubtree(TreeNode root) {
        NodeData nodeData = maximumAverageRecursive(root);
        return nodeData.avg;
    }

    private static NodeData maximumAverageRecursive(TreeNode currNode) {
        if (currNode == null) {
            return new NodeData(0, 0, 0);
        }
        // recursively get left node/subtree data
        NodeData leftNodeData = maximumAverageRecursive(currNode.left);
        // recursively get right node/subtree data
        NodeData rightNodeData = maximumAverageRecursive(currNode.right);
        // recursively get the max avg of left and right node/subtree
        double maxChildAvg = Math.max(leftNodeData.avg, rightNodeData.avg);
        // current node sum is sum of current node value and left + right subtree
        double currNodeSum = currNode.val + leftNodeData.sum + rightNodeData.sum;
        // current node count is current node + left + right count
        int currNodeCount = 1 + leftNodeData.count + rightNodeData.count;
        double currNodeAvg = currNodeSum / currNodeCount;
        // max average is the max average of child vs the current node avg
        double maxAvg = Math.max(maxChildAvg, currNodeAvg);
        // we return node data with new maxAvg
        return new NodeData(currNodeSum, maxAvg, currNodeCount);
    }

    private static class NodeData {
        double sum;
        double avg;
        int count;

        NodeData(double sum, double avg, int count) {
            this.sum = sum;
            this.avg = avg;
            this.count = count;
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
