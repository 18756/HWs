package ru.itmo.wp.test;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        String s = String.valueOf(null);
        System.out.println(s.length());
        System.out.println(s);
    }

    public static class Message {
        public Message(String user, String text) {
            this.user = user;
            this.text = text;
        }
        String user;
        String text;
    }
}
