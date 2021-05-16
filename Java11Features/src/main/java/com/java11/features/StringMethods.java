package com.java11.features;

public class StringMethods {
    public static void main(String[] args) {
        String str = "A \n B\n C\n D    \n ";
        str.lines().forEach(System.out::println);
        System.out.println("Stripped");
        str.lines().map(s->s.strip()).forEach(System.out::println);//last empty space is also kept.

        System.out.println("IsBlank");
        str.lines().map(s->s.strip()).filter(s->!s.isBlank()).forEach(System.out::println);

        String blankSpaceString = "  ";
        System.out.println(blankSpaceString.isEmpty());
        System.out.println(blankSpaceString.isBlank());
        System.out.println("   ".strip().length());
    }
}
