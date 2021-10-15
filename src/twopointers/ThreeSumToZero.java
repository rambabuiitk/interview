package twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSumToZero {
    public static void main(String[] args) {
        System.out.println(" Triplets for  { -3, 0, 1, 2, -1, 1, -2 } is" +
                ThreeSumToZero.searchTriplets(new int[] { -3, 0, 1, 2, -1, 1, -2 }));
    }

    private static List<List<Integer>> searchTriplets(final int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> triplets = new ArrayList<>();
        for(int i = 0; i < arr.length; i++) {
            if(i > 0 && arr[i] == arr[i-1]) {
                continue;
            }
            searchPairs(arr, -arr[i], i + 1, triplets);
        }
        return triplets;
    }

    private static void searchPairs(int[] arr, int targetSum, int left, List<List<Integer>> triplets) {
        int right = arr.length - 1;
        while(left < right) {
            int currentSum = arr[left] + arr[right];
            if(currentSum == targetSum) { // found the triplet
                triplets.add(Arrays.asList( -targetSum, arr[left], arr[right]));
                left++;
                right--;
                while(left < right && arr[left] == arr[left - 1]) {
                    left++;
                }
                while(left < right && arr[right] == arr[right + 1]) {
                    right--;
                }
            } else if (currentSum < targetSum) {
                left++;
            } else {
                right--;
            }
        }
    }
}