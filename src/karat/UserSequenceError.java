package karat;

/**
 * Given a collection of actions and userIds an error occurs when a userId takes a specific action in order for example
 *
 * A => B => => C gives an errror
 * B => A => C no error and etc
 *
 * Write a function that takes in a list of (Actions, UserIds) pairs and returns the user Id that encounters the error
 *
 * Sample Input:
 *
 * action_user_1 = [
 * ["A", "1"],
 * ["B", "1"],
 * ["B", "2"],
 * ["C", "1"],
 * ["C", "2"],
 * ["C", "3"],
 * ["A", "2],
 * ["A", "3"],
 * ["A", "2"],
 * ["B", "2],
 * ["C", "2"],
 * ]
 *
 * Expected output 1,2
 *
 * action_user_2 = [
 * ["A", "1"],
 * ["A", "1"]
 * ["A", "1"]
 * ["B", "1"],
 * ["B", "2"],
 * ["C", "2"],
 * ["C", "2"],
 * ["C", "3"],
 * ["A", "2],
 * ["A", "3"],
 * ["A", "2"],
 * ["B", "2],
 * ["C", "2"],
 * ]
 *
 * Expected output 2
 */

import java.util.*;

public class UserSequenceError {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    static void CheckForError(String[][] actL, String str) {

        Map<String, List<String>> map = new HashMap<String, List<String>>();

        for(String[] row:actL) {
            List<String> charList = map.getOrDefault(row[1], new ArrayList<String>());
            charList.add(row[0]);
            map.put(row[1], charList);
        }

        for(String strVal: map.keySet()) {
            StringBuilder sb=new StringBuilder(map.get(strVal).size());
            for(String ch:map.get(strVal)){
                sb.append(ch);
            }
            if(sb.indexOf(str)>=0)
                System.out.println(strVal);
        }
    }

}

