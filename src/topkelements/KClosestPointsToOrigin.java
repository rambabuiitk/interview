package topkelements;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {
    public static void main(String[] args) {
        int[][] input = new int[][]{{-5, 4}, {-6, -5}, {4, 6}};
        int K = 2;
        System.out.println("Input: " + Arrays.deepToString(input));
        System.out.println("K : " + K);
        int[][] output = kClosest(input, K);
        System.out.println("Output: " + Arrays.deepToString(output));
    }

    // Input is 2D array of int points
    private static int[][] kClosest(int[][] points, int K) {
        // here we are not taking sqrt and so just comparing (x2^2 + y2^2) - (x1^2 + y2^2)
        // since we are doing maxheap it is similar to (a,b) -> b-a
        PriorityQueue<int[]> maxHeap =
                new PriorityQueue<int[]>((p1, p2) ->
                        (p2[0] * p2[0] + p2[1] * p2[1]) - (p1[0] * p1[0] + p1[1] * p1[1]));

        for (int[] p : points) {
            maxHeap.offer(p);
            // if the size grows above k we remove 1 element to keep heap size k.
            if (maxHeap.size() > K) {
                maxHeap.poll(); // we are excluding the max element as poll for max heap is highest element
            }
        }
        // at te end heap will have k smallest numbers as we were excluding largest element from poll everytime
        int count = K - 1;
        int[][] res = new int[K][2];
        while (count >= 0) {
            res[count] = maxHeap.poll();
            count--;
        }
        return res;
    }

}
