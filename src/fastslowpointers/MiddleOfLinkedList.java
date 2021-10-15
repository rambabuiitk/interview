package fastslowpointers;

public class MiddleOfLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        System.out.println("1 -> 2 -> 3 -> 4 -> 5 -> 6");
        ListNode middleNode = findMiddle(head);
        System.out.println("Output: " + middleNode.value);
    }

    private static ListNode findMiddle(final ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // When the fast node reaches the end the slow node will be in middle.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static class ListNode {
        int value = 0;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

}