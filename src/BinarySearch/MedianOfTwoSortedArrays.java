package BinarySearch;

import java.util.Arrays;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3, 5};
        int[] nums2 = new int[]{2, 4};
        System.out.println("nums1: " + Arrays.toString(nums1));
        System.out.println("nums2: " + Arrays.toString(nums2));
        double median = findMedianSortedArrays(nums1, nums2);
        System.out.println("Output: " + median);
    }

    // doing a binary search
    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {        
        int size1 = nums1.length;
        int size2 = nums2.length;
        // keep nums1 always smaller than nums2
        if (size1 > size2) {
            return findMedianSortedArrays(nums2, nums1); // make sure nums1 is always smaller.
        }
        int start = 0;
        int end = size1; // here size
        while (start <= end) {
            int mid1 = start + (end - start) / 2;
            int mid2 = (size1 + size2 + 1) / 2 - mid1;
            //if mid1 is 0 it means nothing is there on left side. Use INTEGER.MIN for maxLeft1
            int maxLeft1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[mid1 - 1];
            //if mid1 is length of input then there is nothing on right side. Use INTEGER.MAX for minRight1
            int minRight1 = (mid1 == size1) ? Integer.MAX_VALUE : nums1[mid1];
            // same for mid2
            //if mid2 is 0 it means nothing is there on left side. Use INTEGER.MIN for maxLeft2
            int maxLeft2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[mid2 - 1];
            //if mid2 is length of input then there is nothing on right side. Use INTEGER.MAX for minRight2
            int minRight2 = (mid2 == size2) ? Integer.MAX_VALUE : nums2[mid2];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // we have partitioned the array at correct place.
                // Now get max of left elements and min of right elements to get the median if combined total array is even
                // or get max of left if combined total array size is odd
                if ((size1 + size2) % 2 == 0) { // if combined size is even
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2;
                } else {
                    return Math.max(maxLeft1, maxLeft2);
                }
            } else if (maxLeft1 > minRight2) { //we are too far on right side for partitionX. Go on left side.
                end = mid1 - 1;
            } else { //we are too far on left side for partitionX. Go on right side.
                start = mid1 + 1;
            }
        }
        throw new IllegalArgumentException(); // can only land here if input arrays are not sorted
    }
}
