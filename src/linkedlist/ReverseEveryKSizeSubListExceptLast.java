package linkedlist;

public class ReverseEveryKSizeSubListExceptLast {
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
        if (K <= 1 || head == null || head.next == null) {
            return head;
        }
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode prev;
        ListNode start;
        ListNode then;
        ListNode tail;
        prev = newHead;
        tail = prev;
        start = prev.next;
        while (true) {
            // check if there's k nodes left-out
            for (int i = 0; i < K; i++) {
                tail = tail.next;
                if (tail == null)
                    return newHead.next;
            }
            // reverse k nodes
            for (int i = 0; i < K - 1; i++) {
                then = start.next;
                start.next = then.next;
                then.next = prev.next;
                prev.next = then;
            }
            prev = start;
            tail = prev;
            start = prev.next;
        }
    }

    private static class ListNode {
        int value;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }
}
