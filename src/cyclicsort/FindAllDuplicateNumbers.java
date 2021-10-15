import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllDuplicateNumbers {
    public static void main(String[] args) {
        int[] input = new int[]{3, 4, 4, 5, 5};
        System.out.println("Input: " + Arrays.toString(input));
        List<Integer> duplicates = findAllDuplicates(input);
        System.out.println("Output: " + duplicates);
    }

    private static List<Integer> findAllDuplicates(final int[] arr) {
        int i = 0;
        while (i < arr.length) {
            if (arr[i] != arr[arr[i] - 1]) {
                swap(arr, i, arr[i] - 1);
            } else {
                i++;
            }
        }

        List<Integer> duplicateNumbers = new ArrayList<>();
        for (i = 0; i < arr.length; i++) {
            if ( arr[i] != i + 1) // if the element does not match meaning its duplicated
                // we can do more thorough check here of having a set and all
                duplicateNumbers.add(arr[i]);
        }
        return duplicateNumbers;
    }

    private static void swap(final int[] arr, final int i, final int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
