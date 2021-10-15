package linkedlist;

public class EvenAndOddNodesInLinkedList {

    public static void main(String[] args) {
	ListNode n1 = new ListNode(15);
        ListNode n2 = new ListNode(8);
        ListNode n3 = new ListNode(12);
        ListNode n4 = new ListNode(10);
        ListNode n5 = new ListNode(5);
        n1.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        System.out.println("Input: " + n1);
        ListNode output = oddEvenList(n1);
        System.out.println("Output: " + output);

    }

    private static ListNode oddEvenList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode odd = head;
		final ListNode evenHead = head.next;
		ListNode even = evenHead;
		while (even != null && even.next != null) {
			odd.next = even.next;
			odd = odd.next;
			even.next = odd.next;
			even = even.next;
		}
		odd.next = evenHead;
		return head;
	}

	private static class ListNode {
		ListNode next;
		Integer value;
		ListNode(int value) {
			this.value = value;
		}

		public String toString() {
			String output = "";
			ListNode node = next;
			while (node != null) {
				output = output + node.value + "->";
				node = node.next;
			}
			output = output + "NULL";
			return output;
		}
	}

}
