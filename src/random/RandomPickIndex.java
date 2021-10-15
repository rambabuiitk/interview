package random;

import java.util.Random;

/**
 * Given an array of positive integers containing duplicates.
 * Write pick() which randomly returns index of given integer.
 * if there are multple index for same integer, if should return index in equal probability,
 * <p>
 * Example: w = [1, 2, 3, 3, 3],
 * pick(3) should return index 2, 3 or 4. Each should have equal probability,
 * pick(1) should return index 0 as that is the only index having 1.
 */
public class RandomPickIndex {
    public static void main(String[] args) {
        int[] w = new int[]{1, 2, 3, 3, 3};
        RandomPickIndex rw = new RandomPickIndex(w);
        // since this is random function answers wil change every time
        System.out.println("pick(3): " + rw.pick(3));
        System.out.println("pick(3): " + rw.pick(3));
        System.out.println("pick(1): " + rw.pick(1));
        System.out.println("pick(3): " + rw.pick(3));
    }

    private int[] nums;
    Random random;

    public RandomPickIndex(int[] arr) {
        random = new Random();
        nums = arr;
    }

    // This is called Reservoir Sampling solution
    // time: O(N) where N is length of array
    // space: O(1)
    public int pick(int target) {
        int index = -1;
        // duplicate count of target number
        // if target is 3 count meaning count will be 1, 2, and become 3 at the end
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            // if nums[i] is same as target
            if(nums[i] == target) {
                // increment the count of total candidates
                // available to be chosen uniformly at random  
                count = count + 1;
                // random.nextInt(N) is randomly pick integer
                // from 0(included) to N (excluded)
                // random.nextInt(3) --> pick randomly from 0(included) to 2(included)
                // everytime we are picking random number
                if (random.nextInt(count) == 0) {
                    index = i;
                }
            }
        }
        return index;
    }
}
