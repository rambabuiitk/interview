package random;

import java.util.Random;

 /**
 *  Example: w = [1, 3],
 *  the probability of picking the index 0 is 1 / (1 + 3) = 0.25 (i.e 25%)
 *  the probability of picking the index 1 is 3 / (1 + 3) = 0.75 (i.e 75%)
 */
public class RandomPickWeight {
    public static void main(String[] args) {
        int[] w = new int[]{1, 3};
        RandomPickWeight rw = new RandomPickWeight(w);
        // since this is random function answers wil change every time
        System.out.println("pickIndex(): " + rw.pickIndex());
        System.out.println("pickIndex(): " + rw.pickIndex());
        System.out.println("pickIndex(): " + rw.pickIndex());
        System.out.println("pickIndex(): " + rw.pickIndex());
        System.out.println("pickIndex(): " + rw.pickIndex());
        System.out.println("pickIndex(): " + rw.pickIndex());
    }

    // left sum array for [1, 2, 3, 4] is [1, 3, 6, 10]
    // for our example [1 ,3] is [1, 4]
    private int[] prefixSum;
    Random random;

    public RandomPickWeight(int[] w) {
        random = new Random();
        // the reason we choose prefixSum is majorty
        // of the time random number will fall will be based on prefixSum
        prefixSum = new int[w.length];
        int prefixSum = 0;
        for (int i = 0; i < w.length; ++i) {
            prefixSum = prefixSum + w[i];
            this.prefixSum[i] = prefixSum;
        }
    }

    public int pickIndex() {
        int len = prefixSum.length;
        // random.nextInt(N) is randomly pick integer from 0(included) to N (excluded)
        // prefixSum = [1,4]
        // random.nextInt(4) --> pick randomly from 0 to 3
        // (random.nextInt(4) + 1) --> target will be from 1 to 4
        int target = random.nextInt(prefixSum[len - 1]) + 1;

        // run a binary search to find the target zone
        int start = 0;
        int end = len;
        while (start < end) {
            // better to avoid the overflow
            int mid = start + (end - start) / 2;
            if (target > prefixSum[mid])
                start = mid + 1;
            else
                end = mid;
        }
        // here we are returning the index
        // if asked return number we can do w[start]
        return start;
    }
}
