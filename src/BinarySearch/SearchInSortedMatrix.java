package BinarySearch;

import java.util.Arrays;

public class SearchInSortedMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11},
                {2, 5, 8, 12},
                {3, 6, 9, 16}
        };

        System.out.println("Input: " + Arrays.deepToString(matrix));
        int target = 6;
        System.out.println("target: " + target);
        System.out.println("Output: " + search(matrix, target));
        System.out.println("--");
        System.out.println("Output: " + binarySearchMatrix(matrix, target));
    }


    // time: O(M + N)
    // space: O (1)
    private static boolean search(int[][] matrix, int target) {
        int start = 0;
        int end = matrix[0].length - 1;  //set indexes for top right element
        while (start < matrix.length && end >= 0) {
            if (matrix[start][end] == target) { // found the element
                return true;
            }
            if (matrix[start][end] > target) {  // found the element
                end--;
            } else {
                start++;   //  if mat[start][end] < target
            }
        }
        return false; // element not found.
    }


    // Using Binary Search
    // time: O(log N!)
    private static boolean binarySearchMatrix(int[][] matrix, int target) {
        // an empty matrix obviously does not contain `target`
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        // find length of diagonal and iterate over matrix diagonals
        int shorterDim = Math.min(matrix.length, matrix[0].length);

        for (int i = 0; i < shorterDim; i++) {
            boolean verticalFound = binarySearch(matrix, target, i, true); // do search in same column
            boolean horizontalFound = binarySearch(matrix, target, i, false); // do search in same row
            if (verticalFound || horizontalFound) { // if found in col or row
                return true;
            }
        }
        return false;
    }

    private static boolean binarySearch(int[][] matrix, int target, int currentIndex, boolean vertical) {
        int start = currentIndex;
        int end = vertical ? matrix[0].length - 1 : matrix.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (vertical) { // searching in a column
                if (matrix[currentIndex][mid] < target) {
                    start = mid + 1;
                } else if (matrix[currentIndex][mid] > target) {
                    end = mid - 1;
                } else {
                    return true;
                }
            } else { // searching in a row
                if (matrix[mid][currentIndex] < target) {
                    start = mid + 1;
                } else if (matrix[mid][currentIndex] > target) {
                    end = mid - 1;
                } else {
                    return true;
                }
            }
        }

        return false;
    }


}
