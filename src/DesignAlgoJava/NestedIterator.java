package DesignAlgoJava;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class NestedIterator implements Iterator<Integer> {
    public static void main(String[] args) {
        List<NestedInteger> input = new ArrayList<>();

        List<NestedInteger> n1 = new ArrayList<>();
        n1.add(new NestedInteger(true, 1, null));
        n1.add(new NestedInteger(true, 1, null));
        input.add(new NestedInteger(false, null, n1));

        n1 = new ArrayList<>();
        n1.add(new NestedInteger(true, 2, null));
        input.add(new NestedInteger(false, null, n1));

        n1 = new ArrayList<>();
        n1.add(new NestedInteger(true, 1, null));
        n1.add(new NestedInteger(true, 1, null));
        input.add(new NestedInteger(false, null, n1));

        System.out.println("Input: " + input);
        System.out.println("Output: ");
        NestedIterator iter = new NestedIterator(input);
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
    }

    private Deque<NestedInteger> stack;

    // a Deque instead. We'll use addFirst() for push, and removeFirst() for pop.
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new ArrayDeque<>(nestedList);
    }

    @Override
    public Integer next() {
        // throw an exception if there's no elements left.
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        // hasNext ensures the stack top is now an integer. Pop and return this integer.
        return stack.removeFirst().value;
    }

    @Override
    public boolean hasNext() {
        // Check if there are integers left by getting one onto the top of stack.
        makeStackTopAnInteger();
        // If there are any integers remaining, one will be on the top of the stack,
        // and therefore the stack can't possibly be empty.
        return !stack.isEmpty();
    }

    private void makeStackTopAnInteger() {
        // While there are items remaining on the stack and the front of
        // stack is a list (i.e. not integer), keep unpacking.
        while (!stack.isEmpty() && !stack.peekFirst().isInteger) {
            // Put the NestedIntegers onto the stack in reverse order.
            List<NestedInteger> nestedList = stack.removeFirst().list;
            // add all the elements from the list to stack again but in reverse order
            // so we can pick
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.addFirst(nestedList.get(i));
            }
        }
    }

    private static class NestedInteger {
        public boolean isInteger;
        public Integer value;
        public List<NestedInteger> list;

        public NestedInteger(boolean isInteger, Integer value, List<NestedInteger> list) {
            this.isInteger = isInteger;
            this.value = value;
            this.list = list;
        }

        public String toString() {
            if (isInteger && value != null) {
                return value.toString();
            } else {
                return list.toString();
            }
        }
    }
}
