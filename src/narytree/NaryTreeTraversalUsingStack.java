package narytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class NaryTreeTraversalUsingStack {
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
        List<Integer> output = preorderTraversal(root);
        System.out.println("PreOrder: " + output);
        System.out.println("---");
        root = new TreeNode(1);
        three = new TreeNode(3);
        two = new TreeNode(2);
        four = new TreeNode(4);
        five = new TreeNode(5);
        six = new TreeNode(6);
        root.children = new ArrayList<>(Arrays.asList(three, two, four));
        three.children = new ArrayList<>(Arrays.asList(five, six));
        System.out.println("Input: [1, null, 3, 2, 4, null, 5, 6]");
        output = postorderTraversal(root);
        System.out.println("PostOrder: " + output);
    }

    private static List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.pop();
            result.add(currentNode.val);
            if (currentNode.children != null) {
                Collections.reverse(currentNode.children); // were are reversing the children order in place
                stack.addAll(currentNode.children);
            }
        }
        return result;
    }

    private static List<Integer> postorderTraversal(TreeNode root) {
        // here we are making output as linkedlist as we have plans to add at of linkedlist
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.pop();
            result.addFirst(currentNode.val);
            if (currentNode.children != null) {
                stack.addAll(currentNode.children);
            }
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
