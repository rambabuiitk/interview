package karat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Treasure {

    public List<int[]> allPossibleMoves(int[][] board, int i, int j) {
        List<int[]> moves = new ArrayList<>();
        floodFillDFS(board, i, j, moves);
        return moves;
    }

    private void floodFillDFS(int[][] board, int x, int y, List<int[]> moves) {
        if (x < 0 || y < 0 || x >= board.length|| y >= board[0].length || board[x][y] == -1 || board[x][y] == 1) {
            return;
        }

        board[x][y] = 1;
        moves.add(new int[]{x,y});
        floodFillDFS(board, x+1, y, moves);
        floodFillDFS(board, x-1, y, moves);
        floodFillDFS(board, x, y+1, moves);
        floodFillDFS(board, x, y-1, moves);
    }

    public boolean findLegalMoves(int[][] board, int x, int y) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        floodFillDFS(board, x, y, visited);
        for (int i=0;i<board.length;i++) {
            for (int j=0;j<board[0].length;j++) {
                if (!visited[i][j] && board[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private void floodFillDFS(int[][] board, int x, int y, boolean[][] visited) {
        if (x < 0 || y < 0 || x >= board.length|| y >= board[0].length || board[x][y] == -1 || visited[x][y]) {
            return;
        }

        visited[x][y] = true;
        floodFillDFS(board, x+1, y, visited);
        floodFillDFS(board, x-1, y, visited);
        floodFillDFS(board, x, y+1, visited);
        floodFillDFS(board, x, y-1, visited);
    }

    public static List<List<int[]>> findAllTreasures(int[][] board, int[] start, int[] end) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return new ArrayList<>();
        }

        int numOfTreasures = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    numOfTreasures++;
                }
            }
        }

        List<List<int[]>> paths = new ArrayList<>();
        dfs(board, start[0], start[1], new ArrayList<>(), numOfTreasures, end, paths);
        if (paths.size() == 0) {
            return new ArrayList<>();
        }

        int minPathLength = paths.get(0).size();
        for (int i=0;i<paths.size();i++) {
            minPathLength = Math.min(minPathLength, paths.get(i).size());
        }

        int minPath = minPathLength;
        return paths.stream().filter(path -> path.size() == minPath).collect(Collectors.toList());
    }

    private static void dfs(int[][] board, int x, int y, List<int[]> path, int remainTreasures, int[] end, List<List<int[]>> paths) {
        if (x < 0 || y < 0 || x >= board.length|| y >= board[0].length || board[x][y] == -1 || board[x][y] == 2) {
            return;
        }

        path.add(new int[]{x,y});
        int temp = board[x][y];
        if (temp == 1) {
            remainTreasures--;
        }

        if (x == end[0] && y == end[1] && remainTreasures == 0) {
            paths.add(new ArrayList<>(path));
            path.remove(path.size()-1);
            board[x][y] = temp;
            return;
        }

        board[x][y] = 2;
        dfs(board, x+1, y, path, remainTreasures, end, paths);
        dfs(board, x-1, y, path, remainTreasures, end, paths);
        dfs(board, x, y+1, path, remainTreasures, end, paths);
        dfs(board, x, y-1, path, remainTreasures, end, paths);
        board[x][y] = temp;
        path.remove(path.size()-1);
    }

    public static void main(String[] args) {
        int[][] board3 = {
                {1, 0, 0, 0, 0 },
                {0, -1, -1, 0, 0},
                {0, -1, 0, 1, 0 },
                {-1, 0, 0, 0, 0 },
                {0, 1, -1, 0, 0 },
                {0, 0, 0, 0, 0 }
        };

        List<List<int[]>> result = findAllTreasures(board3, new int[]{5, 2}, new int[]{2, 0});
       for (List<int[]> path: result) {
           for (int[] point: path) {
               System.out.print("(" + point[0] + "," + point[1]+") ");
           }
           System.out.println();
       }
    }
}
