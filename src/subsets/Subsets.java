package subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {
        // Input Array will be distinct integers here.
        int[] input = new int[]{1, 2, 3};
        List<List<Integer>> output = findSubsets(input);
        System.out.println("Output: " + output);
    }

    // Find Subsets Iteratively
    private static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // start by adding the empty subset
        result.add(new ArrayList<>());
        for (int currentNumber : nums) {
            // we will take all existing subsets and insert the current number in them to create new subsets
            int n = result.size();
            for (int i = 0; i < n; i++) {
                // create a new subset from the existing subset and insert the current element to it
                List<Integer> tempList = new ArrayList<>(result.get(i));
                tempList.add(currentNumber);
                result.add(tempList);
            }
        }
        return result;
    }
}
