import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public static void main(String[] args) {
        int[] input = new int[]{2, 2, 4};
        int target = 4;
        System.out.println("input: " + Arrays.toString(input));
        System.out.println("target: " + target);
        System.out.println("Output: " + combinationSum(input, target));
    }
    // O(N ^ (T/M))
    // Let N be the number of candidates, T be the target value, and MM be the minimal value among the candidates.
    private static List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0) {
            return;
        } else if (remain == 0) { // if remainder is 0 meaning we found the list
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = start; i < nums.length; i++) {
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
