package disjointset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class AccountsMerge {
    public static void main(String[] args) {
        List<String> acc1 = new ArrayList(Arrays.asList("John", "A", "B"));
        List<String> acc2 = new ArrayList(Arrays.asList("John", "C"));
        List<String> acc3 = new ArrayList(Arrays.asList("John", "A", "D"));
        List<String> acc4 = new ArrayList(Arrays.asList("Mary", "E"));
        List<List<String>> acts = new ArrayList<>();
        acts.add(acc1);
        acts.add(acc2);
        acts.add(acc3);
        acts.add(acc4);
        System.out.println("Accounts: " + acts);

        List<List<String>> output = accountsMerge(acts);
        System.out.println("Output: " + output);
    }

    // union-find
    // time: O(A log A) where A is total accounts
    // space:O(A)
    private static List<List<String>> accountsMerge(List<List<String>> acts) {
        Map<String, String> emailToOwnerMap = new HashMap<>();

        // Initializing the childToParent map with child itself
        for (List<String> accounts : acts) {
            for (int i = 1; i < accounts.size(); i++) {
                emailToOwnerMap.put(accounts.get(i), accounts.get(0)); // 0th index is owner
            }
        }

        Graph graph = new Graph(acts);
        for (List<String> account : acts) {
            // for each account union 1st entry with all the entries in the account
            for (int i = 2; i < account.size(); i++) {
                graph.union(account.get(1), account.get(i));
            }
        }

        Map<String, Set<String>> parentToChildMap = new HashMap<>();

        for (List<String> accounts : acts) {
            // find the parent of 1st entry
            String parent = graph.find(accounts.get(1));
            if (!parentToChildMap.containsKey(parent)) {
                parentToChildMap.put(parent, new TreeSet<>());
            }
            // parent of 1st entry will be same for all entries in same account
            // so add all entries to same found parent
            for (int i = 1; i < accounts.size(); i++)
                parentToChildMap.get(parent).add(accounts.get(i));
        }

        // convert parentToChildMap into List<List<String>>
        List<List<String>> result = new ArrayList<>();
        for (String children : parentToChildMap.keySet()) {
            List<String> emails = new ArrayList(parentToChildMap.get(children));
            emails.add(0, emailToOwnerMap.get(children));
            result.add(emails);
        }
        return result;

    }

    private static class Graph {
        Map<String, String> childToParentMap;

        public Graph(List<List<String>> allAccounts) {
            childToParentMap = new HashMap<>();
            // Initializing the childToParent map with child itself
            for (List<String> acounts : allAccounts) {
                for (int i = 1; i < acounts.size(); i++) {
                    childToParentMap.put(acounts.get(i), acounts.get(i)); // map initially parent same as child
                }
            }
        }

        public String find(String child) {
            // if the parent of current vertex is not same meaning its parent is set
            if (childToParentMap.get(child) != child) {
                // recursively find its root parent and update that as parent of current child
                String parent = find(childToParentMap.get(child));
                childToParentMap.put(child, parent);
            }
            return childToParentMap.get(child);
        }

        public void union(String a, String b) {
            String aRoot = find(a); // find root parent of a
            String bRoot = find(b); // find root parent of b
            // update parent of aRoot as bRoot
            // parent(aRoot) = bRoot
            childToParentMap.put(aRoot, bRoot);
        }
    }
}
