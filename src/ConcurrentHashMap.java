import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentHashMap {

    Deque<Integer> queue = new LinkedList<>();
    private Map<String, Object> map = new HashMap<>();

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock writeLock = readWriteLock.writeLock();
    Lock readLock = readWriteLock.readLock();

    public void put(String key, Object value) {
        writeLock.lock();
        map.put(key, value);
        writeLock.unlock();
    }

    public Object get(String key) {
        readLock.lock();
        Object result = map.get(key);
        readLock.unlock();
        return result;
    }
}
