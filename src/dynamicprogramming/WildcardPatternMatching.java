package dynamicprogramming;

public class WildcardPatternMatching {
    public static void main(String[] args) {
        String input = "adceb";
        String pattern = "a*c?b"; // c*a*b will return false
        System.out.println("input: " + input);
        System.out.println("pattern: " + pattern);
        WildcardPatternMatching sre = new WildcardPatternMatching();
        boolean output = sre.isMatchLinearTime(input, pattern);
        System.out.println("dp using recursion: " + output);
        output = sre.isMatchUsingBottomUp(input, pattern);
        System.out.println("dp using bottomup: " + output);
        output = sre.isMatchLinearTime(input, pattern);
        System.out.println("dp using linear time: " + output);
    }

    // time: O(M * N))
    private static boolean isMatchUsingBottomUp(String text, String pattern) {
        boolean[][] match = new boolean[text.length() + 1][pattern.length() + 1];
        match[text.length()][pattern.length()] = true;
        for (int i = pattern.length() - 1; i >= 0; i--) {
            if (pattern.charAt(i) != '*') {
                break;
            } else {
                match[text.length()][i] = true;
            }
        }
        for (int i = text.length() - 1; i >= 0; i--) {
            for (int j = pattern.length() - 1; j >= 0; j--) {
                if (text.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '?') {
                    match[i][j] = match[i + 1][j + 1];
                } else if (pattern.charAt(j) == '*') {
                    match[i][j] = match[i + 1][j] || match[i][j + 1];
                } else {
                    match[i][j] = false;
                }
            }
        }
        return match[0][0];
    }


    // *** If (*s==*p or *p == '.') --> this is match, then goes to next element s++ p++.
    // *** If (p=='*') --> this is match, but one or many chars may be available,
    // so let us save this *'s position and the matched s position.
    // *** If not match, then we check if there is a * previously showed up,
    //       if there is no *,  return false;
    //       if there is a *,  we set current p to the next element of *,
    //       and set current s to the next saved s position.
    // time: O(min (M,N))
    private static boolean isMatchLinearTime(String text, String pattern) {
        int s = 0;
        int p = 0;
        int match = 0;
        int starIdx = -1;
        while (s < text.length()) {
            // advancing both pointers
            if (p < pattern.length()
                    && (pattern.charAt(p) == '?'
                    || text.charAt(s) == pattern.charAt(p))) {
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*') {
                starIdx = p; // storing the star location
                match = s; // storing the last matched location for string s
                p++; // moving the next pointer in pattern
            }
            // check if there was a '*' previously showed up,
            else if (starIdx != -1) { // else if last pointer is * we can move to next as
                p = starIdx + 1; // move to next of the star and
                match++;
                s = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else {
                return false;
            }
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*') {
            p++;
        }
        // if we reach end of pattern return true else false
        return p == pattern.length();
    }

}
