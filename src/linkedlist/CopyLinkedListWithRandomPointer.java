package linkedlist;

public class CopyLinkedListWithRandomPointer {
    public static void main(String[] args) {
        ListNode head = new ListNode(7);
        ListNode thirteen = new ListNode(13);
        ListNode eleven = new ListNode(11);
        ListNode ten = new ListNode(10);
        ListNode one = new ListNode(1);
        head.index = 0;
        head.next = thirteen;
        thirteen.index = 1;
        thirteen.next = eleven;
        eleven.index = 2;
        eleven.next = ten;
        ten.index = 3;
        ten.next = one;
        one.index = 4;

        head.random = null;
        thirteen.random = head;
        eleven.random = one;
        ten.random = eleven;
        one.random = head;
        System.out.println("Input: ");
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr);
            curr = curr.next;
        }
        ListNode result = deepCopy(head);
        System.out.println("\nOutput: ");
        curr = result;
        while (curr != null) {
            System.out.print(curr);
            curr = curr.next;
        }
    }

    private static ListNode deepCopy(ListNode head) {
        ListNode iter = head;
        ListNode next;

        // First round: make copy of each node,
        // and link them together side-by-side in a single list.
        while (iter != null) {
            next = iter.next;

            ListNode temp = new ListNode(iter.value);
            iter.next = temp;
            temp.next = next;

            iter = next;
        }

        // Second round: assign random pointers for the copy nodes.
        iter = head;
        while (iter != null) {
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }

        // Third round: restore the original list, and extract the copy list.
        iter = head;
        ListNode pseudoHead = new ListNode(0);
        ListNode copy;
        ListNode copyIter = pseudoHead;

        while (iter != null) {
            next = iter.next.next;

            // extract the copy
            copy = iter.next;
            copyIter.next = copy;
            copyIter = copy;

            // restore the original list
            iter.next = next;

            iter = next;
        }

        return pseudoHead.next;

    }

    private static class ListNode {
        int value;
        ListNode next;
        ListNode random;
        int index;

        ListNode(int value) {
            this.value = value;
            this.next = null;
            this.random = null;
            this.index = 0;
        }

        @Override
        public String toString() {
            return "[" + value + ", " + (random == null ?  "null" : random.index)+ ']';
        }
    }

}
