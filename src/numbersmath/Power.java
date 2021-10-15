package numbersmath;

public class Power {
    public static void main(String[] args) {
        double x = 2;
        int y = 10;
        System.out.println("X: " + x);
        System.out.println("X: " + y);
        double output = myPowUsingConstantSpace(x, y);
        System.out.println("Using Constant Space: " + output);

        output = myPow(x, y);
        System.out.println("Using Recursive Call: " + output);
    }

    // time: O(log N) where N is n which is the power
    // space: O(1) 
    private static double myPowUsingConstantSpace(double x, int n) {
        // if n is negative we need to make it positive
        // 2^-2 == 1/(2^2)
        long N = n;
        if (n < 0) {
            N = -N;
            x = 1 / x;
        }
        double ans = 1;
        double current_product = x;
        // x^(a+b) = x^a * x^b
        for (long i = N; i > 0; i = i/2) {
            if ((i % 2) == 1) {
                ans = ans * current_product;
            }
            current_product = current_product * current_product;
        }
        return ans;
    }

    // Recursive
    // time: O(log N) where N is n which is the power
    // space: O(log N) recursive stack calls
    private static double myPow(double x, int n) {
        // if n is negative we need to make it positive
        // 2^-2 == 1/(2^2)
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        double res = calc(x, n);
        return res;
    }

    private static double calc(double x, long n) {
        if (n == 0) { // x^0 = 1
            return 1;
        } else if (n == 1) { // because x^1 = x
            return x;
        }
        // recursively calculate power(x, n/2)
        double val = calc(x, n / 2);
        if (n % 2 == 0) { // if n was even
            return val * val;  // meaning x^4 = x^2 * x^2
        } else { // if n was odd
            return val * val * x;  // meaning x^5 = x^2 * x^2 * x
        }
    }

}
