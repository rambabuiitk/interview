package linkedlist;

public class IntersectionPointOfTwoLinkedList {

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        a.next = new ListNode(3);
        a.next.next = new ListNode(6);
        a.next.next.next = new ListNode(9);
        a.next.next.next.next = new ListNode(12);
        System.out.println("List1: 1 -> 3 -> 6 -> 9 -> 12");

        ListNode b = new ListNode(2);
        b.next = a.next.next; // Here we put the intersection point. please see that
        System.out.println("List2: 2 -> 6 -> 9 -> 12");
        ListNode output = getIntersectionNode(a, b);
        int val = output == null ? -1 : output.value;
        System.out.println("Intersecting Node: " + output.value);

    }

    private static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = length(headA);
        int lenB = length(headB);
        // move headA and headB to the same start point
        // A is greater we will move to next element in A until size is same
        while (lenA > lenB) {
            headA = headA.next;
            lenA--;
        }
        // B is greater we will move to next element in B until size is same
        while (lenA < lenB) {
            headB = headB.next;
            lenB--;
        }
        // not both list are of equal size
        // find the intersection until end of linkedlist
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    private static int length(ListNode node) {
        int length = 0;
        while (node != null) {
            node = node.next;
            length++;
        }
        return length;
    }

    private static class ListNode {
        int value;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

}
