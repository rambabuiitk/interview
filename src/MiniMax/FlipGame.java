package MiniMax;

import java.util.HashMap;
import java.util.Map;

/**
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
 *
 * Write a function to determine if the starting player can guarantee a win.
 *
 * Input:
 *  s = "++++"
 *
 *
 * Output: true
 *
 * Explanation: The starting player can guarantee a win by flipping the middle
 * "++"
 *  to become
 * "+--+"
 */

public class FlipGame {
    public static void main(String[] args) {
        String input = "++++";
        System.out.println("Input: " + input);
        System.out.println("using backtrack: " + canWin(input));
        System.out.println("using memoization: " + canWinMemoization(input));
    }

    private static boolean canWin(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        return canWinBacktrack(s);
    }

    private static boolean canWinBacktrack(String s) {
        // at each character we check if the current player udates the ++ can he win
        // if he can make the move else move to next character
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') { // if we found ++
                String opponent = s.substring(0, i) + "--" + s.substring(i + 2); // change to '--'
                if (!canWin(opponent)) { // if the opponent string returns false meaning you won
                    return true;
                }
            }
        }
        // opponent is able to win with any change you make in string so you lost
        return false;
    }


    private static boolean canWinMemoization(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        Map<String, Boolean> map = new HashMap<>();
        return canWinMemoized(map, s);
    }

    private static boolean canWinMemoized(Map<String, Boolean> dp, String s) {
        if (dp.containsKey(s)) {
            return dp.get(s);
        }
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                String newS = s.substring(0, i) + "--" + s.substring(i + 2);
                if (!canWinMemoized(dp, newS)) { // if the opponent string returns false meaning you won
                    dp.put(s, true);
                    return true;
                }
            }
        }
        // opponent is able to win with any change you make in string so you lost
        dp.put(s, false);
        return false;
    }
}
