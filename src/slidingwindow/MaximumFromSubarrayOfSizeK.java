package slidingwindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MaximumFromSubarrayOfSizeK {

    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 3, 2, 2, 1, 2, 3};
        int K = 3;
        System.out.println("Input: " + Arrays.toString(input) + " K: " + K);
        int[] output = maxUsingSlidingWindow(input, K);
        System.out.println("Using Sliding Window: " + Arrays.toString(output));
        output = maxUsingQueue(input, K);
        System.out.println("Using Queue: " + Arrays.toString(output));
        System.out.println("---");
    }

    // complexity : O(N*k)
    private static int[] maxUsingSlidingWindow(int[] nums, int k) {
        int windowStart = 0;
        int[] output = new int[nums.length - k + 1];
        for (int windowEnd = 0; windowEnd < nums.length - k + 1; windowEnd++) {
            int windowMax = nums[windowStart];
            for (int j = 0; j < k; j++) {
                windowMax = Math.max(windowMax, nums[windowStart + j]);
            }
            output[windowStart] = windowMax;
            windowStart++;
        }
        return output;
    }

    // complexity : O(N)
    private static int[] maxUsingQueue(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] output = new int[n - k + 1];
        // store index
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            // remove numbers out of range k
            // we are storing the index in the queue and so if index reaches the windowlimit we poll the element
            while (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }
            // remove smaller numbers in k range as they are useless
            // peekLast is the recent element added in queue
            // if the element[queue last index] is smaller than remove from the queue
            while (!q.isEmpty() && nums[q.peekLast()] <= nums[i]) {
                q.pollLast();
            }
            // q contains index... output contains content
            q.offer(i);
            if (i >= k - 1) {
                output[i - k + 1] = nums[q.peek()];
            }
        }
        return output;
    }
}
