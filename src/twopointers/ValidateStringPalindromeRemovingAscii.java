public class ValidateStringPalindromeRemovingAscii {
    public static void main(String[] args) {
        String a = "A man, nam:a";
        System.out.println("String : " + a);
        System.out.println("Output: " + isPalindrome(a));
    }

    static boolean isPalindrome(String s) {// better
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            // move to right we find valid character
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // move to left until we find valid character
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            // validate if both valid characters are same
            if (left < right && Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


}