package mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveIntervalInArray {

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{0, 2}, {3, 4}, {5, 7}};
        int[] newInterval = new int[]{1, 6};
        System.out.println("intervals: " + Arrays.deepToString(intervals));
        System.out.println("toBeRemoved: " + Arrays.toString(newInterval));
        int[][] output = remove(intervals, newInterval);
        System.out.println("Output: " + Arrays.deepToString(output));
    }

    private static int[][] remove(int[][] intervals, int[] toBeRemoved) {
        List<int[]> mergedIntervals = new ArrayList<>();
        // if intervals is empty or null, nothing to be removed
        if (intervals == null || intervals.length == 0) {
            return new int[][]{};
        }
        int i = 0;
        // skip/add all intervals that come before toBeRemoved.
        // meaning end of interval is before start of toBeRemoved
        while (i < intervals.length && intervals[i][1] < toBeRemoved[0]) {
            mergedIntervals.add(intervals[i]);
            i++;
        }
        // we have merged all intervals that end before toBeRemoved
        // now if the start of nex interval is less then end of new interval means overlapping.
        while (i < intervals.length && intervals[i][0] < toBeRemoved[1]) {
            // whichever starts early which is min of start in both the intervals is the new start
            if (intervals[i][0] < toBeRemoved[0]) {
                mergedIntervals.add(new int[]{intervals[i][0], toBeRemoved[0]});
            }
            if (intervals[i][1] > toBeRemoved[1]) {
                mergedIntervals.add(new int[]{toBeRemoved[1], intervals[i][1]});
            }
            i++;
        }

        // copy remaining intervals as it is.
        while (i < intervals.length) {
            mergedIntervals.add(intervals[i]);
            i++;
        }
        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }
}
