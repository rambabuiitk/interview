package mergeintervals;

import java.util.Arrays;

public class MaximumOverlappingIntervals {


    public static void main(String[] args) {
        int[] start = new int[]{1, 2, 10, 5, 5};
        int[] end = new int[]{4, 5, 12, 9, 12};
        System.out.println("Start: " + Arrays.toString(start));
        System.out.println("End: " + Arrays.toString(end));
        int[] maxOverlap = findMaxOverlap(start, end);
        System.out.println("Max Interval Value: " + maxOverlap[0]);
        System.out.println("Max Interval Start Time: " + maxOverlap[1]);
    }

    private static int[] findMaxOverlap(final int[] start,
                                        final int[] end) {
        if (start.length != end.length) {
            return new int[]{-1, -1};
        }
        int maxOverlap = 0;
        int currentOverlap = 0;

        Arrays.sort(start);
        Arrays.sort(end);

        int i = 0;
        int j = 0;
        int m = start.length;
        int time = -1;
        while (i < m && j < m) {
            // We know that a new range begins.
            // So increment the current counter and update value of max counter.
            if (start[i] <= end[j]) {
                currentOverlap++;
                if (currentOverlap > maxOverlap) {
                    maxOverlap = currentOverlap;
                    time = start[i];
                }
                i++;
            } else {
                // It’s an end point of a range so we decrement the counter.
                currentOverlap--;
                j++;
            }
        }

        return new int[]{maxOverlap, time};
    }
}
