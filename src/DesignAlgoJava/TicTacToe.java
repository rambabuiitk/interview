package DesignAlgoJava;

public class TicTacToe {

    public static void main(String[] args) {
        int n = 3;
        System.out.println("n: " + n);
        TicTacToe toe = new TicTacToe(3);
        System.out.println("toe.move(0, 0, 1)");
        toe.move(0, 0, 1);
        System.out.println("toe.move(0, 2, 2)");
        toe.move(0, 2, 2);
        System.out.println("toe.move(2, 2, 1)");
        toe.move(2, 2, 1);
        System.out.println("toe.move(1, 1, 2)");
        toe.move(1, 1, 2);
        System.out.println("toe.move(2, 0, 1)");
        toe.move(2, 0, 1);
        System.out.println("toe.move(1, 0, 2)");
        toe.move(1, 0, 2);
        System.out.println("toe.move(2, 1, 1)");
        int output = toe.move(2, 1, 1);
        System.out.println("Output : " + output);
    }

    int size;
    private int[] rows;
    private int[] cols;
    private int diagonal;
    private int reverseDiagonal;

    TicTacToe(int n) {
        size = n; // size of grid
        rows = new int[n];
        cols = new int[n];
        diagonal = 0;
        reverseDiagonal = 0;
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     *
     * @param row    The row of the board.
     * @param col    The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either:
     * 0: No one wins.
     * 1: Player 1 wins.
     * 2: Player 2 wins.
     */
    // Here we will keep track of sum of each row and col and diagonal.
    // for player 1 we will do +1 and for player 2 will will -1
    // if sum is +ve -> player1 won els if -ve -> player2 won
    // if sum 0 no one won
    private int move(int row, int col, int player) {
        // wrong move outside of grid
        if (row >= size || col >= size) {
            return 0;
        }
        // as mentioned here we will add +1 for player 1 and -1 for player 2
        int toAdd = player == 1 ? 1 : -1;

        // put the move in particular row and col
        rows[row] = rows[row] + toAdd;
        cols[col] = cols[col] + toAdd;

        // meaning move was done on diagonal
        if (row == col) {
            diagonal = diagonal + toAdd;
        }

        // that is the move was done on reverse diagonal
        // here we can interpret this as col = size - row - 1
        if (row + col == size - 1) {
            reverseDiagonal = reverseDiagonal + toAdd;
        }
        /* when sum of the current certain row or column is n or -n we know a player has won */
        if (Math.abs(rows[row]) == size
                || Math.abs(cols[col]) == size
                || Math.abs(diagonal) == size
                || Math.abs(reverseDiagonal) == size) {
            return player;
        }
        return 0;
    }


}
