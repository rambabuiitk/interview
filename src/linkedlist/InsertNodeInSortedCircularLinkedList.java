package linkedlist;

public class InsertNodeInSortedCircularLinkedList {
    public static void main(String[] args) {

        ListNode n = new ListNode(3);
        ListNode n1 = new ListNode(5);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(2);
        n.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n1;
        System.out.print("Before ");
        System.out.println("3->5->1->2.. again point to 5");
        int a = 4;
        System.out.println("Insert Value : " + a);
        insert(n, a);
        System.out.println("Output will be 3->4->5->1->2");
    }

    // time: O(n)
    // space: O(1)
    private static ListNode insert(ListNode head, int x) {
        if (head == null) {
            ListNode cur = new ListNode(x);
            cur.next = cur;
            return cur;
        }
        ListNode cur = head.next;
        ListNode prev = head;
        boolean find = false;
        while (cur != head) {
            int next = cur.value;
            int pre = prev.value;
            // two conditions
            // 1, node value equals with x
            // 2. x value between two nodes
            if (x == pre
                    || x <= next && x > pre // ascending
                    || next < pre && x > pre // descending
                    || next < pre && x < next) { // descending
                find = true;
                ListNode node = new ListNode(x);
                prev.next = node;
                node.next = cur;
                break;
            }
            prev = cur;
            cur = cur.next;
        }
        // here cur points to head and prev points to last element
        // if we were not able to insert value in middle of linkedlist
        // add at the end 
        if (!find) {
            ListNode node = new ListNode(x);
            prev.next = node;
            node.next = cur;
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
