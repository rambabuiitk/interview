package linkedlist;

public class LinkedListPartition {
    public static void main(String[] args) {
        ListNode n = new ListNode(9);
        n.next = new ListNode(2);
        n.next.next = new ListNode(7);
        n.next.next.next = new ListNode(4);
        n.next.next.next.next = new ListNode(6);
        n.next.next.next.next.next = new ListNode(5);
        n.next.next.next.next.next.next = new ListNode(3);
        System.out.println("Input: 9 2 7 4 6 5 3");
        int target = 6;
        System.out.println("Target: " + target);
        ListNode res = partition(n, target);
        System.out.print("Output: ");
        ListNode curr = res;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
    }

    // time: O(N) where N is size of linkedlist
    // space: O(1)
    private static ListNode partition(ListNode head, int target) {
        if (head == null) {
            return head;
        }

        ListNode beforeTail = new ListNode(0);
        ListNode afterTail = new ListNode(0);
        ListNode beforeHead = beforeTail;
        ListNode afterHead = afterTail;
        while (head != null) {
            if (head.val < target) {
                beforeTail.next = head;
                beforeTail = head;
            } else {
                afterTail.next = head;
                afterTail = head;
            }
            head = head.next;
        }

        afterTail.next = null;
        beforeTail.next = afterHead.next;
        return beforeHead.next;
    }


    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}