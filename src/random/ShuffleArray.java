package random;

import java.util.Arrays;
import java.util.Random;

public class ShuffleArray {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println("Input: " + Arrays.toString(nums));
        ShuffleArray sa = new ShuffleArray(nums);
        int[] newNums = sa.shuffle();
        System.out.println("Shuffled: " + Arrays.toString(newNums));
        int[] resetNums = sa.reset();
        System.out.println("Reset: " + Arrays.toString(resetNums));
    }

    int[] array;
    int[] original;
    Random rand = new Random();

    ShuffleArray(int[] nums) {
        array = nums;
        original = nums.clone();
    }
    // time: O(N)
    // fisher yates algorithm
    private int[] shuffle() {
        for (int i = 0; i < array.length; i++) {
            int rand = randRange(i, array.length);
            swap(i, rand);
        }
        return array;
    }

    private int[] reset() {
        // resetting the array from original
        array = original;
        // resetting the original
        original = original.clone();
        return original;
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private int randRange(int min, int max) {
        return rand.nextInt(max - min) + min;
    }
}
