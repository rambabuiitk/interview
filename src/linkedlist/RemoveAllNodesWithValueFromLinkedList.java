package linkedlist;

public class RemoveAllNodesWithValueFromLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        int val = 2;
        System.out.println("Input: 2->4->6->2->8->2->10->2");
        System.out.println("node: " + val);
        ListNode output = removeElements(head, val);
        ListNode curr = output;
        while (curr != null) {
            System.out.print(curr.value + " ");
            curr = curr.next;
        }
    }


    private static ListNode removeElements(ListNode head, int val) {
        // create a dummy node. 
        // dummy node has to be a value which is not in LinkedList
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        // Iterate till current.next is null. Keep checking next with value
        while (curr.next != null) {
            // next element is a match so skip pointing to next element.
            if (curr.next.value == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next; // else keep moving ahead.
            }
        }
        return dummy.next; 
    }

    private static class ListNode {
        int value;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }
}
