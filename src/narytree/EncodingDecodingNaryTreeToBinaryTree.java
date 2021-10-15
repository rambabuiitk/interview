package narytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodingDecodingNaryTreeToBinaryTree {

    public static void main(String[] args) {
        NTreeNode root = new NTreeNode(1);
        NTreeNode three = new NTreeNode(3);
        NTreeNode two = new NTreeNode(2);
        NTreeNode four = new NTreeNode(4);
        NTreeNode five = new NTreeNode(5);
        NTreeNode six = new NTreeNode(6);
        root.children = new ArrayList<>(Arrays.asList(three, two, four));
        three.children = new ArrayList<>(Arrays.asList(five, six));
        System.out.println("Input: [1, null, 3, 2, 4, null, 5, 6]");
        BTreeNode binaryTreeRoot = encode(root);
        System.out.println("Encoded: " + binaryTreeRoot.val);
        System.out.println("--");
        NTreeNode nTreeRoot = decode(binaryTreeRoot);
        System.out.println("Decoded: " + nTreeRoot.val);
    }

    // Encodes an n-ary tree to a binary tree.
    private static BTreeNode encode(NTreeNode root) {
        if (root == null) {
            return null;
        }
        BTreeNode newRoot = new BTreeNode(root.val);
        // Encode the first child of n-ary node to the left node of binary tree.
        if (root.children != null && root.children.size() > 0) {
            NTreeNode firstChild = root.children.get(0);
            newRoot.left = encode(firstChild);
        }
        // Encoding the rest of the sibling nodes.
        BTreeNode sibling = newRoot.left;
        if (root.children != null) {
            for (int i = 1; i < root.children.size(); i++) {
                sibling.right = encode(root.children.get(i));
                sibling = sibling.right;
            }
        }
        return newRoot;
    }

    // Decodes your binary tree to an n-ary tree.
    public static NTreeNode decode(BTreeNode root) {
        if (root == null) {
            return null;
        }
        NTreeNode newRoot = new NTreeNode(root.val);
        newRoot.children = new ArrayList<>();
        // Decoding all the children nodes
        BTreeNode sibling = root.left;
        while (sibling != null) {
            newRoot.children.add(decode(sibling));
            sibling = sibling.right;
        }

        return newRoot;
    }

    private static class NTreeNode {
        public int val;
        public List<NTreeNode> children;

        public NTreeNode(int val) {
            this.val = val;
        }
    }

    private static class BTreeNode {
        public int val;
        BTreeNode left;
        BTreeNode right;

        BTreeNode(int val) {
            this.val = val;
        }
    }
}
