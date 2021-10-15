package DesignAlgoJava;

import java.util.Arrays;
import java.util.Comparator;

public class NaturalSortingComparator implements Comparator<String> {
    public static void main(String[] args) {
        String[] strings = new String[]{"b", "a", "a1", "a10b3", "a2", "aa"};
        System.out.println("Input: " + Arrays.toString(strings));
        Arrays.sort(strings, new NaturalSortingComparator());
        System.out.println("Output: " + Arrays.toString(strings));
    }

    @Override
    public int compare(String a, String b) {
        int i = 0, j = 0;
//            while (i < a.length() && j < b.length()) {
        for (; i < a.length() && j < b.length(); i++, j++) {
            if (Character.isDigit(a.charAt(i))
                    && Character.isDigit(b.charAt(i))) {

                String sint_a = "" + a.charAt(i);
                String sint_b = "" + b.charAt(i);
                // combine all digits in sint_a
                while (i + 1 < a.length() && Character.isDigit(a.charAt(i + 1))) {
                    sint_a += a.charAt(i + 1);
                    i++;
                }
                // combine all digits in sint_b
                while (j + 1 < b.length() && Character.isDigit(b.charAt(j + 1))) {
                    sint_b += b.charAt(j + 1);
                    j++;
                }
                // if both string are same continue to next characters
                if (sint_a == sint_b) {
                    continue;
                }
                // else compare both the interger from string 1 and 2
                return Integer.parseInt(sint_a) - Integer.parseInt(sint_b);
            }

            if (a.charAt(i) == b.charAt(j)) {
                continue;
            }

            return a.charAt(i) - b.charAt(j);
        }
        return a.length() - i;
    }
}
