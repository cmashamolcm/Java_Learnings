package com.java8.practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringPools {
    public static void main(String[] args) {
        String str1 = "Hello";
        System.out.println(str1.hashCode());
        String str2 = new String("Hello");
        System.out.println(str2.hashCode());

        String str3 = new String("Hello").intern();
        System.out.println(str3.hashCode());

        //find no: of times a string exists.
        String str4 = "hello...hello...everybody...hello";
        Pattern pattern = Pattern.compile("hello");
        Matcher matcher = pattern.matcher(str4);
        int counter = 0;
        while (matcher.find()){
            counter++;
        }
        System.out.println(counter);

        str1.substring(str1.length());// no error but just empty string.
        //System.out.println(str1 + str2 + str3 +str4);//internally uses builder now.

        System.out.println(str1.concat(str2));//not using builder here.
    }
}
