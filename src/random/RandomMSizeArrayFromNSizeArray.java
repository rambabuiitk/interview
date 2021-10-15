package random;

import java.util.Arrays;

public class RandomMSizeArrayFromNSizeArray {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        System.out.print("Array : " + Arrays.toString(arr));
        int m = 4;
        System.out.println("M : " + m);
        int[] output = getRandomM(arr, m);
        System.out.print("Array : " + Arrays.toString(output));
    }

    // Reservoir Sampling
    // time: O(N)
    private static int[] getRandomM(int[] original, int m) {
        int[] subset = new int[m];

        // Fill the subset array with first part of the original array
        for (int i = 0; i < m; i++) {
            subset[i] = original[i];
        }

        /*Go through the remaining original array pick random from (0,i)
         * and if random int k is less than m than replace subset[k] with original[i]
         */
        for (int i = m; i < original.length; i++) {
            int k = randomWithRange(0, i);
            if (k < m) {
                subset[k] = original[i];
            }
        }
        return subset;
    }

    //returns an integer with a user-defined range.
    private static int randomWithRange(int min, int max) {
        double rand = Math.random(); // generating random number
        int range = (max - min) + 1;
        return min + (int) (rand * range);
    }

}
