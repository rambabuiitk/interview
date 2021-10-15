package karat;

import java.util.Stack;

public class BasicCalculator2 {

    public static int eval(String expression) {
        if (expression == null || expression.isEmpty()) {
            return 0;
        }

        int result = 0;
        int sign = 1;
        Stack<Integer> st = new Stack<>();
        for (int i =0;i<expression.length();i++) {
            if (Character.isDigit(expression.charAt(i))) {
                StringBuilder num = new StringBuilder();
                num.append(expression.charAt(i));
                while (i+1 < expression.length() && Character.isDigit(expression.charAt(i+1))) {
                    num.append(expression.charAt(++i));
                }
                result += Integer.parseInt(num.toString()) * sign;
            } else  if (expression.charAt(i) == '+') {
                sign = 1;
            } else if(expression.charAt(i) == '-') {
                sign = -1;
            } else if (expression.charAt(i) == '(') {
                st.push(result);
                st.push(sign);
                result = 0;
                sign = 1;
            } else  if (expression.charAt(i) == ')') {
                result = result * st.pop() + st.pop();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(eval("2+((8+2)+(3-999))"));
    }
}
