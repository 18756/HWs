package ru.itmo.wp.test;

public class Test {
    public static void main(String[] args) {
        Object[] list = new Object[]{1, 2L, "sa"};
        System.out.println(list[0].getClass().getName());
        System.out.println(list[1].getClass().getName());
        System.out.println(list[2].getClass().getName());
    }
}
