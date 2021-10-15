package DesignAlgoJava;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TimeHitCounter {

    public static void main(String[] args) {
        TimeHitCounter counter = new TimeHitCounter();
        System.out.println("hit(1)");
        counter.hit(1);
        System.out.println("hit(2)");
        counter.hit(2);
        System.out.println("hit(3)");
        counter.hit(3);
        System.out.println("getHits(4) : " + counter.getHits(4));
        System.out.println("hit(300)");
        counter.hit(300);
        System.out.println("getHits(300) : " + counter.getHits(300));
        System.out.println("getHits(301) : " + counter.getHits(301));
    }

    private static int timeWindow;
    // Queue will only have timestamps which are unique and no duplicates
    private Queue<Integer> queue;
    // map will store timestamp and total of hits encountered at that time
    private Map<Integer, Integer> timestampHitCountMap;

    public TimeHitCounter() {
        timeWindow = 60 * 5; // 60 seconds * 5 minutes
        queue = new LinkedList<>();
        timestampHitCountMap = new HashMap<>();
    }

    void hit(int timestamp) {
        if (timestampHitCountMap.containsKey(timestamp)) {
            // if the hit is already in the map just update the counter no need to add in queue
            timestampHitCountMap.put(timestamp, timestampHitCountMap.get(timestamp) + 1);
        } else { // if it is new hit remove expired hits from map and add the new hit timestamp to queue and map
            removeExpiredHits(timestamp);
            queue.add(timestamp);
            timestampHitCountMap.put(timestamp, 1);
        }
    }

    /**
     * Return the number of hits in the past 5 minutes.
     */
    int getHits(int timestamp) {
        // cleanup old hits
        removeExpiredHits(timestamp);
        int totalHits = 0;
        // sum all the values from the timestampHitCountMap
        for (int counts : timestampHitCountMap.values()) {
            totalHits = totalHits + counts;
        }
        return totalHits;
    }

    // Remove all hits that are too old.
    private void removeExpiredHits(int currTimeStamp) {
        // validate against the peek of the queue and delete all hits older than peek
        while (!queue.isEmpty() && queue.peek() <= currTimeStamp - timeWindow) {
            // remove the oldest hit whose timestamp is less than timeWindow
            timestampHitCountMap.remove(queue.poll());
        }
    }
}
