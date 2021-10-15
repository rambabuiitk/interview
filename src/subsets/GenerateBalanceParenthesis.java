import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GenerateBalanceParenthesis {

    public static void main(String[] args) {
        int input = 2;
        System.out.println("n : " + input);
        List<String> output = generateValidParentheses(input);
        System.out.println("Output: " + output);
    }

    private static List<String> generateValidParentheses(final int num) {
        List<String> result = new ArrayList<String>();
        Queue<ParenthesesString> queue = new LinkedList<>();
        queue.add(new ParenthesesString("", 0, 0));
        while (!queue.isEmpty()) {
            ParenthesesString tempString = queue.poll();
            // if we've reached the maximum number of open and close parentheses, add to the result
            if (tempString.openCount == num && tempString.closeCount == num) {
                result.add(tempString.str);
            } else {
                // if we can add an open parentheses, add it
                if (tempString.openCount < num) {
                    queue.add(new ParenthesesString(tempString.str + "(", tempString.openCount + 1, tempString.closeCount));
                }
                // For adding the close ')' it has to be less than openCount in order to be valid
                if (tempString.openCount > tempString.closeCount) {
                    queue.add(new ParenthesesString(tempString.str + ")", tempString.openCount, tempString.closeCount + 1));
                }
            }
        }
        return result;
    }

    private static class ParenthesesString {
        String str;
        int openCount; // open parentheses count
        int closeCount; // close parentheses count

        public ParenthesesString(String str, int openCount, int closeCount) {
            this.str = str;
            this.openCount = openCount;
            this.closeCount = closeCount;
        }
    }
}
