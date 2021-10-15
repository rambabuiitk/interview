package linkedlist;

public class ReverseEveryKSizeSubListFromLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        int k = 3;
        System.out.println("Input: 1->2->3->4->5->6->7->8");
        System.out.println("k: " + k);
        ListNode result = reverse(head, k);
        System.out.print("Output: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }

    private static ListNode reverse(ListNode head, final int K) {
        if (K <= 1 || head == null) {
            return head;
        }
        ListNode current = head;
        ListNode previous = null;
        while (true) {
            ListNode lastNodeOfPreviousPart = previous;
            // after reversing the LinkedList 'current' will become the last node of the sub-list
            ListNode lastNodeOfSubList = current;
            ListNode next = null; // this is used temp in every sublist
            // reverse each sublist in patch of size K
            for (int i = 0; current != null && i < K; i++) {
                next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            if (lastNodeOfPreviousPart != null) {
                lastNodeOfPreviousPart.next = previous;
            } else {
                head = previous;
            }
            // connect with next part
            lastNodeOfSubList.next = current;

            // we have reached the end of the linkedlist
            if (current == null) {
                break;
            }
            // lastnode of previous sublist will become the previous for next batch
            previous = lastNodeOfSubList;
        }
        return head;
    }

    static class ListNode {
        int value;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }
}
