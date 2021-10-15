package linkedlist;

public class RemoveDuplicatesAndOriginalFromSortedLinkList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);

        System.out.println("1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5");
        ListNode output = removeDuplicatesAndOriginal(head);
        ListNode curr = output;
        while (curr != null) {
            System.out.print(curr.value + " ");
            curr = curr.next;
        }
    }
    // time: O(N)
    private static ListNode removeDuplicatesAndOriginal(final ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode output = new ListNode(-1);
        ListNode curr = head;
        ListNode nodeBeforeDup = output;
        nodeBeforeDup.next = curr;
        while (curr != null) {
            while (curr.next != null && curr.value == curr.next.value) {
                curr = curr.next;
            }
            // now curr points to last element in duplicates if exist else to the next non element.
            if (nodeBeforeDup.next != curr) { // duplicates detected
                nodeBeforeDup.next = curr.next; //remove the dups.
                curr = nodeBeforeDup.next; //reposition the curr pointer.
            }  else {  //no dup, move down both pointer.
                nodeBeforeDup = nodeBeforeDup.next;
                curr = curr.next;
            }
        }
        return output.next;
    }

    private static class ListNode {
        int value;
        ListNode next;
        ListNode(int value) {
            this.value = value;
        }
    }

}
