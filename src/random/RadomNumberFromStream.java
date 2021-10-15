package random;

import java.util.Random;

public class RadomNumberFromStream {
    public static void main(String[] args) {
        int stream[] = {1, 2, 3, 4};
        int n = stream.length;
        for (int i = 0; i < n; i++)
            System.out.println("selectRandom(" + (i + 1) + "): " + selectRandom(stream[i]));
    }

    static int res = 0;    // The resultant random number
    static int count = 0;  //Count of numbers visited so far in stream

    // reservoir sampling
    //A method to randomly select a item from stream[0], stream[1], .. stream[i-1]
    // time: O(1)
    // space: O(1)
    private static int selectRandom(int x) {
        count++; // increment count of numbers seen so far

        // If this is the first element from stream, return it
        if (count == 1) {
            res = x;
        } else {
            // Generate a random number from 0 to count - 1
            Random r = new Random();
            int i = r.nextInt(count);

            // Replace the prev random number with new number with 1/count probability
            if (i == count - 1)
                res = x;
        }
        return res;
    }
}
