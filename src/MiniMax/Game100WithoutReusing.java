package MiniMax;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * In the “100 game” two players take turns adding, to a running total, any integer from 1 to 10. The player who first causes the running total
 * to reach or exceed 100 wins.
 *
 * What if we change the game so that players cannot re-use integers?
 *
 * For example, two players might take turns drawing from a common pool of numbers from 1 to 15 without replacement until they reach a total >= 100.
 *
 * Given two integers maxChoosableInteger and desiredTotal, return true if the first player to move can force a win, otherwise return false. Assume
 * both players play optimally.
 *
 * Input: maxChoosableInteger = 10, desiredTotal = 11
 *
 * Output: false
 *
 * Explanation:
 * No matter which integer the first player choose, the first player will lose. The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10. The second player will win by choosing 10
 * and get a total = 11, which is >= desiredTotal. Same with other integers chosen by the first player, the second player will always win.
 */

public class Game100WithoutReusing {
    public static void main(String[] args) {
        int maxChoosableInteger = 10;
        int desiredTotal = 11;
        Game100WithoutReusing game = new Game100WithoutReusing();
        System.out.println("maxChoosableInteger: " + maxChoosableInteger);
        System.out.println("desiredTotal: " + desiredTotal);
        System.out.println("using recursion: " + game.canWinUsingRecursion(maxChoosableInteger, desiredTotal));
        System.out.println("using topdown dp: " + game.canWinUsingTopDown(maxChoosableInteger, desiredTotal));
    }

    private boolean canWinUsingRecursion(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= maxChoosableInteger) {
            return true;
        }
        // 1 + 2 + ... + maxChoosableInteger < desiredTotal means can't reach to desiredTotal
        // == n * (n + 1)/2 < desiredtotal
        if ((maxChoosableInteger * (maxChoosableInteger + 1) / 2) < desiredTotal) {
            return false;
        }
        return canWinRecursive(maxChoosableInteger, desiredTotal, new boolean[maxChoosableInteger + 1]);
    }

    private boolean canWinRecursive(int maxChoosableInteger, int desiredTotal, boolean[] chosen) {
        // desiredTotal <= 0 means the prior one wins
        if (desiredTotal <= 0) {
            return false;
        }
        // We want to see if a path exists where all the branches for the opponent results in a false.
        for (int i = 1; i <= maxChoosableInteger; i++) {
            if (chosen[i]) {
                continue;
            }
            chosen[i] = true; // you choose i as maxChoosableInteger
            boolean opponent = canWinRecursive(maxChoosableInteger, desiredTotal - i, chosen);
            if (!opponent) { // if opponent cannot win meaning you won at that path
                chosen[i] = false; // make if back to initial state
                return true;
            }
            // make if back to initial state for
            chosen[i] = false;
        }
        // else any integer you choose you are not going to win.
        return false;
    }


    public boolean canWinUsingTopDown(int maxChoosableInteger, int desiredTotal) {

        if (desiredTotal <= maxChoosableInteger) {
            return true;
        }
        // 1 + 2 + ... + maxChoosableInteger < desiredTotal means can't reach to desiredTotal
        // == n * (n + 1)/2 < desiredtotal
        if ((maxChoosableInteger * (maxChoosableInteger + 1) / 2) < desiredTotal) {
            return false;
        }
        // key: chosen[] to string, value: canIWinWithSituation return value when chosen to string is key
        Map<String, Boolean> dp = new HashMap<>();

        return canWinTopDown(dp, maxChoosableInteger, desiredTotal, new boolean[maxChoosableInteger + 1]);
    }

    private boolean canWinTopDown(Map<String, Boolean> dp, int maxChoosableInteger, int curDesiredTotal, boolean[] chosen) {
        if (curDesiredTotal <= 0) {
            return false;
        }
        String chosenSerialization = Arrays.toString(chosen);
        if (dp.containsKey(chosenSerialization)) {
            return dp.get(chosenSerialization);
        }

        for (int i = 1; i <= maxChoosableInteger; i++) {
            if (chosen[i]) {
                continue;
            }
            chosen[i] = true;
            if (!canWinTopDown(dp, maxChoosableInteger, curDesiredTotal - i, chosen)) {
                dp.put(chosenSerialization, true);
                chosen[i] = false;
                return true;
            }
            chosen[i] = false;
        }
        dp.put(chosenSerialization, false);
        return false;
    }

}