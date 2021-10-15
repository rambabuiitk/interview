package mergeintervals;

import java.util.Arrays;
import java.util.Comparator;

public class ConflictingMeetings {
    public static void main(String[] args) {
        Interval[] intervals = {
                new Interval(1, 4),
                new Interval(2, 5),
                new Interval(7, 9)
        };
        System.out.println("Input: " + Arrays.toString(intervals));
        boolean output = canAttendAllMeetings(intervals);
        System.out.println("Output " + output);
    }

    private static boolean canAttendAllMeetings(final Interval[] intervals) {
        // sort the intervals by start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.start));
        for (int i = 1; i < intervals.length; i++) {
            // please note the comparison above, it is "<" and not "<="
            if (intervals[i].start < intervals[i - 1].end) {
                // intervals are conflicting
                // you can add intervals[i] and intervals[i-1] to get all conflicting appointments
                return false;
            }
        }
        return true;
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