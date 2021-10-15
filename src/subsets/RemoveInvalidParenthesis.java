import java.util.ArrayList;
import java.util.List;

public class RemoveInvalidParenthesis {
    public static void main(String[] args) {
        String input = "(a)())()";
        System.out.println("Input: " + input);
        List<String> output = removeInvalidParentheses(input);
        System.out.println("Output: " + output);
    }
    private static List<String> removeInvalidParentheses(String s) {
        List<String> output = new ArrayList<>();
        removeHelper(s, output, 0, 0, '(', ')');
        return output;
    }

    private static void removeHelper(String s, List<String> output, int iStart, int jStart, char openParen, char closedParen) {
        int numOpenParen = 0, numClosedParen = 0;
        // here i loops until string end
        for (int i = iStart; i < s.length(); i++) {
            // count open parenthesis in the string
            if (s.charAt(i) == openParen) {
                numOpenParen++;
            }
            // count closed parenthesis in the string
            if (s.charAt(i) == closedParen) {
                numClosedParen++;
            }
            // closed parenthesis is greater than open meaning we have extra closed parenthesis and needs to be removed
            if (numClosedParen > numOpenParen) {
                // loop j from start till i for removing the invalid parenthesis
                for (int j = jStart; j <= i; j++) // Try removing one at each position, skipping duplicates
                    if (s.charAt(j) == closedParen && (j == jStart || s.charAt(j - 1) != closedParen)) {
                        // Recursion: iStart = i since we now have valid closed parenthesis thru i.
                        // jStart = j prevents duplicates
                        removeHelper(s.substring(0, j) + s.substring(j + 1), output, i, j, openParen, closedParen);
                    }
                return; // Stop here. The recursive calls handle the rest of the string.
            }
        }
        // No invalid closed parenthesis detected. Now check opposite direction, or reverse back to original direction.
        String reversed = new StringBuilder(s).reverse().toString();
        if (openParen == '(')
            removeHelper(reversed, output, 0, 0, ')','(');
        else
            output.add(reversed);
    }

}
