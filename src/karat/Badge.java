package karat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Badge {

    public List<List<String>> invalidBadgeRecords(Map<String, String> records) {
        if (records == null || records.isEmpty()) {
            return new ArrayList<>();
        }

        List<List<String>> result = new ArrayList<>();
        Map<String, Integer> state = new HashMap<>();
        Set<String> invalidEnter = new HashSet<>();
        Set<String> invalidExit = new HashSet<>();
        for (Map.Entry<String, String> entry : records.entrySet()) {
            state.putIfAbsent(entry.getKey(), 0);
            if (entry.getValue().equals("enter")) {
                if (state.get(entry.getKey()) == 0) {
                    state.put(entry.getKey(), 1);
                } else {
                    invalidEnter.add(entry.getKey());
                }
            } else {
                if (state.get(entry.getKey()) == 1) {
                    state.put(entry.getKey(), 0);
                } else {
                    invalidExit.add(entry.getKey());
                }
            }
        }

        for (Map.Entry<String, Integer> entry : state.entrySet()) {
            if (entry.getValue() == 1) {
                invalidEnter.add(entry.getKey());
            }
        }

        result.add(new ArrayList<>(invalidEnter));
        result.add(new ArrayList<>(invalidExit));

        return result;
    }

    public static Map<String, List<String>> getEmployeesBadgedSecuredRoom(String[][] badge_times) {
        Map<String, List<String>> map = new HashMap<>();

        for(int i = 0; i < badge_times.length; i++) {
            String[] entry = badge_times[i];
            map.computeIfAbsent(entry[0], v -> new ArrayList<>()).add(entry[1]);
        }

        Map<String, List<String>> result = new HashMap();


        for (Map.Entry<String, List<String>> entry: map.entrySet()) {
            List<String> list = entry.getValue();
            list.sort(Comparator.comparingInt(Integer::valueOf));
            int start = Integer.valueOf(list.get(0));
            List<String> l = new ArrayList();
            l.add(String.valueOf(start));

            for (int i = 1; i < list.size(); i++) {
                int current = Integer.valueOf(list.get(i));
                if (current - start <= 100) {
                    l.add(list.get(i));
                }

                if (current - start > 60 || i == list.size() - 1) {
                    if (l.size() >= 3) {
                        result.put(entry.getKey(), l);
                    } else {
                        start = Integer.valueOf(list.get(i));
                        l = new ArrayList();
                        l.add(String.valueOf(start));
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[][] input = new String[][] {
                {"Paul",     "1355"},
                {"Jennifer", "1910"},
                {"John",      "835"},
                {"John",      "830"},
                {"Paul",     "1315"},
                {"John",     "1615"},
                {"John",     "1640"},
                {"Paul",     "1405"},
                {"John",      "855"},
                {"John",      "930"},
                {"John",      "915"},
                {"John",      "730"},
                {"John",      "940"},
                {"Jennifer", "1335"},
                {"Jennifer",  "730"},
                {"John",     "1630"},
                {"Jennifer",    "5"}
        };
        Map<String, List<String>> map = getEmployeesBadgedSecuredRoom(input);
        System.out.println(map);
    }
}
