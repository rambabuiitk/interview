package depthfirstsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FriendCircle {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 0, 0, 1},
                {1, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 0, 1, 1}
        };
        System.out.println("Input: " + Arrays.deepToString(matrix));
        int output = findCircleNum(matrix);
        System.out.println("Output: " + output);
    }

    // Using DFS O(N^2)
    private static int findCircleNum(int[][] M) {
        int count = 0;
        // Here we have N * N matrix with N friends.
        if (M == null || M.length == 0) {
            return count;
        }
        // visited[i] == 1 meaning node is already visited.
        // 0 meaning node is not yet visited
        int[] visited = new int[M.length];
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) { // node at row i not visited so visit it for all that row
                findCircleRecursive(M, visited, i);
                count++;
            }
        }
        return count;
    }

    private static void findCircleRecursive(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            // here m[i][j] is 1 meaning ith and jth student are friends
            // if we have not visited that column then we will visit jth friends we will recursively do that.
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                findCircleRecursive(M, visited, j);
            }
        }
    }
}
