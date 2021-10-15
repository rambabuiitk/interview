package mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(5, 7));
        intervals.add(new Interval(10, 12));

        Interval newInterval = new Interval(4, 9);

        System.out.println("Intervals: " + intervals);
        System.out.println("New Interval: " + newInterval);
        List<Interval> result = insert(intervals, newInterval);
        System.out.println("Output: " + result);
    }

    private static List<Interval> insert(final List<Interval> intervals, final Interval newInterval) {
        if (intervals == null || intervals.isEmpty()) {
            return Arrays.asList(newInterval);
        }
        List<Interval> mergedIntervals = new ArrayList<>();
        int i = 0;
        // skip all intervals that come before newInterval.
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
            mergedIntervals.add(intervals.get(i));
            i++;
        }
        // merge all intervals that overlap with newInterval
        // if the start of next interval is less then end of new interval means its merging
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
            newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
            i++;
        }
        //add the above merged interval in the output list.
        mergedIntervals.add(newInterval);
        // copy the remaining interval as it is.
        while (i < intervals.size()) {
            mergedIntervals.add(intervals.get(i));
            i++;
        }
        return mergedIntervals;
    }

    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public String toString() {
            return "[" + this.start + "," + this.end + "]";
        }
    }
}
