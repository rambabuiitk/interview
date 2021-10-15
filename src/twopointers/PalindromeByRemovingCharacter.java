public class PalindromeByRemovingCharacter {
    public static void main(String[] args) {
        String input = "abca";
        System.out.println("Input: " + input);
        boolean output = possibleToRemoveChar(input);
        System.out.println("Output: " + output);
    }

        private static boolean possibleToRemoveChar(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            // If both characters are equal then move both pointer towards end
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {

                /*  If removing s[start] makes the whole string palindrome.
                We basically check if substring s[start+1..end] is
                palindrome or not. */
                if (isPalindrome(s, start + 1, end)) {
                    // here start is the index which can be removed
                    return true;
                }
                /*  If removing s[end] makes the whole string palindrome
                We basically check if substring s[start+1..end] is
                palindrome or not. */
                // here end is the index which can be removed
                return isPalindrome(s, start, end - 1);
            }
        }
        //  We reach here when complete string will be palindrome
        //  if complete string is palindrome then return mid character
        return true;
    }

    // simple palindrome check for a string.
    private static boolean isPalindrome(String str, int start, int end) {
        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
