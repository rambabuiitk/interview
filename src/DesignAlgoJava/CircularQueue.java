package DesignAlgoJava;

public class CircularQueue {
    public static void main(String[] args) {
        int K = 3;
        CircularQueue circularQueue = new CircularQueue(K);
        System.out.println("circularQueue.enQueue(1): " + circularQueue.enQueue(1));  // return true
        System.out.println("circularQueue.enQueue(2): " + circularQueue.enQueue(2));  // return true
        System.out.println("circularQueue.enQueue(3): " + circularQueue.enQueue(3));  // return true
        System.out.println("circularQueue.enQueue(4): " + circularQueue.enQueue(4)); // return false, the queue is full
        System.out.println("circularQueue.Rear() : " + circularQueue.Rear());  // return 3
        System.out.println("circularQueue.isFull(): " + circularQueue.isFull());  // return true
        System.out.println("circularQueue.deQueue(): " + circularQueue.deQueue());  // return true
        System.out.println("circularQueue.enQueue(4): " + circularQueue.enQueue(4));  // return true
        System.out.println("circularQueue.Rear(): " + circularQueue.Rear());  // return 4
    }

    int[] queue;
    int front;
    int rear = -1;
    int len = 0;

    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    public CircularQueue(int k) {
        queue = new int[k];
    }

    /**
     * Checks whether the circular queue is empty or not.
     */
    public boolean isEmpty() {
        return len == 0;
    }

    /**
     * Checks whether the circular queue is full or not.
     */
    public boolean isFull() {
        return len == queue.length;
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    public boolean enQueue(int value) {
        if (!isFull()) {
            rear = (rear + 1) % queue.length;
            queue[rear] = value;
            len++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete an element from the circular queue. Return true if the operation is successful.
     */
    public boolean deQueue() {
        if (!isEmpty()) {
            front = (front + 1) % queue.length;
            len--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get the front item from the queue.
     */
    public int Front() {
        return isEmpty() ? -1 : queue[front];
    }

    /**
     * Get the last item from the queue.
     */
    public int Rear() {
        return isEmpty() ? -1 : queue[rear];
    }


}
