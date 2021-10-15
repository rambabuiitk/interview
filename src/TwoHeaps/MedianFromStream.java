package TwoHeaps;

import java.util.PriorityQueue;


/**
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) – Add a integer number from the data stream to the data structure.
 * double findMedian() – Return the median of all elements so far.
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median
 * is the mean of the two middle values.
 *
 * For example,
 *
 * [2,3,4], the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Input:
 * addNum(1)
 * addNum(2)
 * findMedian()
 * addNum(3)
 * findMedian()
 *
 * Output:
 * 1.5
 * 2
 *
 * Explanation:
 * addNum(1)
 * addNum(2)
 * findMedian(): 1.5
 * addNum(3)
 * findMedian(): 2.0
 */
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

/**
 * Time Complexity:
 * addNumber: O(logN)
 * findMedian: O(1)
 * Space Complexity: O(N) where N is total number of elements added by addNum
 * Follow Up:
 * Q: What if the there is million number of entries.
 *
 * Ans:
 *
 * Assume (for illustration purposes) that you know that the values are between 1 and 1 million. Set up N bins, of size S.
 * So if S=10000, you’d have 100 bins, corresponding to values [1:10000, 10001:20000, … , 990001:1000000]
 * Then, step through the values. Instead of storing each value, just increment the counter in the appropriate bin. Using the
 * midpoint of each bin as an estimate, you can make a reasonable approximation of the median.
 * Since you don’t know how big your values may get, just pick a bin size large enough that you aren’t likely to run out of memory,
 * using some quick back-of-the-envelope calculations. You might also store the bins sparsely, such that you only add a bin if it contains a value.
 */
