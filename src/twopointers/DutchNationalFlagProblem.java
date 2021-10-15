import java.util.Arrays;

public class DutchNationalFlagProblem {

    public static void main(String[] args) {
        int[] input = new int[] { 1, 0, 2, 1, 0 };
        System.out.println("Input: " + Arrays.toString(input));
        DutchNationalFlagProblem.sort(input);
        System.out.println("Output: " + Arrays.toString(input));
    }

    private static void sort(final int[] arr) {
        int low= 0;
        int high = arr.length - 1;

        for (int i = 0; i <= high; ) {
            if (arr[i] == 0) {
                swap(arr, i, low);
                i++;
                low++;
            } else if (arr[i] == 1) {
                i++;
            } else {
                swap(arr, i, high);
                high--;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}