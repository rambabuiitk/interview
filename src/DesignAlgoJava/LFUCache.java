package DesignAlgoJava;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache {

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        System.out.println("cache.set(1, 1)");
        cache.set(1, 1);
        System.out.println("cache.set(2, 2)");
        cache.set(2, 2);
        System.out.println("cache.get(1): " + cache.get(1));
        System.out.println("cache.set(3, 3)");
        cache.set(3, 3);
        System.out.println("cache.get(2): " + cache.get(2));
        System.out.println("cache.get(3): " + cache.get(3));
        System.out.println("cache.set(4, 4)");
        cache.set(4, 4);
        System.out.println("cache.get(1): " + cache.get(1));
        System.out.println("cache.get(3): " + cache.get(3));
        System.out.println("cache.get(4): " + cache.get(4));
    }

    HashMap<Integer, Integer> keyValueMap;
    HashMap<Integer, Integer> keyCountsMap;
    HashMap<Integer, LinkedHashSet<Integer>> lists;
    int capacity;
    int min = -1;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        keyValueMap = new HashMap<>();
        keyCountsMap = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
        // if key does not exist in the keyvalue map return -1
        if (!keyValueMap.containsKey(key)) {
            return -1;
        }
        // if key exists then get the key count.
        int count = keyCountsMap.get(key);
        // increment the count
        keyCountsMap.put(key, count + 1);

        lists.get(count).remove(key);
        if (count == min && lists.get(count).size() == 0) {
            min++;
        }
        if (!lists.containsKey(count + 1)) {
            lists.put(count + 1, new LinkedHashSet<>());
        }
        lists.get(count + 1).add(key);
        return keyValueMap.get(key);
    }

    public void set(int key, int value) {
        if (capacity <= 0)
            return;
        if (keyValueMap.containsKey(key)) {
            keyValueMap.put(key, value);
            get(key);
            return;
        }
        if (keyValueMap.size() >= capacity) {
            int evict = lists.get(min).iterator().next();
            lists.get(min).remove(evict);
            keyValueMap.remove(evict);
        }
        keyValueMap.put(key, value);
        keyCountsMap.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }

}
