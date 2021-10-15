package DesignAlgoJava;

public class CircularDeque {
    public static void main(String[] args) {
        int K = 3;
        CircularDeque cdque = new CircularDeque(K);
        System.out.println("cdque.insertLast(1): " + cdque.insertLast(1));  // return true
        System.out.println("cdque.insertLast(2): " + cdque.insertLast(2));  // return true
        System.out.println("cdque.insertFront(3): " + cdque.insertFront(3));  // return true
        System.out.println("cdque.insertFront(4): " + cdque.insertFront(4)); // return false, the queue is full
        System.out.println("cdque.getRear() : " + cdque.getRear());  // return 2
        System.out.println("cdque.isFull(): " + cdque.isFull());  // return true
        System.out.println("cdque.deleteLast(): " + cdque.deleteLast());  // return true
        System.out.println("cdque.insertFront(4): " + cdque.insertFront(4));  // return true
        System.out.println("cdque.getFront(): " + cdque.getFront());  // return 4
    }

    int[] queue;
    int front = 0;
    int rear = 0;
    int len;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public CircularDeque(int k) {
        queue = new int[k + 1];
        len = k + 1;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (!isFull()) {
            queue[front] = value;
            front = (front + 1) % len;
            return true;
        }
        return false;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (!isFull()) {
            rear = (rear - 1 + len) % len;
            queue[rear] = value;
            return true;
        }
        return false;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (!isEmpty()) {
            front = (front - 1 + len) % len;
            return true;
        }
        return false;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (!isEmpty()) {
            rear = (rear + 1) % len;
            return true;
        }
        return false;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        return isEmpty() ? -1 : queue[(front - 1 + len) % len];
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        return isEmpty() ? -1 : queue[rear];
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return (front + 1) % len == rear;
    }

}
