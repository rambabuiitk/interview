package karat;

public class BasicCalculator {

    public static int evaluateExpression(String expression) {
        if (expression == null || expression.isEmpty()) {
            return 0;
        }

        int result = 0;
        int sign = 1;
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
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(evaluateExpression("2+3-999"));
    }
}
