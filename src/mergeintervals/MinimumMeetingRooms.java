package mergeintervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumMeetingRooms {

    public static void main(String[] args) {
        List<Meeting> input = new ArrayList<Meeting>() {
            {
                add(new Meeting(4, 5));
                add(new Meeting(2, 3));
                add(new Meeting(2, 4));
                add(new Meeting(3, 5));
            }
        };
        System.out.println("Input: " + input);
        int output = findMinimumMeetingRooms(input);
        System.out.println("Output: " + output);
    }

    private static int findMinimumMeetingRooms(final List<Meeting> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return 0;
        }
        Collections.sort(intervals, Comparator.comparingInt(a -> a.start));
        int minRooms = 0;

        PriorityQueue<Meeting> minHeap =
                new PriorityQueue<>(intervals.size(), (a, b) -> Integer.compare(a.end, b.end));

        for (Meeting meeting : intervals) {
            // poll until there are no conflicts.
            while (!minHeap.isEmpty() && meeting.start >= minHeap.peek().end) {
                minHeap.poll();
            }
            minHeap.offer(meeting);
            minRooms = Math.max(minRooms, minHeap.size());
        }
        return minRooms;
    }

    private static class Meeting {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public String toString() {
            return "[" + this.start + "," + this.end + "]";
        }
    }
}