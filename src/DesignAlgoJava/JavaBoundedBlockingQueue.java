package DesignAlgoJava;

import java.util.Deque;
import java.util.LinkedList;

public class JavaBoundedBlockingQueue {

    Deque<Integer> deQueue;
    int size;
    Object lock;

    public JavaBoundedBlockingQueue(int capacity) {
        deQueue = new LinkedList<>();
        size = capacity;
        lock = new Object();
    }

    public static void main(String[] args) throws InterruptedException {
        JavaBoundedBlockingQueue boundedBlockingQueue = new JavaBoundedBlockingQueue(3);
        boundedBlockingQueue.enqueue(1);
        boundedBlockingQueue.dequeue();
        boundedBlockingQueue.dequeue();
        boundedBlockingQueue.enqueue(0);
        boundedBlockingQueue.enqueue(2);
        boundedBlockingQueue.enqueue(3);
        boundedBlockingQueue.enqueue(4);
    }

    // When queue is full,
    // block enqueue thread,
    // add thread to full waiting list
    private void enqueue(int element) throws InterruptedException {
        synchronized (lock) {
            while (deQueue.size() == size) {
                lock.wait();
            }
            deQueue.addLast(element);
            lock.notify();
        }
    }

    // When queue is empty, we block dequeue thread,
    // and add thread to empty waiting list
    private int dequeue() throws InterruptedException {
        int val = 0;
        synchronized (lock) {
            while (deQueue.isEmpty()) {
                lock.wait();
            }
            val = deQueue.removeFirst();
            lock.notify();
        }
        return val;
    }

    public int size() {
        return deQueue.size();
    }
}
