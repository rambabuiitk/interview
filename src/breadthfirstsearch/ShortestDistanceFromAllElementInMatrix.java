package breadthfirstsearch;

import java.util.*;

/**
 * Shortest Distance from All Building in Grid
 *
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 *
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * Input:
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 *
 * Output:
 * Shortest Distance: 7
 *
 * Explanation:
 * Buildings at (0,0), (0,4), (2,2)
 * Obstacle at (0,2)
 * The point
 * (1,2)
 *  is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
 */
public class ShortestDistanceFromAllElementInMatrix {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 0, 2, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}
        };
        System.out.println("Grid: " + Arrays.deepToString(grid));
        int output = shortestDistance(grid);
        System.out.println("Shortest Distance from All Building: " + output);
    }

    private static int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // grid to store distance of each point
        int[][] dist = new int[m][n];
        // Initialize building list and accessibility matrix `grid`
        List<Point> buildings = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) { // found  building add to buildings list
                    buildings.add(new Point(i, j, 0));
                }
                // marking each node as negative meaning visited in first run
                grid[i][j] = -grid[i][j];
            }
        }
        // grid will be negative for all the walls and buildings after above loops

        // for each location in grid check its distance from
        for(int k = 0; k < buildings.size(); k++) {
            bfs(buildings.get(k), k, dist, grid);
        }
        int minDistance = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // in the dfs above all the locations from where distance is possible from all buildings
                // will have grid[i][j] = building.size as on that point we can build house and distance is possible
                // also check if distance at that point is min update the minDistance
                if (grid[i][j] == buildings.size() && (minDistance < 0 || dist[i][j] < minDistance)) {
                    minDistance = dist[i][j];
                }
            }
        }
        return minDistance;
    }

    private static void bfs(Point root, int k, int[][] dist, int[][] grid) {
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        Queue<Point> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Point curr = q.poll();
            dist[curr.x][curr.y] = dist[curr.x][curr.y] + curr.dist;

            for (int[] dir : dirs) {
                int x = curr.x + dir[0];
                int y = curr.y + dir[1];
                if (x >= 0 && x < grid.length
                        && y >= 0 && y < grid[0].length
                        && grid[x][y] == k) {
                    grid[x][y] = k + 1; // update the gid to
                    q.add(new Point(x, y, curr.dist + 1));
                }
            }
        }
    }

    private static class Point {
        int x;
        int y;
        int dist;
        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
