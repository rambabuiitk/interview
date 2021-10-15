package BinarySearch;

import java.util.Arrays;

public class KthSmallestInSortedMatrixUsingBinarySearch {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
            {1, 5, 9}, 
            {10, 11, 13}, 
            {12, 13, 15}
        };
        int k = 8;
        System.out.println("Input: " + Arrays.deepToString(matrix));
        System.out.println("k: " + k);
        int result = findKthSmallest(matrix, k);
        System.out.print("Output: " + result);
    }

    private static int findKthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int start = matrix[0][0]; // first element of matrix
        int end = matrix[n - 1][n - 1]; // last element of matrix
        while (start < end) {
            int mid = start + (end - start) / 2;
            // first number is the smallest and the second number is the largest
            int[] smallLargePair = {matrix[0][0], matrix[n - 1][n - 1]};
            // Count all the numbers smaller than or equal to middle in the matrix.
            int count = countLessEqual(matrix, mid, smallLargePair);

            if (count == k) {
                return smallLargePair[0];
            }

            if (count < k) {
                start = smallLargePair[1]; // search higher
            } else {
                end = smallLargePair[0]; // search lower
            }
        }

        return start;
    }

    // Count all the numbers smaller than or equal to middle in the matrix.
    private static int countLessEqual(int[][] matrix, int mid, int[] smallLargePair) {
        int count = 0;
        int n = matrix.length;
        int row = n - 1;
        int col = 0;
        while (row >= 0 && col < n) {
            if (matrix[row][col] > mid) {
                // as matrix[row][col] is bigger than the mid, let's keep track of the
                // smallest number greater than the mid
                smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col]);
                row--;
            } else {
                // as matrix[row][col] is less than or equal to the mid, let's keep track of the
                // biggest number less than or equal to the mid
                smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col]);
                count += row + 1;
                col++;
            }
        }
        return count;
    }

}
