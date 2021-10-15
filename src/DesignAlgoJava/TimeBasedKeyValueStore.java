package DesignAlgoJava;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TimeBasedKeyValueStore {
    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);
        String output = timeMap.get("foo", 1);
        System.out.println(output);
        output = timeMap.get("foo", 3);
        System.out.println(output);
        timeMap.set("foo", "bar2", 4);
        output = timeMap.get("foo", 4);
        System.out.println(output);
        output = timeMap.get("foo", 5);
        System.out.println(output);
    }

    private static class TimeMap {
        private Map<String, TreeMap<Integer, String>> map;

        public TimeMap() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            if (!map.containsKey(key)) {
                map.put(key, new TreeMap<>());
            }
            map.get(key).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            TreeMap<Integer, String> treeMap = map.get(key);
            if (treeMap == null) {
                return "";
            }
            // floorKey method is used to return the greatest key less than or equal to given key from the parameter.
            Integer floor = treeMap.floorKey(timestamp);
            if (floor == null) {
                return "";
            }
            return treeMap.get(floor);
        }
    }

}
