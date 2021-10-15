package linkedlist;

public class ReverseSubListFromLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        head.next.next.next.next.next = new ListNode(12);
        int m = 1;
        int n = 4;
        System.out.println("2->4->6->8->10->12");
        System.out.println("m : " + m);
        System.out.println("n : " + n);
        ListNode result = reverse(head, m, n);
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }

    private static ListNode reverse(ListNode head, final int m, final int n) {
        // if start and end of sublist is same no need to do anything
        if (m == n) {
            return head;
        }
        ListNode current = head; // current node that we will be processing
        ListNode previous = null;
        for (int i = 0; current != null && i < m - 1; i++) {
            previous = current;
            current = current.next;
        }
        // we are interested in three parts of the LinkedList,
        // part before index 'm', part between 'm' and 'q', and the part after index 'q'
        ListNode lastNodeofFirstPart = previous;

        //after reversing the linkedlist current wil become the last node of sublist.
        ListNode lastNodeOfSubLast = current;
        ListNode next = null;
        for (int i = 0; current != null && i < n - m + 1; i++) {
            next = current.next; // store next element pointer in next
            current.next = previous; // point current next to prev
            previous = current; // update previous to new current for upcoming nodes.
            current = next; // move current to next.
        }
        // if we have firstpart then make end of first part point to head of sublist which is at prev
        if (lastNodeofFirstPart != null) {
            lastNodeofFirstPart.next = previous;
        } else { // means our sublist started from head and there is no first part
            head = previous;
        }
        lastNodeOfSubLast.next = current;

        return head; // return the last node as it is the new head.
    }

    static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }
}
