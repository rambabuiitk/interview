import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllMissingNumbers {

    public static void main(String[] args) {
        int[] input = new int[]{2, 3, 1, 8, 2, 3, 5, 1};
        System.out.println("Input: " + Arrays.toString(input));
        List<Integer> output = missingNumbers(input);
        System.out.println("Output: " + output);
    }

    private static List<Integer> missingNumbers(final int[] arr) {
        int i = 0;
        // iterate till end of array.
        while (i < arr.length) {
            // validate if element is smaller then last index
            // if element value 2 is not at element at index 1 then we swap to make it at index 2
            // reason we compare i with arr[i] - 1 is because input start from 1
            if (arr[i] != arr[arr[i] - 1]) {
                swap(arr, i, arr[i] - 1); // swap index i with arr[i] - 1
            } else { // element is at correct place move to next element
                i++;
            }
        }

        List<Integer> missingNumbers = new ArrayList<>();
        // sorted all the elements validate elements
        // for example element with value 1 should be at
        for (i = 0; i < arr.length; i++) {
            // element at arr[0] should be equal to value 0+1 (i.e)
            // if not then element with value 1 is missing in array
            if (arr[i] != i + 1) {
                missingNumbers.add(i + 1);
            }
        }
        return missingNumbers;
    }

    private static void swap(final int[] arr, final int i, final int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
