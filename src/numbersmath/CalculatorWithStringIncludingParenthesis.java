package numbersmath;

import java.util.Stack;

public class CalculatorWithStringIncludingParenthesis {
    public static void main(String[] args) {
        System.out.println("'100 * ( 2 + 12 )' = " + evaluateExpression("100 * ( 2 + 12 )"));
        System.out.println("'10*3+6/2' = " + evaluateExpression("10*3+6/2"));
        System.out.println("'-1+2' = " + evaluateExpression("-1+2"));
    }

    private static int evaluateExpression(String s) {
        // removing all the space here
        s = s.replaceAll("\\s+", "");
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        for (int i = 0; i < s.length(); ) {
            char c = s.charAt(i);
            // if we encounter an open parenthesis
            if (c == '(') {
                // find the block and use the recursive to solve
                int l = 1;
                int j = i + 1;
                while (j < s.length() && l > 0) {
                    if (s.charAt(j) == '(') l++;
                    else if (s.charAt(j) == ')') l--;
                    j++;
                }
                int blockValue = evaluateExpression(s.substring(i + 1, j - 1));
                i = j;
                if (sign == '+') {
                    stack.push(blockValue);
                } else if (sign == '-') {
                    stack.push(-blockValue);
                } else if (sign == '*') {
                    stack.push(stack.pop() * blockValue);
                } else if (sign == '/') {
                    stack.push(stack.pop() / blockValue);
                }
            } else if (Character.isDigit(c)) { // if its a digit
                int j = i;
                int value = 0;
                while (j < s.length() && Character.isDigit(s.charAt(j))) {
                    value = 10 * value + (s.charAt(j) - '0');
                    j++;
                }
                i = j;
                if (sign == '+') {
                    stack.push(value);
                } else if (sign == '-') {
                    stack.push(-value);
                } else if (sign == '*') {
                    stack.push(stack.pop() * value);
                } else if (sign == '/') {
                    stack.push(stack.pop() / value);
                }
            } else {
                sign = c;
                i++;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
