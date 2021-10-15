package depthfirstsearch;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class CountTotalPathForASum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        int sum = 11;
        System.out.println("Input: [12, 7, 1, 4, 10, 5]");
        System.out.println("sum : " + sum);
        System.out.println("Output: " + countPaths(root, sum));
    }

    public static int countPaths(TreeNode root, int S) {
        List<Integer> currentPath = new LinkedList<>();
        return countPathsRecursive(root, S, currentPath);
    }

    private static int countPathsRecursive(TreeNode currentNode, int S, List<Integer> currentPath) {
        if (currentNode == null) {
            return 0;
        }

        // add the current node to the path
        currentPath.add(currentNode.val);
        int pathCount = 0;
        int pathSum = 0;
        // find the sums of all sub-paths in the current path list
        // ListIterator allows to traverse the list in both the direction
        ListIterator<Integer> pathIterator = currentPath.listIterator(currentPath.size());
        // hasPrevious return true if this list has more elements when traversing in the reverse direction.
        while (pathIterator.hasPrevious()) {
            // previous returns previous element in the list and moves cursor backwards.
            pathSum += pathIterator.previous();
            // if the sum of any sub-path is equal to 'S' we increment our path count.
            if (pathSum == S) {
                pathCount++;
            }
        }

        // traverse the left sub-tree
        pathCount += countPathsRecursive(currentNode.left, S, currentPath);
        // traverse the right sub-tree
        pathCount += countPathsRecursive(currentNode.right, S, currentPath);

        // remove the current node from the path to backtrack,
        // we need to remove the current node while we are going up the recursive call stack.
        currentPath.remove(currentPath.size() - 1);

        return pathCount;
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
