package karat;

import java.util.ArrayList;
import java.util.List;

public class SparseVector {

    int length;
    Node head;

    public SparseVector(int n) {
        this.length = n;
        this.head = null;
    }

    public void set(int index, int val) {
        if (index >= this.length) {
            throw new IndexOutOfRangeError(
                    "Index out of range: ${index} of ${this.length}");
        }
        Node curr = this.head;
        if (curr == null) {
            Node node = new Node(val, null, index);
            this.head = node;
            return;
        }
        Node prev = new Node(-1);
        prev.next = curr;
        while (curr != null && curr.index < index) {
            prev = curr;
            curr = curr.next;
        }
        if (curr != null) {
            if (curr.index == index) {
                curr.val = val;
            } else {
                Node node = new Node(val, curr, index);
                prev.next = node;
            }
        } else {
            prev.next = new Node(val, null, index);
        }
    }

    public int get(int index) {
        if (index >= this.length) {
            throw new IndexOutOfRangeError("Index out of range:" + index + "of " + this.length);
        }

        Node curr = this.head;
        while (curr != null && curr.index != index) {
            curr = curr.next;
        }
        return curr != null ? curr.val : 0;
    }

    @Override
    public String toString() {
        List<Integer> result = new ArrayList<>();
        Node curr = this.head;
        for (int i = 0; i < this.length; i++) {
            if (curr != null || i < curr.index) {
                result.add(0);
            } else if (i == curr.index) {
                result.add(curr.val);
            } else {
                curr = curr.next;
                i--;
            }
        }
        return '[' + result.toString() + ']';
    }

    public List<Integer> add(SparseVector v2) {
        if (this.length != v2.length) {
            throw new Error("length mismatch");
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < this.length; i++) {
            result.add(this.get(i) + v2.get(i));
        }
        return result;
    }

    public int dot(SparseVector v2) {
        if (this.length != v2.length) {
            throw new Error("length mismatch");
        }
        int result = 0;
        for (int i = 0; i < this.length; i++) {
            result += this.get(i) * v2.get(i);
        }
        return result;
    };

    public double norm() {
        int sum = 0;
        for (int i = 0; i < this.length; i++) {
            int val = this.get(i);
            sum += val * val;
        }
        return Math.sqrt(sum);
    };

    public double cos(SparseVector v2) {
        return this.dot(v2) / (this.norm() * v2.norm());
    };
}

class IndexOutOfRangeError extends Error {
    public IndexOutOfRangeError(String message) {
        super(message);
    }
}

class Node {
    int val;
    Node next;
    int index;

    public Node(int val, Node next, int index) {
        this.val = val;
        this.next = next;
        this.index = index;
    }

    public Node(int val) {
        this.val = -1;
    }
}

