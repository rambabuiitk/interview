package mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MeetingScheduler {
    public static void main(String[] args) {
        List<Interval> slots1 = new ArrayList<>(Arrays.asList(
                new Interval(10, 50),
                new Interval(60, 120),
                new Interval(140, 210)));
        List<Interval> slots2 = new ArrayList<>(Arrays.asList(
                new Interval(0, 15),
                new Interval(60, 70)));
        // number of minutes the new meeting should be scheduled for
        int duration = 8;
        System.out.println("slots1: " + slots1);
        System.out.println("slots2: " + slots2);
        System.out.println("duration: " + duration);
        Interval meetingInterval = scheduleMeeting(slots1, slots2, duration);
        System.out.print("Output: " + meetingInterval);
    }

    private static Interval scheduleMeeting(final List<Interval> slots1,
                                            final List<Interval> slots2,
                                            final int duration) {
        // sort slots1 by start time.
        Collections.sort(slots1, (a, b) -> a.start - b.start);
        // sort slots2 by start time.
        Collections.sort(slots2, (a, b) -> a.start - b.start);

        int i = 0;
        int j = 0;
        // loop until availability list is over
        while (i < slots1.size() && j < slots2.size()) {
            int intersectStart = Math.max(slots1.get(i).start,
                    slots2.get(i).start); // find the matching available start
            int intersectEnd = Math.min(slots1.get(i).end, slots2.get(i).end); // find the matching available end
            if (intersectStart + duration <= intersectEnd) {// check if duration can fit in this matching point.
                // found the slot. Schedule meeting in this slot
                return new Interval(intersectStart, intersectStart + duration);
            } else if (slots1.get(i).end < slots2.get(j).end) { // move next from the interval which is finishing first
                i++;
            } else {
                j++;
            }
        }
        return new Interval(-1, -1); // found no available time
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