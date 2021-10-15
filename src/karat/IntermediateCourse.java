package karat;

import java.util.*;

public class IntermediateCourse {

    public static String findIntermediateCourse(String[][] courses) {

        if (courses == null || courses.length == 0 || courses[0].length == 1) {
            return null;
        }

        // find head course
        Set<String> cache = new HashSet<>();
        HashMap<String, String> map = new HashMap<>();
        for (String[] course : courses){
            cache.add(course[1]);
            map.put(course[0], course[1]);
        }

        String head = "";
        for (String[] course : courses){
            if (!cache.contains(course[0])) {
                head = course[0];
                break;
            }
        }

        int mid = courses.length / 2;
        for (int i = 0; i < mid; i++){
            head = map.get(head);
        }

        return head;
    }

    public static void main(String[] args) {
        String[][] prereqs_courses1 = {
                {"Data Structures", "Algorithms"},
                {"Foundations of Computer Science", "Operating Systems"},
                {"Computer Networks", "Computer Architecture"},
                {"Algorithms", "Foundations of Computer Science"},
                {"Computer Architecture", "Data Structures"},
                {"Software Design", "Computer Networks"}
        };

        System.out.println(findIntermediateCourse(prereqs_courses1));
    }
}
