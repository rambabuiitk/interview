package narytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class SerializeDeserializeNaryTree {
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
        String serializedString = serialize(root);
        System.out.println("Serialized: " + serializedString);
        System.out.println("--");
        TreeNode newRoot = deserialize(serializedString);
        System.out.println("Deserialize: " + newRoot.val);
        System.out.println("--");
        serializedString = serialize(newRoot);
        System.out.println("Serialized: " + serializedString);
        System.out.println("--");

    }

    // serialize using preorder traversal with stack and also add number of children for current node in order
    private static String serialize(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return "";
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.pop();
            result.add(String.valueOf(currentNode.val)); // adding current node to the result.
            if (currentNode.children != null) {
                // also adding children size to the result after curentnode
                result.add(String.valueOf(currentNode.children.size()));
                Collections.reverse(currentNode.children); // were are reversing the children order in place
                stack.addAll(currentNode.children);
            } else { // no children for current node so add size 0 as list of children
                result.add(String.valueOf(0));
            }
        }
        // will return "" for empty arraylist
        return String.join(",", result);
    }

    private static TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        String[] values = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(values));
        return deserializeHelper(queue);
    }

    // we recursively deserialize each node and its children by picking each node and numOfChildren at a time
    private static TreeNode deserializeHelper(Queue<String> queue) {
        int rootValue = Integer.parseInt(queue.poll()); // first value is the root node
        TreeNode root = new TreeNode(rootValue);
        int numOfChildren = Integer.parseInt(queue.poll()); // all values are followed by num of children
        root.children = new ArrayList<TreeNode>(numOfChildren);
        for (int i = 0; i < numOfChildren; i++) {
            // call recursive function for each children
            root.children.add(deserializeHelper(queue));
        }
        return root;
    }

    private static class TreeNode {
        public int val;
        public List<TreeNode> children;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
