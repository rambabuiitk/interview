package depthfirstsearch;

import java.util.ArrayList;
import java.util.List;

public class FindAllTreePaths {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        int sum = 23;
        System.out.println("Input: [12, 7, 1, 4, 10, 5]");
        System.out.println("Sum : " + sum);
        List<List<Integer>> result = findPathsRecursive(root, sum);
        System.out.println("Output: " + result);
    }

    // DFS Using Recursion
    private static List<List<Integer>> findPathsRecursive(final TreeNode root, final int sum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findPathsHelper(root, sum, currentPath, allPaths);
        return allPaths;
    }

    private static void findPathsHelper(final TreeNode currentNode, final int sum,
                                           final List<Integer> currentPath,
                                           final List<List<Integer>> allPaths) {
        if (currentNode == null) {
            return;
        }
        // add the current node to the path
        currentPath.add(currentNode.val);

        if (currentNode.val == sum && currentNode.left == null && currentNode.right == null) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            // traverse the left sub-tree
            findPathsHelper(currentNode.left, sum - currentNode.val, currentPath, allPaths);
            // traverse the right sub-tree
            findPathsHelper(currentNode.right, sum - currentNode.val, currentPath, allPaths);
        }
        // remove the current node from the path to backtrack,
        // we need to remove the current node while we are going up the recursive call stack.
        currentPath.remove(currentPath.size() - 1);
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
