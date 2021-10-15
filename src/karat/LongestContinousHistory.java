package karat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestContinousHistory {

    public static List<String> longestCommonContinuousHistory(List<String> history1, List<String> history2) {
        if (history1 == null ||
                        history2 == null ||
                        history1.isEmpty() ||
                        history2.isEmpty()) {
            return new ArrayList<>();
        }

        int count = -1;
        List<String> result = new ArrayList<>();
        int[][] memo = new int[history1.size()+1][history2.size()+1];
        for (int i = 1; i <= history1.size(); i++) {
            for (int j = 1; j <= history2.size(); j++) {
                if (history1.get(i - 1).equals(history2.get(j - 1))) {
                    memo[i][j] = 1 + memo[i - 1][j - 1];
                    if (memo[i][j] > count) {
                        count = memo[i][j];
                        result = history1.subList(i - count, i);
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[] history1 = {"3234.html", "xys.html", "7hsaa.html"}; // user1
        String[] history2 = {"3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"}; // user2

        System.out.println(longestCommonContinuousHistory(Arrays.asList(history1), Arrays.asList(history2)));
    }
}
