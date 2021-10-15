package karat;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BasicCalculator3 {

    public static String eval(String expression, Map<String, Integer> vars) {
        if (expression == null || expression.isEmpty()) {
            return "";
        }

        int result = 0;
        int sign = 1;
        Stack<Integer> st = new Stack<>();
        Stack<String> st2 = new Stack<>();
        for (int i =0;i<expression.length();i++) {
            if (Character.isDigit(expression.charAt(i))) {
                StringBuilder num = new StringBuilder();
                num.append(expression.charAt(i));
                while (i+1 < expression.length() && Character.isDigit(expression.charAt(i+1))) {
                    num.append(expression.charAt(++i));
                }
                result += Integer.parseInt(num.toString()) * sign;
            } else if(Character.isAlphabetic(expression.charAt(i))) {
                StringBuilder str = new StringBuilder();
                str.append(expression.charAt(i));
                while (i+1 < expression.length() && Character.isAlphabetic(expression.charAt(i+1))) {
                    str.append(expression.charAt(++i));
                }
                String key = str.toString();
                if (vars.containsKey(key)) {
                    result += vars.get(key) * sign;
                } else {
                    st2.push(key);
                    st2.push(sign == 1 ? "+" : "-");
                }
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
                if (!st2.isEmpty()) {
                    if (st.peek() == -1 && st2.peek().equals("+")) {
                        st2.pop();
                        st2.push("-");
                    }
                }
                result = result * st.pop() + st.pop();
            }
        }
        String finalResult = result + "";
        while(!st2.isEmpty()){
            finalResult += st2.pop() + st2.pop();
        }
        return finalResult;
    }

    public static void main(String[] args) {

        String expression = "(e+30)-(pressure+temperature)-foobar";
        Map<String, Integer> variables = new HashMap<>();
        variables.put("e", 8);
        variables.put("y", 7);
        variables.put("pressure", 5);

        System.out.println(eval(expression, variables));
    }
}
