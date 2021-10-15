package breadthfirstsearch;

/**
 * All Nodes at k Distance from Target Node
 *
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 *
 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
 *
 * Input: [3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]
 * k = 2
 * target = 5
 * Output: [7, 4, 1]
 *
 * Explanation: All nodes 7, 4 and 1 are 2 edges apart from target node 5.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class NodesKDistanceApartFromTarget {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        System.out.println("Input: [[3,5,1,6,2,0,8,null,null,7,4]");
        int k = 2;
        System.out.println("k: " + k);
        TreeNode target = root.left;
        System.out.println("target: " + target.val);
        List<Integer> output = distanceK(root, target, k);
        System.out.println("Output: " + output);
    }

    //here can also use Map<TreeNode, TreeNode> to only store the child - parent mapping,
    // since parent-child mapping is inherent in the tree structure
    // time: O(N)
    private static List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<Integer>();
        Map<TreeNode, TreeNode> backMap = new HashMap<>();   // store all edges that trace node back to its parent
        // O(N)
        // Step1. Perform DFS to find the node
        TreeNode kNode = dfs(root, target.val, backMap);

        if (root == null || K < 0) {
            return res;
        }

        // Step2. Perform BFS on this node to find the closest leaf node.
        Queue<TreeNode> queue = new LinkedList<>(); // the queue used in BFS
        queue.add(kNode);
        Set<TreeNode> visited = new HashSet<>(); // store all visited nodes
        visited.add(kNode);
        int level = 0;
        while (!queue.isEmpty() && level < K) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode curr = queue.poll();
                visited.add(curr);
                // check on left side
                // set.add will return boolean if its there in set
                if (curr.left != null && visited.add(curr.left)) {
                    queue.add(curr.left);
                }

                // check on right side
                if (curr.right != null && visited.add(curr.right)) {
                    queue.add(curr.right);
                }

                // check on upper side
                // set.add will return boolean if its there in set
                if (backMap.containsKey(curr) && visited.add(backMap.get(curr))) {  // go alone the back edge
                    queue.add(backMap.get(curr));
                }
            }
            level++;
        }
        for (TreeNode n : queue) {
            res.add(n.val);
        }

        return res;
    }

    // Step1. Perform DFS on root in order to find the node whose val = k,
    // at the meantime use HashMap to keep record of all back edges from child to parent;
    private static TreeNode dfs(TreeNode root, int k, Map<TreeNode, TreeNode> backMap) {
        if (root.val == k) {
            return root;
        }
        if (root.left != null) {
            backMap.put(root.left, root);        // add back edge
            TreeNode left = dfs(root.left, k, backMap);
            if (left != null) {
                return left;
            }
        }
        if (root.right != null) {
            backMap.put(root.right, root);       // add back edge
            TreeNode right = dfs(root.right, k, backMap);
            if (right != null) {
                return right;
            }
        }
        return null;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}

/**
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
