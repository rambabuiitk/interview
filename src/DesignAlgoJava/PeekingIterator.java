package DesignAlgoJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class PeekingIterator implements Iterator<Integer> {
    public static void main(String[] args) {
        ArrayList list = new ArrayList(Arrays.asList(1, 2, 3));
        System.out.println("List: " + list);
        Iterator itr = list.iterator();
        PeekingIterator iterator = new PeekingIterator(itr);
        System.out.println("iterator.next() : " + iterator.next());
        System.out.println("iterator.peek() : " + iterator.peek());
        System.out.println("iterator.next() : " + iterator.next());
        System.out.println("iterator.next() : " + iterator.next());
        System.out.println("iterator.hasNext() : " + iterator.hasNext());
    }

    private Iterator<Integer> iter;
    private Integer peekValue;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        iter = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (peekValue == null) {
            if (!iter.hasNext()) {
                return null;
            }
            peekValue = iter.next();
        }
        return peekValue;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (peekValue != null) {
            Integer toReturn = peekValue;
            peekValue = null;
            return toReturn;
        }
        if (!iter.hasNext()) {
            return null;
        }
        return iter.next();
    }

    @Override
    public boolean hasNext() {
        return peekValue != null || iter.hasNext();
    }

}
