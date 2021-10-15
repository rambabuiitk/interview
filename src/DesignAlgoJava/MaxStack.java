package DesignAlgoJava;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class MaxStack {
    public static void main(String[] args) {
        MaxStack ms = new MaxStack();
        System.out.println("ms.push(5)");
        ms.push(5);
        System.out.println("ms.push(1)");
        ms.push(1);
        System.out.println("ms.push(5)");
        ms.push(5);
        System.out.println("ms.top(): " + ms.top());
        System.out.println("ms.popMax(): " + ms.popMax());
        System.out.println("ms.top(): " + ms.top());
        System.out.println("ms.peekMax(): " + ms.peekMax());
        System.out.println("ms.pop(): " + ms.pop());
        System.out.println("ms.top(): " + ms.top());
    }

    private final ListNode head;
    private final TreeMap<Integer, LinkedList<ListNode>> map = new TreeMap<>();

    public MaxStack() {
        head = new ListNode(0);
        head.next = head.prev = head;
    }

    public void push(int x) {
        ListNode node = new ListNode(x);
        node.next = head;
        node.prev = head.prev;
        head.prev.next = node;
        head.prev = node;
        map.computeIfAbsent(x, k -> new LinkedList<>()).add(node);
    }

    public int pop() {
        ListNode tail = head.prev;
        if (tail == head) {
            return 0;   // no element exist
        }
        deleteNode(tail);
        // since it's pop(), we are always sure that the last element in the map's value list will be the tail
        map.get(tail.val).removeLast();
        if (map.get(tail.val).isEmpty()) {
            map.remove(tail.val);
        }
        return tail.val;
    }

    public int top() {
        return head.prev.val;
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int max = peekMax();
        ListNode node = map.get(max).removeLast();
        deleteNode(node);
        if (map.get(max).isEmpty()) {
            map.remove(max);
        }
        return max;
    }

    private void deleteNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private class ListNode {
        int val;
        ListNode prev;
        ListNode next;

        public ListNode(int v) {
            val = v;
        }
    }
}
