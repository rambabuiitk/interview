package mergeintervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MergeOverlappingIntervals {

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<>();
        input.add(new Interval(6, 7));
        input.add(new Interval(1, 2));
        input.add(new Interval(2, 4));
        input.add(new Interval(5, 9));
        input.add(new Interval(10, 12));
        System.out.println("Input: " + input);
        List<Interval> output = merge(input);
        System.out.println("Output: " + output);
    }

    private static List<Interval> merge(final List<Interval> intervals) {
        // if we have 1 or no interval return that interval.
        if (intervals.size() <= 1) {
            return intervals;
        }
        // sort intervals by start time
        Collections.sort(intervals, Comparator.comparingInt(a -> a.start));
        List<Interval> mergedIntervals = new LinkedList<>();
        Iterator<Interval> intervalItr = intervals.iterator();
        // get the first interval start and first interval end.
        Interval interval = intervalItr.next();
        int start = interval.start;
        int end = interval.end;
        // iterate all the intervals from second interval
        while (intervalItr.hasNext()) {
            interval = intervalItr.next();
            // second interval starts before end of previous meaning overlaping
            // so pick the longest end amongst both
            if (interval.start <= end) {
                end = Math.max(end, interval.end);
            } else { // non overlapping interval
                // add the previous interval c. with new start and end after merge.
                mergedIntervals.add(new Interval(start, end));
                // update new start and end for future intervals to compare with
                start = interval.start;
                end = interval.end;
            }
        }
        // add the last interval
        mergedIntervals.add(new Interval(start, end));
        return mergedIntervals;
    }

    private static class Interval {
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