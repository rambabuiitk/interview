package numbersmath;

public class CalculatorWithString {
    public static void main(String[] args) {
        System.out.println("'100 * 2 + 12' = " + basicCalculator("100 * 2 + 12"));
        System.out.println("'10*3+6/2' = " + basicCalculator("10*3+6/2"));
        System.out.println("'-1+2' = " + basicCalculator("-1+2"));
    }

    private static int basicCalculator(String s) {
        int sum = 0;
        int tempSum = 0;
        int num = 0;
        char lastSign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }

            if (i == s.length() - 1 || (!Character.isDigit(c) && c != ' ')) {
                // here are we are checking lastSign and not the current sign
                switch (lastSign) {
                    case '+':
                        sum = sum + tempSum;
                        tempSum = num;
                        break;
                    case '-':
                        sum = sum + tempSum;
                        tempSum = -num;
                        break;
                    case '*':
                        tempSum = tempSum * num;
                        break;
                    case '/':
                        tempSum = tempSum / num;
                        break;
                }
                lastSign = c;
                num = 0;
            }
        }
        sum = sum + tempSum;
        return sum;
    }
}
