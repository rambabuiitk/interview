
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TelephoneNumbers {

    public static void main(String[] args) {
        String input = "35";
        List<String> output = letterCombinations(input);
        System.out.println("Output : " + output);
    }

    private static HashMap<Character, char[]> createDict() {
        HashMap<Character, char[]> dict = new HashMap<>();
        dict.put('2', new char[]{'a', 'b', 'c'});
        dict.put('3', new char[]{'d', 'e', 'f'});
        dict.put('4', new char[]{'g', 'h', 'i'});
        dict.put('5', new char[]{'j', 'k', 'l'});
        dict.put('6', new char[]{'m', 'n', 'o'});
        dict.put('7', new char[]{'p', 'q', 'r', 's'});
        dict.put('8', new char[]{'t', 'u', 'v'});
        dict.put('9', new char[]{'w', 'x', 'y', 'z'});
        return dict;
    }

    private static List<String> letterCombinations(String digits) {
        HashMap<Character, char[]> dict = createDict();
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        char[] arr = new char[digits.length()];
        helper(digits, 0, dict, result, arr);
        return result;
    }

    private static void helper(String digits, int index, HashMap<Character, char[]> dict,
                               List<String> result, char[] arr) {
        if (index == digits.length()) {
            result.add(new String(arr));
            return;
        }

        char number = digits.charAt(index);
        char[] options = dict.get(number);
        for (int i = 0; i < options.length; i++) {
            arr[index] = options[i];
            helper(digits, index + 1, dict, result, arr);
        }
    }

}
