import java.util.Arrays;

public class SubsequenceWithMinMaxLessThanTarget {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 5, 6, 7};
        int target = 9;
        System.out.println("Input: " + Arrays.toString(nums));
        System.out.println("Target: " + target);
        int output = numSubseq(nums, target);
        System.out.println("Total Subsequence: " + output);
    }

    // time: O(NlogN)
    // space: O(N)
    // (sort + twoSum using two pointer)
    private static int numSubseq(int[] nums, int target) {
        //sort the array
        Arrays.sort(nums);

        int totalCount = 0;
        int n = nums.length;
        int mod = (int) 1e9 + 7;

        //record all 2 ^ n to save time
        int[] pows = new int[n];
        pows[0] = 1;
        for (int i = 1; i < n; i++) {
            pows[i] = (pows[i - 1] * 2) % mod;
        }
        System.out.println(Arrays.toString(pows));

        int start = 0;
        int end = n - 1;
        while (start <= end) {
            if (nums[start] + nums[end] > target) {
                end--;
            } else {
                /* For each elements in the subarray A[start] to A[end],
                    we can pick or not pick,
                    so there are 2 ^ (end - start) subsequences in total.
                    which is pos[end - start]
                    so ==> 2 ^ (start - end) = pos[start - end]
                */
                totalCount = (totalCount + pows[end - start]) % mod;
                start++;
            }
        }
        return totalCount;
    }

}
