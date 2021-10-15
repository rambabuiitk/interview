package topkelements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KClosestElementsForX {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 6, 8, 9, 10, 12};
        int k = 3;
        int x = 7;
        System.out.println("Input: " + Arrays.toString(arr));
        System.out.println("K: " + k);
        System.out.println("X: " + x);
        List<Integer> result = findKClosestElements(arr, k, x);
        System.out.println("Output: " + result);
        System.out.println("---");
        arr = new int[]{1, 3, 5, 6, 8, 9, 10, 12};
        k = 3;
        x = 7;
        System.out.println("Input: " + Arrays.toString(arr));
        System.out.println("K: " + k);
        System.out.println("X: " + x);
        result = findClosestElementsUsngTwoPonters(arr, k, x);
        System.out.println("Output: " + result);

    }


    // using binary search
    // time O(log(N - K)) and space O(K)
    private static List<Integer> findKClosestElements(int[] arr, int k, int x) {
        int start = 0;
        int end = arr.length - k;
        while (start < end) {
            int mid = (start + end) / 2;
            if (x - arr[mid] > arr[mid+k] - x)
                start = mid + 1;
            else
                end = mid;
        }
        return Arrays.stream(arr, start, start + k).boxed().collect(Collectors.toList());
    }

    private static List<Integer> findClosestElementsUsngTwoPonters(final int[] arr, final int k, final int x) {
        int lowPointer = 0;
        int highPointer = arr.length - 1;
        while (highPointer - lowPointer >= k) {
            if (Math.abs(arr[lowPointer] - x) > Math.abs(arr[highPointer] - x)) {
                lowPointer++;
            } else {
                highPointer--;
            }
        }
        List<Integer> result = new ArrayList<>(k);
        for (int i = lowPointer; i <= highPointer; i++) {
            result.add(arr[i]);
        }
        return result;
    }

}
