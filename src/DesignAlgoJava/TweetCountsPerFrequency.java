package DesignAlgoJava;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TweetCountsPerFrequency {

    public static void main(String[] args) {
        TweetCountsPerFrequency tweetCounts = new TweetCountsPerFrequency();
        System.out.println("recordTweet(\"tweet3\", 0)");
        tweetCounts.recordTweet("tweet3", 0);
        System.out.println("recordTweet(\"tweet3\", 60)");
        tweetCounts.recordTweet("tweet3", 60);
        System.out.println("recordTweet(\"tweet3\", 10)");
        tweetCounts.recordTweet("tweet3", 10);

        System.out.println("getTweetCountsPerFrequency(\"minute\",\"tweet3\",0,59): "
                + tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59));

        System.out.println("getTweetCountsPerFrequency(\"minute\",\"tweet3\",0,60): "
                + tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60));

        System.out.println("recordTweet(\"tweet3\", 120)");
        tweetCounts.recordTweet("tweet3", 10);
        System.out.println("getTweetCountsPerFrequency(\"hour\",\"tweet3\",0, 210): "
                + tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210));
    }

    private Map<String, TreeMap<Integer, Integer>> map;

    TweetCountsPerFrequency() {
        map = new HashMap<>();
    }

    // time: O(1)
    public void recordTweet(String tweetName, int time) {
        if (!map.containsKey(tweetName)) {
            map.put(tweetName, new TreeMap<>());
        }
        TreeMap<Integer, Integer> tweetMap = map.get(tweetName);
        tweetMap.put(time, tweetMap.getOrDefault(time, 0) + 1);
    }

    // time: O()
    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName,
                                                    int startTime, int endTime) {
        if (!map.containsKey(tweetName)) {
            return null;
        }
        List<Integer> res = new LinkedList<>();

        int interval = 0;
        int size = 0;
        if (freq.equals("minute")) {
            interval = 60;
        } else if (freq.equals("hour")) {
            interval = 3600;
        } else {
            interval = 86400;
        }
        size = ((endTime - startTime) / interval) + 1;

        int[] buckets = new int[size];

        TreeMap<Integer, Integer> tweetMap = map.get(tweetName);
        // Returns a entries of the portion of map whose keys range from until to
        // time : map.subMap(start, end) is O(1)
        // iterating entries is O(K) where k is entries in submap
        for (Map.Entry<Integer, Integer> entry : tweetMap.subMap(startTime, endTime + 1).entrySet()) {
            int index = (entry.getKey() - startTime) / interval;
            buckets[index] = buckets[index] + entry.getValue();
        }

        for (int num : buckets) {
            res.add(num);
        }

        return res;
    }


}
