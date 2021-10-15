package DesignAlgoJava;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCache {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        System.out.println("cache.put(1, 1)");
        cache.put(1, 1);
        System.out.println("cache.put(2, 2)");
        cache.put(2, 2);

        System.out.println("cache.get(1): " + cache.get(1));
        System.out.println("cache.put(3, 3)");
        cache.put(3, 3);
        System.out.println("cache.get(2): " + cache.get(2));
        System.out.println("cache.put(4, 4)");
        cache.put(4, 4);
        System.out.println("cache.get(1): " + cache.get(1));
        System.out.println("cache.get(3): " + cache.get(3));
        System.out.println("cache.get(4): " + cache.get(4));
        System.out.println(cache.cache);
    }

    private Map<Integer, Node> cache;
    private Node head;
    private Node tail;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
	// Here I am using ConcurrentHashMap just to be thread safe.
        cache = new ConcurrentHashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.pre = head;
        head.pre = null;
        tail.next = null;
    }

    public int get(int key) {
    	// if cache does not contains key return -1
        if (!cache.containsKey(key)) {
            return -1;
        }
        // if it contains key get the linkedlist node
        Node node = cache.get(key);
        int result = node.value;
        // delete the node from that linkedlist and add it to the head
        deleteNode(node);
	// adding node to head as it is recently used.
        addToHead(node);
        return result;
    }

    public void put(int key, int value) {
    	// check if it there in cache or not if it is in cache,
		// add to the head of linkedlist
        if (cache.get(key) != null) {
            Node node = cache.get(key);
            node.value = value;
            deleteNode(node);
            addToHead(node);
        } else { // element is not in the cache so we have to add in cache and also to head of linkedlist
     	 Node node = new Node(key, value);
            cache.put(key, node);
            // if cache has capacity add it
            if (cache.size() <= capacity) {
            	addToHead(node);
            } else { // cache does not have capacity remove last recently used element which is at tail
            	// here tail points to numm and so last element is tail.pre
                cache.remove(tail.pre.key);
                deleteNode(tail.pre);
                addToHead(node);
            }
        }
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
        int key;
        int value;
        Node pre;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
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
