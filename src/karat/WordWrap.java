package karat;

import java.util.ArrayList;
import java.util.List;

public class WordWrap {

    public List<String> wordWrap(List<String> words, int maxLen) {
        if (words == null || words.size() == 0) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        int i =0;
        while (i<words.size()) {
            int remain = maxLen;
            int count = 0;
            while (i<words.size()) {
                if (remain - words.get(i).length() < 0) {
                    break;
                }
                count++;
                remain -= words.get(i).length() + 1;
                i++;
            }
            result.add(String.join(" ", words.subList(i-count, i)));
        }

        return result;
    }
}
