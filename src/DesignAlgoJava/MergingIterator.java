package DesignAlgoJava;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class MergingIterator implements Iterator<Integer> {

    public static void main(String[] args) {
        Iterator<Integer> it1 = Arrays.asList(2, 5, 9).iterator();
        Iterator<Integer> it2 = Collections.emptyIterator();
        Iterator<Integer> it3 = Arrays.asList(4, 10).iterator();

        List<Iterator<Integer>> lists = Arrays.asList(it1, it2, it3);
        MergingIterator itr = new MergingIterator(lists);
        System.out.println("Output: ");
        System.out.println("itr.hasNext(): " + itr.hasNext());
        while (itr.hasNext()) { // true
            System.out.println("itr.next(): " + itr.next());
        }
        System.out.println("itr.hasNext(): " + itr.hasNext());
    }


    /**
     * 1. Using Sorted list like data structure;
     * Using Priority Queue
     * 2. Option2 : We can also solve using peekIterator class and PriorityQueue
     */
    // Here we are creating compared against each value in ascending order
    // PriorityQueue<Pair<>(iterator_index, actual_value)
    private final PriorityQueue<Pair<Iterator<Integer>, Integer>> priorityQueue;


    public MergingIterator(List<Iterator<Integer>> iterators) {
        priorityQueue = new PriorityQueue<>((p1, p2) -> p1.getValue() - p2.getValue());
        init(iterators);
    }

    /**
     * O(K * log(K)) where K is number of iterators
     */
    private void init(List<Iterator<Integer>> iterators) {
        for (Iterator<Integer> iterator : iterators) {
            if (iterator.hasNext()) {
                Integer value = iterator.next();
                priorityQueue.offer(new Pair<>(iterator, value));
            }
        }
    }

    /**
     * O(1) where size is Length of iterators list
     */
    public boolean hasNext() {
        return !priorityQueue.isEmpty();
    }

    /**
     * O(log(K))
     */
    public Integer next() {
        Pair<Iterator<Integer>, Integer> poll = priorityQueue.poll();
        Integer toReturn = poll.getValue();
        if (poll.getKey().hasNext()) {
            Integer next = poll.getKey().next();
            priorityQueue.offer(new Pair<>(poll.getKey(), next));
        }
        return toReturn;
    }

    public class Pair<T,U> {
        private final T key;
        private final U value;

        public Pair(T key, U value) {
            this.key = key;
            this.value = value;
        }

        public T getKey() {
            return this.key;
        }

        public U getValue() {
            return this.value;
        }
    }

}
