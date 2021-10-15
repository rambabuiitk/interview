import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PermutationsOfArrayWithDuplicates {

    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 1};
        System.out.println("Input: " + Arrays.toString(input));
        Arrays.sort(input);
        List<List<Integer>> result = findPermutationsUsingRecursion(input);
        System.out.println("Output: " + result);
    }

    // Permutation using Recursion
    private static List<List<Integer>> findPermutationsUsingRecursion(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generatePermutationsRecursive(result, new ArrayList<Integer>(), nums, new boolean[nums.length]);
        return result;
    }

    private static void generatePermutationsRecursive(List<List<Integer>> result,
                                                      List<Integer> tempList,
                                                      int[] nums,
                                                      boolean[] used) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }
                used[i] = true; // for its own permutation we can keep it true
                tempList.add(nums[i]);
                generatePermutationsRecursive(result, tempList, nums, used);
                used[i] = false; // for other permutation keeping it back to false
                // also reducing the templist and removing the current element
                tempList.remove(tempList.size() - 1);
            }
        }
    }

}
