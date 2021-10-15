package slidingwindow;

import java.util.LinkedList;

public class MovingAverageFromStream {

    public static void main(String[] args) {
        MovingAverageFromStream stream = new MovingAverageFromStream(3);
        System.out.println("stream.next(1) : " + stream.next(1));
        System.out.println("stream.next(2) : " + stream.next(2));
        System.out.println("stream.next(3) : " + stream.next(3));
        System.out.println("stream.next(4) : " + stream.next(4));
        System.out.println("stream.next(5) : " + stream.next(5));
    }

    double sum;
    int size;
    LinkedList<Integer> list;

    public MovingAverageFromStream(int size) {
        this.list = new LinkedList<>();
        this.size = size;
    }

    public double next(int val) {
        sum = sum + val;
        // add new element to the list
        list.offer(val);

        // if the list is still smaller than k
        if (list.size() <= size) {
            return sum / list.size();
        }

        // list is greater than k so remove the left most element.
        sum = sum - list.poll();
        // return the average
        return sum / size;
    }
}
