
package karat;

import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class KeyValueStore {
    public HashMap<String, String> globalMap;
    public Stack<Transaction> ts;
    private ReadWriteLock lock;

    public KeyValueStore() {
        globalMap = new HashMap<>();
        ts = new Stack<>();
        lock = new ReentrantReadWriteLock();
    }

    public void set(String key, String value) {
        lock.writeLock().lock();
        Transaction activeTransaction = ts.peek();
        if(activeTransaction != null) {
            activeTransaction.transactionMap.put(key,value);
        }
        lock.writeLock().unlock();
    }
    public String get(String key) {
        lock.readLock().lock();
        String returnValue;
        if(ts.isEmpty()) {
            returnValue = globalMap.getOrDefault(key, "don't exist");
        } else {
            Transaction activeTransaction = ts.peek();
            returnValue = activeTransaction.transactionMap.getOrDefault(key, "don't exist");
        }
        lock.readLock().unlock();
        return returnValue;
    }

    public void delete(String key) {
        lock.writeLock().lock();
        if(ts.isEmpty()) {
            globalMap.remove(key);
        } else {
            Transaction activeTransaction = ts.peek();
            activeTransaction.transactionMap.remove(key);
        }
        lock.writeLock().unlock();
    }

    public void beginTransaction() {
        lock.writeLock().lock();
        Transaction trans = new Transaction(new HashMap<>(globalMap));
        ts.push(trans);
        lock.writeLock().unlock();
    }

    public void commitTransaction() {
        lock.writeLock().lock();

        if (!ts.isEmpty()) {
            Transaction activeTransaction = ts.pop();
            if (activeTransaction != null) {
                HashMap<String, String> map = activeTransaction.transactionMap;
                for (HashMap.Entry<String, String> entry : map.entrySet()) {
                    globalMap.put(entry.getKey(), entry.getValue());
                }
            }
        }
        lock.writeLock().unlock();

    }

    public void rollbackTransaction() {
        lock.writeLock().lock();
        if (!ts.isEmpty()) {
            ts.pop();
        }
        lock.writeLock().unlock();
    }

    public static void main(String[] args) {

        KeyValueStore s = new KeyValueStore();

        s.beginTransaction();
        s.set("e", "50");
        System.out.println(s.get("e")); // 50

        s.beginTransaction();
        s.set("f", "60");
        s.set("e", "55");
        System.out.println(s.get("f")); // 60
        System.out.println(s.get("e")); // 55

        s.beginTransaction();
        s.set("g", "70");
        s.delete("e");
        System.out.println(s.get("g")); // 70
        System.out.println(s.get("e")); // -2147483648
        s.rollbackTransaction();

        System.out.println(s.get("g")); // -2147483648
        s.set("e", "58");
        s.delete("e");
        System.out.println(s.get("e")); // -2147483648
        s.rollbackTransaction();

        System.out.println(s.get("g")); // -2147483648
        System.out.println(s.get("f")); // -2147483648
        System.out.println(s.get("e")); // 50
        s.commitTransaction();

        System.out.println(s.get("e"));
    }
}

class Transaction {
    public HashMap<String, String> transactionMap = new HashMap<>();
    Transaction(HashMap<String, String> map) {
        this.transactionMap = map;
    }
}