package DesignAlgoJava;

import java.util.PriorityQueue;

public class MedianFromStream {

    PriorityQueue<Integer> maxHeap; //containing first half of numbers
    PriorityQueue<Integer> minHeap; //containing second half of numbers

    public MedianFromStream() {
        // if odd max-heap will have 1 more element than min-heap
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap = new PriorityQueue<>();
    }

    public static void main(String[] args) {
        MedianFromStream medianFromStream = new MedianFromStream();
        System.out.println("addNum(1)");
        medianFromStream.addNum(1);
        System.out.println("addNum(2)");
        medianFromStream.addNum(2);
        System.out.println("findMedian(): " + medianFromStream.findMedian());

        System.out.println("addNum(3)");
        medianFromStream.addNum(3);
        System.out.println("findMedian(): " + medianFromStream.findMedian());
    }

    private double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            // we have even number of elements, take the average of middle two elements
            return maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
        }
        // because max-heap will have one more element than the min-heap
        return maxHeap.peek();
    }

    private void addNum(final int num) {
        if (maxHeap.isEmpty() || maxHeap.peek() >= num) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        // either both the heaps will have equal number of elements or max-heap will have one
        // more element than the min-heap
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }
}
