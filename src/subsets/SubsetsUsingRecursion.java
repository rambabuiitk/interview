package subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsUsingRecursion {

    public static void main(String[] args) {
        // Input Array will be distinct integers here.
        int[] input = new int[]{1, 2, 3};
        System.out.println("Input: " + Arrays.toString(input));
        List<List<Integer>> output = findSubsetsUsingRecursion(input);
        System.out.println("Output: " + output);
    }

    // Find Subsets Recursively
    private static List<List<Integer>> findSubsetsUsingRecursion(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        findSubsetsRecursion(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private static void findSubsetsRecursion(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
        result.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            findSubsetsRecursion(result, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

}
