package linkedlist;

public class InsertNodeInSortedLinkedList {

    public static void main(String[] args) {
        ListNode n = new ListNode(1);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(5);
        n.next = n1;
        n1.next = n2;
        n2.next = n3;
        System.out.print("Before ");
        System.out.println("1->2->3-5");
        int a = 4;
        System.out.println("Insert Value : " + a);
        ListNode newHead = insertNode(n, a);
        System.out.println("1->2->3->4->5");
    }

    // time: O(n)
    // space: O(1)
    private static ListNode insertNode(ListNode head, int x) {
        ListNode node = new ListNode(x);
        ListNode curr;
        // if the list is empty or x is equal to head
        // insert at the head
        if (head == null || head.value >= node.value) {
            node.next = head;
            head = node;
        } else {
            /* Locate the node before the point of insertion */
            curr = head;
            while (curr.next != null
                    && curr.next.value < node.value) {
                curr = curr.next;
            }
            // out of loop meaning curr.next (5) element is smaller than x (4)
            node.next = curr.next;
            curr.next = node;
        }
        return head;
    }

    private static class ListNode {
        int value;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

}
