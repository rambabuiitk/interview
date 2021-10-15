import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class PermutationsOfArrayUsingRecursion {

    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3};
        System.out.println("Input: " + Arrays.toString(input));
        List<List<Integer>> result = findPermutationsUsingRecursion(input);
        System.out.println("Output: " + result);
    }

    // Permutation using Recursion
    private static List<List<Integer>> findPermutationsUsingRecursion(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generatePermutationsRecursive(result, new ArrayList<>(), new HashSet<>(), nums);
        return result;
    }

        private static void generatePermutationsRecursive(List<List<Integer>> result,
                                                      List<Integer> tempList,
                                                      Set<Integer> tempSet,
                                                      int[] nums) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tempSet.contains(nums[i])) {  // element already exists, skip
                    continue;
                }
                tempSet.add(nums[i]);
                tempList.add(nums[i]);
                generatePermutationsRecursive(result, tempList, tempSet, nums);
                tempSet.remove(tempList.get(tempList.size() - 1));
                tempList.remove(tempList.size() - 1);
            }
        }
    }

}
