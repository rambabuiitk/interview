import java.util.Arrays;

public class MergeSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;
        System.out.println("nums1: " + Arrays.toString(nums1) + " m: " + m);
        System.out.println("nums2: " + Arrays.toString(nums2) + " n: " + n);
        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    // time: O(N) where N is max of (length of num1, length of num2)
    // space: O(1) here we are using already existed space of num1
    private static void merge(int[] nums1, int m, int[] nums2, int n) {
        // two get pointers for nums1 and nums2 and start from end
        int end1 = m - 1;
        int end2 = n - 1;
        int end = m + n - 1;
        while (end1 >= 0 && end2 >= 0) {
            if (nums1[end1] > nums2[end2]) {
                nums1[end] = nums1[end1];
                end1--;
            } else {
                nums1[end] = nums2[end2];
                end2--;
            }
            end--;
        }
        // copy from nums2 to nums1 till end2+1 length
        // arraycopy(src, src_start, dest, dest_start, length)
        System.arraycopy(nums2, 0, nums1, 0, end2 + 1);
    }
}
