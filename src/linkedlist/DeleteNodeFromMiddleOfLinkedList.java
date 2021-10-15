package linkedlist;

public class DeleteNodeFromMiddleOfLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        ListNode node = head.next.next.next;
        System.out.print("Input: 2->4->6->8->10");
        System.out.println("node: " + node.value);
        deleteNode(node);
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.value + " ");
            curr = curr.next;
        }
    }


    private static boolean deleteNode(ListNode node) {
        if (node == null || node.next == null) {
            return false;
        }
        node.value = node.next.value;
        node.next = node.next.next;
        return true;
    }

    private static class ListNode {
        int value;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

}
