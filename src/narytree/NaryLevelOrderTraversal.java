package narytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NaryLevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode three = new TreeNode(3);
        TreeNode two = new TreeNode(2);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        root.children = new ArrayList<>(Arrays.asList(three, two, four));
        three.children = new ArrayList<>(Arrays.asList(five, six));
        System.out.println("Input: [1,null,3,2,4,null,5,6]");
        List<List<Integer>> output = levelOrder(root);
        System.out.println("Output: " + output);
    }

    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevelNodes = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevelNodes.add(currentNode.val);
                if (currentNode.children != null && !currentNode.children.isEmpty()) {
                    queue.addAll(currentNode.children);
                }
            }
            result.add(currentLevelNodes);
        }
        return result;
    }

    private static class TreeNode {
        public int val;
        public List<TreeNode> children;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
