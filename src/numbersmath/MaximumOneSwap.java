package numbersmath;

public class MaximumOneSwap {
    public static void main(String[] args) {
        int num = 54367;
        System.out.println(maximumSwap(num));
    }

    // time: O(N) where N is total digits in given number
    private static int maximumSwap(int num) {
        char[] digits = ("" + num).toCharArray();
        int firstGreater = 0; //find the first digit greater than previous
        while (firstGreater < (digits.length - 1) && digits[firstGreater] >= digits[firstGreater + 1]) {
            firstGreater++;
        }
        firstGreater = firstGreater + 1; // get the element that is breaking the sequence.

        //all digits are reversed sorted, no swap needed
        if (firstGreater == digits.length) {
            return num;
        }

        char max = '0';
        int max_position = firstGreater;
        //find max digit in remain digits
        for (int i = firstGreater; i < digits.length; i++) {
            if (max <= digits[i]) {
                max = digits[i];
                max_position = i;
            }
        }

        //find first digit that smaller than max digit in the second part
        for (int k = 0; k < firstGreater; k++) {
            if (max > digits[k]) {
                StringBuilder s = new StringBuilder(String.valueOf(digits));
                s.setCharAt(max_position, digits[k]);
                s.setCharAt(k, max);
                return Integer.parseInt(s.toString());  //no need to check overflow since max value is 10^8
            }
        }

        return num;
    }
}
