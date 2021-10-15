import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class WindowMap {

    public int timeWindow;
    public Queue<TimedNode> queue;
    public Map<String, TimedNode> map;
    public Long sum = 0L;

    public WindowMap() {
        queue = new LinkedList<>();
        map = new HashMap<>();
        timeWindow = 5 * 60;
    }

    public Integer get(String key) {
        if (!map.containsKey(key)) {
            return null;
        }
        removeExpiredEvents();
        if (map.containsKey(key)) {
            TimedNode node = map.get(key);
            return node.value;
        }

        return null;
    }

    public void put(String key, Integer value) {
        if (map.containsKey(key)) {
            TimedNode node = map.get(key);
            sum -= node.value;
            node.value = value;
            sum += node.value;
        } else {
            TimedNode node = new TimedNode(key, value);
            sum = sum + value;
            queue.add(node);
            map.put(key, node);
        }
    }

    public double avg() {
        removeExpiredEvents();
        return sum / map.size();
    }

    public void delete(String key) {
        if (map.containsKey(key)) {
            TimedNode node = map.remove(key);
            queue.remove(node);
            sum = sum - node.value;
        }
    }

    private void removeExpiredEvents() {
        long currTimeInSeconds = System.currentTimeMillis() / 1000;
        while(!queue.isEmpty() && queue.peek().timeStamp <= currTimeInSeconds - timeWindow) {
            TimedNode node = queue.poll();
            map.remove(node.key);
            sum = sum - node.value;
        }
    }

    class TimedNode {
        public long timeStamp;
        public String key;
        public Integer value;

        public TimedNode(String key, Integer value) {
            this.key = key;
            this.value = value;
            this.timeStamp = System.currentTimeMillis() / 1000;
        }
    }
}
