import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class WindowedMap {

    private Map<String, Node> cache;
    private Node head;
    private Node tail;
    private long sum;

    private final long timeWindow = 5 * 60 * 1000;

    public WindowedMap() {
        // Here I am using ConcurrentHashMap just to be thread safe.
        cache = new HashMap<>();
        head = new Node("", 0);
        tail = new Node("", 0);
        head.next = tail;
        tail.pre = head;
        head.pre = null;
        tail.next = null;
    }

    public int get(String key) throws NoSuchElementException {
        // if cache does not contains key return -1
        if (!cache.containsKey(key)) {
            throw new NoSuchElementException();
        }
        removeExpiredEvents();
        if (!cache.containsKey(key)) {
            throw new NoSuchElementException();
        }
        // if it contains key get the linkedlist node
        Node node = cache.get(key);
        return node.value;
    }

    public void put(String key, int value) {
        removeExpiredEvents();
        if (cache.get(key) != null) {
            Node node = cache.get(key);
            node.timestamp = System.currentTimeMillis();
            sum -= node.value;
            node.value = value;
            sum += node.value;
            deleteNode(node);
            addToHead(node);
        } else { // element is not in the cache so we have to add in cache and also to head of linkedlist
            Node node = new Node(key, value);
            sum += node.value;
            cache.put(key, node);
            addToHead(node);
        }
    }

    private void delete(String key) {
        removeExpiredEvents();
        if (!cache.containsKey(key)) {
            return;
        }
        Node node = cache.get(key);
        sum -= node.value;
        deleteNode(node);
    }

    private double avg() {
        removeExpiredEvents();
        if (cache.size() == 0) {
            return 0.0;
        }

        return sum / cache.size();
    }

    private void removeExpiredEvents() {
        Node lastNode = getLastNode();
        while (lastNode != head && lastNode.timestamp <= System.currentTimeMillis() - timeWindow) {
            sum -= lastNode.value;
            deleteNode(lastNode);
            cache.remove(lastNode.key);
            lastNode = getLastNode();
        }
    }

    private Node getLastNode() {
        return tail.pre;
    }

    public void deleteNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void addToHead(Node node) {
        node.next = head.next;
        node.next.pre = node;
        node.pre = head;
        head.next = node;
    }


    // ------- Double Linked List Node --------
    private static class Node {
        String key;
        int value;
        long timestamp;
        Node pre;
        Node next;

        public Node(String key, int value) {
            this.key = key;
            this.value = value;
            timestamp = System.currentTimeMillis();
        }

        @Override
        public String toString() {
            return " Node{" +
                    "k=" + key +
                    ", v=" + value +
                    '}';
        }
    }
}
