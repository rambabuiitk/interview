package linkedlist;

public class ReverseLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);

        ListNode result = ReverseLinkedList.reverse(head);
        System.out.print("For 2->4->6->8->10, Reversed LinkedList are: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }

    private static ListNode reverse(final ListNode head) {
        ListNode current = head; // current node that we will be processing
        ListNode previous = null;
        ListNode next = null;
        // iterate till the end of linkedlist and make node.next as prev
        while(current != null) {
            next = current.next; // temp store the next value
            current.next = previous; // reverse the current node
            previous = current; // change previous to current for upcoming node
            current = next; // move to next node
        }
        // after looping, current will point to null after last node.
        // previous will point to last node
        return previous; // return the last node as it is the new head.
    }

    static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }
}
