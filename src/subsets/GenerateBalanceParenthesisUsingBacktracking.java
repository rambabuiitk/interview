import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GenerateBalanceParenthesisUsingBacktracking {

    public static void main(String[] args) {
        int input = 2;
        System.out.println("n : " + input);
        List<String> output = generateParenthesisUsingRecursion(input);
        System.out.println("Output: " + output);
    }

    public static List<String> generateParenthesisUsingRecursion(int num) {
        List<String> result = new ArrayList<String>();
        char[] parenthesesString = new char[2 * num];
        generateValidParenthesesRecursive(result, parenthesesString, num, 0, 0, 0);
        return result;
    }

    private static void generateValidParenthesesRecursive(List<String> result,
                                                          char[] tempString,
                                                          int num,
                                                          int index,
                                                          int openCount,
                                                          int closeCount) {

        // if we've reached the maximum number of open and close parentheses, add to the result
        if (openCount == num && closeCount == num) {
            result.add(new String(tempString));
        } else {
            if (openCount < num) { // if we can add an open parentheses, add it
                tempString[index] = '(';
                generateValidParenthesesRecursive(result, tempString, num, index + 1, openCount + 1, closeCount);
            }

            if (openCount > closeCount) { // if we can add a close parentheses, add it
                tempString[index] = ')';
                generateValidParenthesesRecursive(result, tempString, num, index + 1, openCount, closeCount + 1);
            }
        }
    }

}
