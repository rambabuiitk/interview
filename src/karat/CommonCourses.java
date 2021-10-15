package karat;

import java.util.*;
public class CommonCourses {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    public static Map<int[], List<String>> getPossiblePairs(String[][] input){
        Map<int[], List<String>> result = new HashMap();
        Map<Integer, Set<String>> adjList = new HashMap();
        for(String[] course : input){
            int studentId = Integer.parseInt(course[0]);
            adjList.computeIfAbsent(studentId, k-> new HashSet<>()).add(course[1]);
        }
        List<Integer> studentIds = new ArrayList<>(adjList.keySet());
        for(int i = 0; i < studentIds.size(); i++){
            int curr = studentIds.get(i);
            for(int j = i + 1; j < studentIds.size(); j++){
                List<String> commonCourses = findCommon(studentIds.get(j), curr, adjList);
                result.put(new int[]{curr, studentIds.get(j)}, commonCourses);
            }
        }
        return result;
    }

    public static List<String> findCommon(int id1, int id2, Map<Integer, Set<String>> adjMap){
        Set<String> student1 = adjMap.get(id1);
        Set<String> student2 = adjMap.get(id2);
        Set<String> student1Copy = new HashSet<>(student1);
        student1Copy.retainAll(student2);
        return new ArrayList<>(student1Copy);
    }
}

