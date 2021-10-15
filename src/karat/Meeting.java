package karat;

import java.util.ArrayList;
import java.util.List;

public class Meeting {

    public boolean canSchedule(List<int[]> meetings, int start, int end) {
        for (int[] meeting : meetings) {
            if ((start >= meeting[0] && start < meeting[1]) ||
                    (end > meeting[0] && end <= meeting[1]) ||
                    (start < meeting[0] && end > meeting[1])) {
                return false;
            }
        }
        return true;
    }

    public List<int[]> spareTime(List<int[]> meetings) {
        if (meetings == null || meetings.size() == 0) {
            return new ArrayList<>();
        }
        meetings = mergeMeetings(meetings);
        List<int[]> result = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < meetings.size(); i++) {
            result.add(new int[]{start, meetings.get(i)[0]});
            start = meetings.get(i)[1];
        }
        return result;
    }

    public List<int[]> mergeMeetings(List<int[]> meetings) {
          List<int[]> result = new ArrayList<>();
          meetings.sort((a, b) -> a[0] - b[0]);
          int start = meetings.get(0)[0];
          int end = meetings.get(0)[1];

        for (int[] meeting: meetings) {
            if (start < meeting[1]) {
                end = Math.max(end, meeting[1]);
            } else {
                result.add(new int[]{start, end});
                start = meeting[0];
                end = meeting[1];
            }
        }
        return result;
    }
}
