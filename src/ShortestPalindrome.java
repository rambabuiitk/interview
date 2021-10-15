public class ShortestPalindrome {
    public static String shortestPalindrome(String s) {
        String stdash = s + "#" + new StringBuilder(s).reverse().toString();
        int lps = LPS(stdash);
        System.out.println(s.substring(lps));
        String ans = new StringBuilder(s.substring(lps)).reverse().toString() + s;
        return ans;
    }

    public static int LPS(String st){
        System.out.println(st);
        int[] lps = new int[st.length()];
        int len = 0;
        int i = 1;
        while(i < st.length()){
            if(st.charAt(i) == st.charAt(len)){
                len++;
                lps[i] = len;
                i++;
            } else {
                if(len > 0){
                    len = lps[len - 1];
                } else {
                    i++;
                }
            }
        }
        for (int lp:lps)
        System.out.print(lp + " ");
        return lps[lps.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(shortestPalindrome("abcd"));
    }
}