package linkedlist;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * <p>
 * Input: (1 -> 9) + (9 -> 0 -> 1)
 * Meaning 19 + 109 = 200
 * Output: (0, 0, 2)
 */
public class AddTwoNumberUsingLinkedlist {

    public static void main(String[] args) {
        LinkedList<Integer> l1 = new LinkedList<Integer>();
        l1.add(1);
        l1.add(9);
        System.out.println("Linkedlist 1 : 1 -> 9");
        LinkedList<Integer> l2 = new LinkedList<Integer>();
        l2.add(9);
        l2.add(0);
        l2.add(1);
        System.out.println("Linkedlist 2 : 9 -> 0 -> 1");
        LinkedList<Integer> output = addList(l1, l2);
        System.out.println("Output : " + output);
    }

    private static LinkedList<Integer> addList(LinkedList<Integer> l1,
                                               LinkedList<Integer> l2) {
        int len1 = l1.size();
        System.out.println("len1: " + len1);
        int len2 = l2.size();
        System.out.println("len2: " + len2);
        // add padding at the end. Padding is '0' as padding of 1 to 19 is 019
        // here since the number in LinkedList is reversed we will add padding at the end..
        // 9->1 will become 9->1->0
        if (len1 < len2) {
            System.out.println("len1 < len2");
            l1 = padList(l1, len2 - len1);
            System.out.println("Padded L1 :" + l1);
        } else if (len2 < len1) {
            System.out.println("len2 > len1");
            l2 = padList(l2, len1 - len2);
            System.out.println("Padded L2 :" + l2);
        }

        PartialSum psum = addListHelper(l1, l2);
        if (psum.carry == 0) {
            return psum.sum;
        } else {
            psum.sum.add(psum.carry);
            return psum.sum;
        }
    }

    private static PartialSum addListHelper(LinkedList<Integer> l1, LinkedList<Integer> l2) {
        PartialSum psum = new PartialSum();
        psum.sum = new LinkedList<>();

        if (l1 == null || l2 == null) {
            return psum;
        }
        /*Add smaller digits */
        Iterator<Integer> i1 = l1.iterator();
        Iterator<Integer> i2 = l2.iterator();
        psum.carry = 0;
        while (i1.hasNext() || i2.hasNext()) {
            int value = i1.next() + i2.next() + psum.carry;
            psum.sum.add(value % 10);
            psum.carry = value / 10;
        }
        return psum;
    }

    private static LinkedList<Integer> padList(LinkedList<Integer> l, int padding) {
        for (int i = 0; i < padding; i++) {
            l.addLast(0);
        }
        return l;
    }

    private static class PartialSum {
        private static LinkedList<Integer> sum = null;
        private static int carry = 0;
    }

}
