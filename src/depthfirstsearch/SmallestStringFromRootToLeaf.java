package depthfirstsearch;

public class SmallestStringFromRootToLeaf {
    public static void main(String[] args) {
        TreeNode root = new TreeNode('z');
        root.left = new TreeNode('b');
        root.right = new TreeNode('d');
        root.left.left = new TreeNode('b');
        root.left.right = new TreeNode('d');
        root.right.left = new TreeNode('a');
        root.right.right = new TreeNode('c');
        System.out.println("Input: [z, b, d, b, d, a, c]");
        String output = smallestFromLeaf(root);
        System.out.println("Smallest String Lexicographically: " + output);
    }

    private static String ans = "~";

    private static String smallestFromLeaf(TreeNode root) {
        dfs(root, new StringBuilder());
        return ans;
    }

    private static void dfs(TreeNode node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.val);
        // if we reach to the leaf -- reverse the string which is generated from root to leaf
        //
        if (node.left == null && node.right == null) {
            sb.reverse(); // reverse the generated string
            String reversed = sb.toString();
            sb.reverse(); // make it back to orginal

            if (reversed.compareTo(ans) < 0)
                ans = reversed;
        }

        dfs(node.left, sb);
        dfs(node.right, sb);
        // remove the current node from the path to backtrack,
        // we need to remove the current node while we are going up the recursive call stack.
        sb.deleteCharAt(sb.length() - 1);
    }

    private static class TreeNode {
        char val;
        TreeNode left;
        TreeNode right;

        TreeNode(char x) {
            val = x;
        }
    }
}
