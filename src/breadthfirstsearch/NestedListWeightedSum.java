package breadthfirstsearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list — whose elements may also be integers or other lists.

The depth of an integer is the number of lists that it is inside of.

For example, the nested list [1,[4,[6]]]  has each integer’s value set to depth 1 for 1, 2 for 4 and 3 for 6.

Input: [[1,1],2,[1,1]]

Output: 10

Explanation: Four 1's at depth 2, one 2 at depth 1.

 */

public class NestedListWeightedSum {
    public static void main(String[] args) {
        List<NestedInteger> input = new ArrayList<>();

        List<NestedInteger> n1 = new ArrayList<>();
        n1.add(new NestedInteger(true, 1, null));
        n1.add(new NestedInteger(true, 1, null));
        input.add(new NestedInteger(false, null, n1));

        input.add(new NestedInteger(true, 2, null));

        n1 = new ArrayList<>();
        n1.add(new NestedInteger(true, 1, null));
        n1.add(new NestedInteger(true, 1, null));
        input.add(new NestedInteger(false, null, n1));

        System.out.println("Input: " + input);
        int output = nestedSumDFS(input);
        System.out.println("Output DFS: " + output);
        System.out.println("---");
        output = nestedSumBFS(input);
        System.out.println("Output BFS: " + output);

    }

    private static int nestedSumDFS(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    private static int dfs(List<NestedInteger> list, int depth) {
        int sum = 0;
        for (NestedInteger n : list) {
            if (n.isInteger) {
                sum = sum + (n.value * depth);
            } else {
                sum = sum + dfs(n.list, depth + 1);
            }
        }
        return sum;
    }

    // bfs traversal
    private static int nestedSumBFS(List<NestedInteger> nestedList) {
        if (nestedList == null) {
            return 0;
        }
        int sum = 0;
        int depth = 1;
        Queue<NestedInteger> queue = new LinkedList<NestedInteger>(nestedList);
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger ni = queue.poll();
                if (ni.isInteger) {
                    sum = sum + (ni.value * depth);
                } else {
                    queue.addAll(ni.list);
                }
            }
            depth++;
        }
        return sum;
    }


    private static class NestedInteger {
        public boolean isInteger;
        public Integer value;
        public List<NestedInteger> list;

        public NestedInteger(boolean isInteger, Integer value, List<NestedInteger> list) {
            this.isInteger = isInteger;
            this.value = value;
            this.list = list;
        }

        public String toString() {
            if (isInteger && value != null) {
                return value.toString();
            } else {
                return list.toString();
            }
        }
    }
}
