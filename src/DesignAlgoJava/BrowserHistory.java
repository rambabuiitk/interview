package DesignAlgoJava;

import java.util.LinkedList;

public class BrowserHistory {

    public static void main(String[] args) {
        BrowserHistory bh = new BrowserHistory("interviewdaemon.com");
        bh.visit("google.com");
        System.out.println("bh.visit(\"google.com\")");
        bh.visit("facebook.com");
        System.out.println("bh.visit(\"facebook.com\")");
        bh.visit("youtube.com");
        System.out.println("bh.visit(\"youtube.com\")");
        System.out.println("bh.back(1): " + bh.back(1));
        System.out.println("bh.back(1): " + bh.back(1));
        System.out.println("bh.forward(1): " + bh.forward(1));
        bh.visit("youtube.com");
        System.out.println("bh.forward(2): " + bh.forward(2));
        System.out.println("bh.back(2): " + bh.back(2));
        System.out.println("bh.back(7): " + bh.back(7));
    }

    LinkedList<String> list;
    int index = 0;

    BrowserHistory(String homepage) {
        list = new LinkedList<>();
        list.addLast(homepage);
    }

    public void visit(String url) {
        list.subList(index + 1, list.size()).clear();
        list.addLast(url);
        index++;
    }

    public String back(int steps) {
        index = index - steps;
        if (index < 0) {
            index = 0;
        }
        return list.get(index);
    }

    public String forward(int steps) {
        index = index + steps;
        if (index >= list.size()) {
            index = list.size() - 1;
        }
        return list.get(index);
    }
}
