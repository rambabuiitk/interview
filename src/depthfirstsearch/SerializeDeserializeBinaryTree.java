package depthfirstsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SerializeDeserializeBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Input: [12, 7, 1, 9, null, 10, 5]");
        String output = serialize(root);
        System.out.println("Serialize: " + output);
        System.out.println("--");
        TreeNode newRoot = deserialize(output);
        System.out.println("Deserialize: " + newRoot.val);
        System.out.println("--");
        output = serialize(newRoot);
        System.out.println("Serialize: " + output);
    }

    // here we are doing a preorder traversal adding "NULL" string for nodes that are not there
    private static String serialize(TreeNode root) {
        List<String> output = new ArrayList<>();
        serializeHelper(root, output);
        return String.join(",", output);
    }

    // preorder traversal of the input tree
    // we can also do stringbuilder instead of list but just for clean code doing List and later converting to string
    private static void serializeHelper(TreeNode node, List<String> output) {
        if (node == null) {
            output.add("NULL");
        } else {
            output.add(String.valueOf(node.val));
            serializeHelper(node.left, output);
            serializeHelper(node.right, output);
        }
    }

    // we recursively deserialize each node and its left and right by picking each node at a time
    private static TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        String[] values = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(values));
        return deserializeHelper(queue);
    }

    private static TreeNode deserializeHelper(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        String currentNodeValue = queue.poll();
        if (currentNodeValue.equals("NULL")) {
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.valueOf(currentNodeValue));
            node.left = deserializeHelper(queue);
            node.right = deserializeHelper(queue);
            return node;
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
