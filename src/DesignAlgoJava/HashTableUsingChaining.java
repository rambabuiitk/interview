package DesignAlgoJava;

import java.util.Arrays;
import java.util.LinkedList;

public class HashTableUsingChaining<K, V> {
    private static final int MAX_SIZE = 10; // Size of Array
    LinkedList<Cell<K, V>>[] items; // Array of LinkedList whose nodes are Cell

    public static void main(String[] args) {
        HashTableUsingChaining<Integer, String> hashtable = new HashTableUsingChaining<>();
        System.out.println("hashtable.put(1, 'A')");
        hashtable.put(1, "A");
        System.out.println("hashtable.put(2, 'B')");
        hashtable.put(2, "B");
        System.out.println("hashtable.put(3, 'C')");
        hashtable.put(3, "C");
        System.out.println("hashtable.put(1, 'D')");
        hashtable.put(1, "D");
        System.out.println("hashtable.put(2, 'E')");
        hashtable.put(2, "E");
        System.out.println("hashtable.get(1) = " + hashtable.get(1));
		System.out.println("hashtable.get(2) = " + hashtable.get(2));
    }

    public HashTableUsingChaining() {
        items = (LinkedList<Cell<K, V>>[]) new LinkedList[MAX_SIZE];
    }

    public int hashCodeOfKey(K key) {
        return key.hashCode() % items.length;
    }

    public void put(K key, V value) {
        int x = hashCodeOfKey(key);
        if (items[x] == null) {
            items[x] = new LinkedList<Cell<K, V>>();
        }
        // if items[x] is not null then it is collision
        LinkedList<Cell<K, V>> collided = items[x];

        // Loop for an items with same key ad replace if found
        for (Cell<K, V> c : collided) {
            if (c.compareKeyWith(key)) {
                collided.remove(c);
                break;
            }
        }
        // else if we did not found the key in collision itereation then add at the end of linkedlist
        Cell<K, V> cell = new Cell<K, V>(key, value);
        collided.add(cell);
    }

    public V get(K key) {
        int x = hashCodeOfKey(key);
        if (items[x] == null) {
            return null;
        }
        // if items[x] is not null then it is collision
        LinkedList<Cell<K, V>> collided = items[x];

        // Loop for an items with same key ad replace if found
        for (Cell<K, V> c : collided) {
            if (c.compareKeyWith(key)) {
                return c.value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "items=" + Arrays.toString(items);
    }

    private static class Cell<K, V> {
        private K key;
        private V value;

        public Cell(K k, V v) {
            key = k;
            value = v;
        }

        public boolean compareKeyWith(K k) {
            return key.equals(k);
        }
    }
}
