package BinarySearch;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthSmallestInSortedMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
            {1, 5, 9}, 
            {10, 11, 13}, 
            {12, 13, 15}
        };
        int k = 8;
        System.out.println("Input: " + Arrays.deepToString(matrix));
        System.out.println("k: " + k);
        int result = findKthSmallest(matrix, k);
        System.out.print("Output: " + result);
    }

    private static int findKthSmallest(int[][] matrix, int k) {
        // min heap with comparator as comparing two elements from matrix
        PriorityQueue<ElementNode> minHeap =
                new PriorityQueue<>((n1, n2) -> matrix[n1.row][n1.col] - matrix[n2.row][n2.col]);

        // put the 1st element of each row in the min heap
        // we don't need to push more than 'k' elements in the heap
        for (int i = 0; i < matrix.length && i < k; i++) {
            minHeap.add(new ElementNode(i, 0)); // here since column is 0 we put ElementNode(i, 0)
        }
        // take the smallest (top) element form the min heap, if the running count is equal to k return the number
        // if the row of the top element has more elements, add the next element to the heap
        int numberCount = 0;
        int result = 0;
        while(!minHeap.isEmpty()){
            ElementNode elementNode = minHeap.poll();
            result = matrix[elementNode.row][elementNode.col];
            numberCount++;
            if(numberCount == k) {
                break;
            }
            elementNode.col++;
            if(matrix[0].length > elementNode.col) {// of there are more elements in the same row
                minHeap.add(elementNode);
            }
        }
        return result;
    }

    private static class ElementNode {
        int row;
        int col;

        ElementNode(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
