package karat;

import java.util.ArrayList;
import java.util.List;

public class FindRectangles {

    public List<int[]> findOneRectangle(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return new ArrayList<>();
        }

        List<int[]> result = new ArrayList<>();
        for (int i =0;i<board.length;i++) {
            for (int j=0;j<board[0].length;j++) {
                if (board[i][j] == 0) {
                    result.add(new int[]{i, j});
                    int height = 1, width = 1;
                    while (i + height < board.length && board[i + height][j] == 0) {
                        height++;
                    }
                    while (j + width < board[0].length && board[i][j + width] == 0) {
                        width++;
                    }
                    result.add(new int[]{i + height - 1, j + width - 1});
                    break;
                }
            }
        }

        return result;
    }

    public List<List<int[]>> findMultipleRectangles(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return new ArrayList<>();
        }

        List<List<int[]>> result = new ArrayList<>();

        for (int i =0;i<board.length;i++) {
            for (int j=0;j<board[0].length;j++) {
                if (board[i][j] == 0) {
                    List<int[]> rectangle = new ArrayList<>();
                    rectangle.add(new int[]{i, j});
                    board[i][j]=1;
                    int height = 1, width = 1;
                    while (i + height < board.length && board[i + height][j] == 0) {
                        height++;
                    }
                    while (j + width < board[0].length && board[i][j + width] == 0) {
                        width++;
                    }
                    rectangle.add(new int[]{i + height - 1, j + width - 1});
                    for(int h=0;h<height;j++) {
                        for (int w=0;w<width;j++) {
                            board[i+h][j+w]=1;
                        }
                    }
                    result.add(rectangle);
                }
            }
        }

        return result;
    }

    public List<List<int[]>> findMultipleShapes(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return new ArrayList<>();
        }

        List<List<int[]>> result = new ArrayList<>();
        for (int i =0;i<board.length;i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    List<int[]> shape = new ArrayList<>();
                    floodFillDFS(board, i, j, shape);
                    result.add(shape);
                }
            }
        }

        return result;
    }

    private void floodFillDFS(int[][] board, int x, int y, List<int[]> shape) {
        if (x < 0 || y < 0 || x > board.length|| y > board[0].length || board[x][y] == 1) {
            return;
        }

        board[x][y] = 1;
        shape.add(new int[]{x,y});
        floodFillDFS(board, x+1, y, shape);
        floodFillDFS(board, x-1, y, shape);
        floodFillDFS(board, x, y+1, shape);
        floodFillDFS(board, x, y-1, shape);
    }
}
