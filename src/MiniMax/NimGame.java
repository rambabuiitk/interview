package MiniMax;

/**
 * You are playing the following Nim Game with your friend:
 *
 * Initially, there is a heap of stones on the table.
 * You and your friend will alternate taking turns, and you go first.
 * On each turn, the person whose turn it is will remove 1 to 3 stones from the heap.
 * The one who removes the last stone is the winner.
 * Given n, the number of stones in the heap, return true if you can win the game assuming both you and your friend play optimally, otherwise return false.
 *
 * Nim is a mathematical game of strategy in which two players take turns removing (or “nimming”) objects from distinct heaps or piles. On each turn, a player must remove at least one object, and may remove any number of objects provided they all come from the same heap or pile. Depending on the version being played, the goal of the game is either to avoid taking the last object, or to take the last object.
 *
 * Strategy:
 *
 * Let us think of the small cases. It is clear that if there are only one, two, or three stones in the pile, and it is your turn, you can win the game by taking all of them. Like the problem description says, if there are exactly four stones in the pile, you will lose. Because no matter how many you take, you will leave some stones behind for your opponent to take and win the game
 *
 * Input: n = 4
 *
 * Output: false
 *
 * Explanation:
 * These are the possible outcomes:
 * 1. You remove 1 stone. Your friend removes 3 stones, including the last stone. Your friend wins.
 * 2. You remove 2 stones. Your friend removes 2 stones, including the last stone. Your friend wins.
 * 3. You remove 3 stones. Your friend removes the last stone. Your friend wins. In all outcomes, your friend wins.
 */

public class NimGame {
    public static void main(String[] args) {
        int n = 4;
        System.out.println("n : " + n);
        boolean output = canWinNim(n);
        // output is for the one who is picking last
        System.out.println("Output: " + output);
    }

    /**
     * It is clear that if there are only one, two, or three stones in the pile,
     * and it is your turn, you can win the game by taking all of them.
     * Like the problem description says, if there are exactly four stones in the pile,
     * you will lose. Because no matter how many you take, you will leave
     * some stones behind for your opponent to take and win the game.
     */
    private static boolean canWinNim(int n) {
        return (n % 4 != 0);
    }
}

/**
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */
