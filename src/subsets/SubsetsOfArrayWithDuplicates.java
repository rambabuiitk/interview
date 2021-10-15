import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsOfArrayWithDuplicates {

    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 2};
        System.out.println("Input: " + Arrays.toString(input));
        List<List<Integer>> output = findSubsets(input);
        System.out.println("Output: " + output);
    }

    private static List<List<Integer>> findSubsets(final int[] nums) {
        // sort the numbers to handle duplicates
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        int startIndex;
        int endIndex = 0;
        for(int i = 0 ; i < nums.length; i++) {
            // for every new number from input array reset the startIndex
            startIndex = 0;

            if((i > 0) && nums[i] == nums[i-1]) {
                // every time if there is no match we keep track of endIndex
                // if there is a duplicate we move startIndex to first index of previous subset which is endIndex+1
                startIndex = endIndex + 1;
            }
            endIndex = result.size() - 1;
            for(int j = startIndex; j <= endIndex; j++) {
                // create a new subset from the existing subset and add the current element to it
                List<Integer> tempList = new ArrayList<>(result.get(j));
                tempList.add(nums[i]);
                result.add(tempList);
            }
        }
        return result;
    }

}
