package karat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordProcessor {

    public static List<String> reflowAndJustify(List<String> lines, int maxLen) {
        if (lines == null || lines.size() == 0) {
            return new ArrayList<>();
        }

        String[] wordsArr = String.join(" ", lines).split(" ");
        List<String> words = Arrays.asList(wordsArr);
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

            List<String> lineWords = words.subList(i-count, i);
            if (lineWords.size() == 1) {
                result.add(lineWords.get(0));
            } else {
                int wordsLength = lineWords.stream().map(w -> w.length()).reduce((x, y) -> x + y).get();
                String baseDash = "-".repeat((maxLen - wordsLength) / (lineWords.size() - 1));
                int extraLength = (maxLen - wordsLength) % (lineWords.size() - 1);
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < lineWords.size(); j++) {
                    if (j == lineWords.size() - 1) {
                        line.append(lineWords.get(j));
                    } else {
                        line.append(lineWords.get(j)).append(baseDash);
                        if (extraLength-- > 0) {
                            line.append("-");
                        }
                    }
                }
                result.add(line.toString());
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[] lines = { "The day began as still as the",
                "night abruptly lighted with",
                "brilliant flame" };

        System.out.println(reflowAndJustify(Arrays.asList(lines), 24));
    }
}
