import java.util.Arrays;

public class SearchInMountainArray {
    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 4, 5, 3, 1};
        int target = 3;
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("target: " + target);
        int output = search(input, target);
        System.out.println("Output: " + output);
    }

    private static int search(int[] arr, int key) {
        // get the peak index in bitonic array.
        int maxIndex = findPeak(arr);
        // find the key in the increasing array.
        int keyIndex = binarySearch(arr, key, 0, maxIndex);
        // found the key in the increasing array.
        if (keyIndex != -1) {
            return keyIndex;
        }
        // search in decreasing array
        return binarySearch(arr, key, maxIndex + 1, arr.length - 1);
    }

    // find index of the maximum value in a bitonic array
    private static int findPeak(int[] arr) {
        int start = 0, end = arr.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] > arr[mid + 1]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        // at the end of the while loop, 'start == end'
        return start;
    }

    // order-agnostic binary search
    private static int binarySearch(int[] arr, int key, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (key == arr[mid]) {
                return mid;
            }

            if (arr[start] < arr[end]) { // ascending order
                if (key < arr[mid]) {
                    end = mid - 1;
                } else { // key > arr[mid]
                    start = mid + 1;
                }
            } else { // descending order
                if (key > arr[mid]) {
                    end = mid - 1;
                } else { // key < arr[mid]
                    start = mid + 1;
                }
            }
        }
        return -1; // element is not found
    }
}
