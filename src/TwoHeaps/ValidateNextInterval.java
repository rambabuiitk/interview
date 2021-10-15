package TwoHeaps;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an array of intervals, find the next interval of each interval. In a list of intervals, for an interval ‘i’ its next
 * interval ‘j’ will have the smallest ‘start’ greater than or equal to the ‘end’ of ‘i’.
 *
 * Write a function to return an array containing indices of the next interval of each input interval. If there is no next interval
 * of a given interval, return -1. It is given that none of the intervals have the same start point.
 *
 * Input:
 * [[2,3], [5,6], [3,4]]
 *
 * Output:
 * [2, -1, 1]
 *
 * Explanation:
 * The next interval of [2,3] is [3,4] having index ‘2’. Similarly, the next interval of [3,4] is [5,6] having index ‘1’. There is
 * no next interval for [5,6] hence we have ‘-1’.
 */

public class ValidateNextInterval {

    public static void main(String[] args) {
        Interval[] intervals = new Interval[]{new Interval(2, 3), new Interval(5, 6), new Interval(3, 4)};
        int[] result = findNextInterval(intervals);
        System.out.println("Input: [[2,3], [5,6], [3,4]]");
        System.out.println("Output : " + Arrays.toString(result));
    }

    private static int[] findNextInterval(Interval[] intervals) {
        int n = intervals.length;
        // heap for finding the maximum start
        PriorityQueue<Integer> maxStartHeap = new PriorityQueue<>(n, (i1, i2) -> intervals[i2].start - intervals[i1].start);
        // heap for finding the minimum end
        PriorityQueue<Integer> maxEndHeap = new PriorityQueue<>(n, (i1, i2) -> intervals[i2].end - intervals[i1].end);
        int[] result = new int[n];
        for (int i = 0; i < intervals.length; i++) {
            maxStartHeap.offer(i);
            maxEndHeap.offer(i);
        }

        // go through all the intervals to find each interval's next interval
        for (int i = 0; i < n; i++) {
            int topEnd = maxEndHeap.poll(); // let's find the next interval of the interval which has the highest 'end'
            result[topEnd] = -1; // defaults to -1
            if (intervals[maxStartHeap.peek()].start >= intervals[topEnd].end) {
                // poll the top one as default, if we will add this interval to result if we could not find closer than this.
                int topStart = maxStartHeap.poll();
                // find the the interval that has the closest 'start'
                while (!maxStartHeap.isEmpty() && intervals[maxStartHeap.peek()].start >= intervals[topEnd].end) {
                    topStart = maxStartHeap.poll();
                }
                // adding index of topStart in result array as next interval of topEnd
                result[topEnd] = topStart;
                maxStartHeap.add(topStart); // put the interval back as it could be the next interval of other intervals
            }
        }
        return result;
    }

    private static class Interval {
        int start = 0;
        int end = 0;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
