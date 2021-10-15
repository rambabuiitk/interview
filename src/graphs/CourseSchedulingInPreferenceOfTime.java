package graphs;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class CourseSchedulingInPreferenceOfTime {
    public static void main(String[] args) {
        int[][] courses = new int[][]{{100,200}, {200,1300}, {1000,1250}, {2000,3200}};
        System.out.println("Courses: " + Arrays.deepToString(courses));
        int output = scheduleCourseUsingRecursion(courses);
        System.out.println("Output: " + output);
        System.out.println("---");

        System.out.println("Courses: " + Arrays.deepToString(courses));
        output = scheduleCourse(courses);
        System.out.println("Output: " + output);
    }

    // Using DP
    private static int scheduleCourseUsingRecursion(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        int l = courses.length;
        Map[] mem = new HashMap[l];
        for (int i = 0; i < l; i++) {
            mem[i] = new HashMap<Integer, Integer>();
        }
        return dfs(0, 0, courses, mem);
    }

    private static int dfs(int p, int time, int[][] courses, Map<Integer, Integer>[] mem) {
        if (p == courses.length) return 0;
        Integer max = mem[p].get(time);
        if (max != null) return max;
        max = dfs(p + 1, time, courses, mem);
        if (time + courses[p][0] <= courses[p][1]) {
            max = Math.max(max, 1 + dfs(p + 1, time + courses[p][0], courses, mem));
        }
        mem[p].put(time, max);
        return max;
    }

    // Using Greedy
    private static int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int time = 0;
        for (int[] course : courses)
            if (time + course[0] <= course[1]) {
                pq.add(course[0]);
                time += course[0];
            } else if (!pq.isEmpty() && pq.peek() > course[0]) {
                time += course[0] - pq.remove();
                pq.add(course[0]);
            }
        return pq.size();
    }
}
