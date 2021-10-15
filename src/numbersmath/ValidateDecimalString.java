package numbersmath;

public class ValidateDecimalString {
    public static void main(String[] args) {
        String s = "6e-1";
        System.out.println("Input: " + s);
        boolean output = isNumber(s);
        System.out.println("Output: " + output);

        s = "99e2.5";
        System.out.println("Input: " + s);
        output = isNumber(s);
        System.out.println("Output: " + output);

        s = "0";
        System.out.println("Input: " + s);
        output = isNumber(s);
        System.out.println("Output: " + output);
    }

    private static boolean isNumber(String s) {
        // [-+]?(([0-9]+(.[0-9]*)?)|.[0-9]+)(e[-+]?[0-9]+)?
        s = s.trim();
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        for (int i = 0; i < s.length(); i++) {
            if ('0' <= s.charAt(i) && s.charAt(i) <= '9') { // [0-9]
                // if we see a number in
                numberSeen = true;
            } else if (s.charAt(i) == '.') {
                // if e is before '.' or there is another '.'
                if (eSeen || pointSeen) { // e.3 or 4.21.2
                    return false;
                }
                // if point is seen for first time
                pointSeen = true;
            } else if (s.charAt(i) == 'e') {
                //if we have already seen e or there is no number before
                if (eSeen || !numberSeen) { // 4e2e or e1
                    return false;
                }
                // mark number seen as false
                // so that we can validate at least 1 number after e
                numberSeen = false;
                // if e is seen for first time
                eSeen = true;
            } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                // if the we find a - or + in middle if previous char is not e
                if (i != 0 && s.charAt(i - 1) != 'e') { // 1-5 or ++1
                    return false;
                }
            } else { // if character is not any one from above
                return false;
            }
        }
        return numberSeen;
    }
}
