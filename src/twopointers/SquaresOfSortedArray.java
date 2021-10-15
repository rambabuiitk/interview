import java.util.Arrays;

public class SquaresOfSortedArray {
    public static void main(String[] args) {
        int[] input = new int[]{-2, -1, 0, 2, 3};
        System.out.println("Input: " + Arrays.toString(input));
        int[] output = makeSquares(input);
        System.out.println("Output: " + Arrays.toString(output));
    }

    private static int[] makeSquares(final int[] arr) {
        // have 2 pointers on each end.
        // Whichever gives us higher square will be appended at the end of result array
        int n = arr.length;
        int[] result = new int[n];
        int highestSquareIdx = n - 1;
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int leftSquare = arr[left] * arr[left];
            int rightSquare = arr[right] * arr[right];
            if (leftSquare > rightSquare) {
                result[highestSquareIdx] = leftSquare;
                highestSquareIdx--;
                left++;
            } else {
                result[highestSquareIdx] = rightSquare;
                highestSquareIdx--;
                right--;
            }
        }
        return result;
    }
}