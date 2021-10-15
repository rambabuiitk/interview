package numbersmath;

public class AddTwoStringsOfInteger {
    public static void main(String[] args) {
        String s1 = "99";
        String s2 = "11";
        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
        String output = addStrings(s1, s2);
        System.out.println("Output: " + output);
    }

    private static String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();

        int carry = 0;
        int p1 = num1.length() - 1;
        int p2 = num2.length() - 1;
        while (p1 >= 0 || p2 >= 0) {
            int x1 = p1 >= 0 ? num1.charAt(p1) - '0' : 0; // get the p1th digit from num1
            int x2 = p2 >= 0 ? num2.charAt(p2) - '0' : 0; // get the p2th digit from num2
            int value = (x1 + x2 + carry) % 10; // if sum is greater than 10 meaning we have to set carry and remainder
            carry = (x1 + x2 + carry) / 10;  // setting carry
            res.append(value); // adding the remainder to string
            p1--;
            p2--;
        }

        if (carry != 0)
            res.append(carry);

        return res.reverse().toString();
    }
}
