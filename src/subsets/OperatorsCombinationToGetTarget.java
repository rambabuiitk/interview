import java.util.ArrayList;
import java.util.List;

public class OperatorsCombinationToGetTarget {
    public static void main(String[] args) {
        String num = "105";
        int target = 5;
        System.out.println("num: " + num);
        System.out.println("target: " + target);
        List<String> output = addOperators(num, target);
        System.out.println("Output: " + output);
    }
    
    // T(n) = 3 * T(n-1) + 3 * T(n-2) + 3 * T(n-3) + ... + 3 *T(1);
    // T(n-1) = 3 * T(n-2) + 3 * T(n-3) + ... 3 * T(1);
    // Thus T(n) = 4T(n-1);
    // time : 4*T(N-1) = O(4^N)
    private static List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return result;
        }
        backtrack(result,  num, target, "", 0, 0, 0);
        return result;
    }

    // here "multed" is value that is to be multiplied in the next recursion.
    private static void backtrack(List<String> result, String num, int target,
                                  String tempExp, int currIndex,
                                  long eval, long multed) {

        if (currIndex == num.length()) {
            if (target == eval) {
                result.add(tempExp);
            }
            return;
        }
        for (int i = currIndex; i < num.length(); i++) {
            if (i != currIndex && num.charAt(currIndex) == '0') {
                break;
            }
            // using long because we do not want to overflow
            long value = Long.parseLong(num.substring(currIndex, i + 1));
            if (currIndex == 0) { // currentIndex is first index. append value in "" (tempExp)
                backtrack(result, num, target, tempExp + value, i + 1, value, value);
            } else { // if currentIndex is not the first index
                // try using "+"
                backtrack(result, num, target, tempExp + "+" + value,  i + 1, eval + value, value);
                // try using "-". for next recursion we will use multed as "-value"
                backtrack(result, num, target, tempExp + "-" + value, i + 1, eval - value, -value);
                // try using "*". for next recursion we will use "multed" as "multed * value"
                // if val = 4
                // for expression 1 + 2 + 3 -> previous value = multed = 3
                // Now (1 + 2 + 3 * 4) = (1 + 2 + 3) - 3 + (3 * 4)
                // currTarget * value = currTarget - multed + multed * value
                // next multed = (3*4) = 12                
                backtrack(result, num, target, tempExp + "*" + value, i + 1, eval - multed + multed * value, multed * value);
            }
        }
    }


}
