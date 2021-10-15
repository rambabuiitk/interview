package narytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaximumDepthOfNaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode three = new TreeNode(3);
        TreeNode two = new TreeNode(2);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        root.children = new ArrayList<>(Arrays.asList(three, two, four));
        three.children = new ArrayList<>(Arrays.asList(five, six));
        System.out.println("Input: [1, null, 3, 2, 4, null, 5, 6]");
        int output = findDepth(root);
        System.out.println("using bfs: " + output);
        System.out.println("---");
        output = findDepthUsingDFS(root);
        System.out.println("using dfs recursion: " + output);
    }


    private static int findDepth(TreeNode root) {
        // if no tree return 0
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // initial maximumTreeDepth is 0
        int maximumTreeDepth = 0;
        while (!queue.isEmpty()) {
            // on each level increment the depth of tree
            maximumTreeDepth++;
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                // insert the children of current node in the queue
                if (currentNode.children != null) {
                    queue.addAll(currentNode.children);
                }
            }
        }
        return maximumTreeDepth;
    }

    private static int findDepthUsingDFS(TreeNode root) {
        // if no tree return 0
        if (root == null) {
            return 0;
        }
        if (root.children == null) {
            return 1;
        }
        List<Integer> heights = new LinkedList<>();
        for (TreeNode child : root.children) {
            heights.add(findDepthUsingDFS(child));
        }
        return Collections.max(heights) + 1; // adding 1 to include current node element
    }

    private static class TreeNode {
        public int val;
        public List<TreeNode> children;

        public TreeNode(int val) {
            this.val = val;
        }
    }

}
