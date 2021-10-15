package DesignAlgoJava;

import java.util.Stack;

public class BinarySearchTreeIterator {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);
        System.out.println("Input: [7, 3, 15, null, null, 9, 20]");
        BSTIterator iter = new BSTIterator(root);
        System.out.println("iter.next() : " + iter.next());
        System.out.println("iter.next() : " + iter.next());
        System.out.println("iter.hasNext() : " + iter.hasNext());
        System.out.println("iter.next() : " + iter.next());
        System.out.println("iter.hasNext() : " + iter.hasNext());
        System.out.println("iter.next() : " + iter.next());
        System.out.println("iter.hasNext() : " + iter.hasNext());
        System.out.println("iter.next() : " + iter.next());
        System.out.println("iter.hasNext() : " + iter.hasNext());
    }

    private static class BSTIterator {
        private Stack<TreeNode> stack = new Stack<TreeNode>();

        public BSTIterator(TreeNode root) {
            pushAllLeft(root);
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            TreeNode nextNode = stack.pop();
            pushAllLeft(nextNode.right);
            return nextNode.val;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        private void pushAllLeft(TreeNode currNode) {
            while (currNode != null) {
                stack.push(currNode);
                currNode = currNode.left;
            }
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
