import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 4, 6};
        int targetSum = 6;
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Target Sum: " + targetSum);
        int[] output = twoSumProblem(input, targetSum);
        System.out.println("Output : " + Arrays.toString(output));
        System.out.println("---");
        output = twoSumProblemUsingHashmap(input, targetSum);
        System.out.println("Output : " + Arrays.toString(output));
    }


    // Two Sum using Hashmap.
    // time: O(N)
    private static int[] twoSumProblemUsingHashmap(final int[] arr, final int targetSum) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < arr.length; i++) {
            // if the difference is already in hashmap means we already have a number to make the target sum.
            if (map.containsKey(arr[i])) {
                return new int[]{map.get(arr[i]), i};
            } else {
                // store in the map the difference and target index.
                map.put(targetSum - arr[i], i);
            }
        }
        return new int[]{-1, -1};
    }

    // Two Sum using Two Pointers.
    // O(N) wont work if input array is not sorted
    // as we are returning the index if we are return value it will work by sorting
    // if we sort overall complexity is O(NlogN)
    private static int[] twoSumProblem(final int[] arr, final int targetSum) {
        int left = 0;
        int right = arr.length - 1;
        // traverse till left is less than right index
        while (left < right) {
            int currentSum = arr[left] + arr[right];
            if (targetSum == currentSum) {
                return new int[]{left, right};
            }
            if (currentSum < targetSum) {
                left++; // we need a pair with a bigger sum
            } else {
                right--;  // we need a pair with a smaller sum
            }
        }
        return new int[]{-1, -1};
    }

}
