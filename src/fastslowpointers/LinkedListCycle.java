package fastslowpointers;

public class LinkedListCycle {

    private static class ListNode {
        int value;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        System.out.println("1 -> 2 -> 3 -> 4 -> 5 -> 6");
        int cycleStartPoint = findCycleStart(head);
        System.out.println("Cycle Start: " + cycleStartPoint);

        System.out.println("-------------------------------------");
        head.next.next.next.next.next.next = head.next.next;
        System.out.println("1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 3(repeat)");
        cycleStartPoint = findCycleStart(head);
        System.out.println("Cycle Start: " + cycleStartPoint);
    }

    private static int findCycleStart(final ListNode head) {
        // find the LinkedList cycle
        ListNode slow = head;
        ListNode fast = head;
        int cycleLength = 0;
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) { // found the cycle
                hasCycle = true;
                cycleLength = calculateCycleLength(slow);
                break;
            }
        }
        // since has cycle use the two pointers again with cycleLength to get matching point which is start point of cycle
        if (hasCycle) {
            System.out.println("LinkedList has Cycle");
            return findStartPoint(head, cycleLength);
        }
        System.out.println("LinkedList does not have Cycle");
        return -1;
    }

    private static int calculateCycleLength(ListNode start) {
        ListNode current = start;
        int cycleLength = 0;
        // iterate whole cycle till you reach back start node.
        do {
            current = current.next;
            cycleLength++;
        } while (current != start);

        return cycleLength;
    }

    // same as findCycleStart but here fast will move by cycleLength
    private static int findStartPoint(final ListNode head, int cycleLength) {
        ListNode slow = head;
        ListNode fast = head;
        // move fast ahead by 'cycleLength' nodes
        while (cycleLength > 0) {
            fast = fast.next;
            cycleLength--;
        }

        // increment both pointers until they meet at the start of the cycle
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow.value;
    }


}