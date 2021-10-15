package depthfirstsearch;

import java.util.ArrayList;
import java.util.List;

public class AllPathsFromRootToLeaf {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Input: [12, 7, 1, 9, null, 10, 5]");
        List<List<Integer>> result = allPaths(root);
        System.out.println("Output: " + result);
    }

    // DFS Using Recursion
    private static List<List<Integer>> allPaths(final TreeNode root) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        allPathsHelper(root, currentPath, allPaths);
        return allPaths;
    }

    private static void allPathsHelper(final TreeNode currentNode,
                                        final List<Integer> currentPath,
                                        final List<List<Integer>> allPaths) {
        if (currentNode == null) {
            return;
        }
        // add the current node to the path
        currentPath.add(currentNode.val);

        if (currentNode.left == null && currentNode.right == null) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            // traverse the left sub-tree
            allPathsHelper(currentNode.left, currentPath, allPaths);
            // traverse the right sub-tree
            allPathsHelper(currentNode.right, currentPath, allPaths);
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
