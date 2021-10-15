import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Combination {

    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        System.out.println("n: " + n);
        System.out.println("k: " + k);
        List<List<Integer>> combination = combine(n, k);
        System.out.println("Output: " + combination);
    }

    private static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList(), n, k, 1);
        return res;
    }

    private static void backtrack(List<List<Integer>> res, List<Integer> tempList, int n, int k, int num) {
        if (tempList.size() == k) {
            res.add(new ArrayList(tempList));
            return;
        }
        for (int i = num; i <= n; i++) {
            tempList.add(i);
            backtrack(res, tempList, n, k, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
