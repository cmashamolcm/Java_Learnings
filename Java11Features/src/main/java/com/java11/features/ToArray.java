package com.java11.features;

import java.util.Arrays;
import java.util.List;

public class ToArray {
    public static void main(String[] args) {
        Integer[] array = List.of(1, 2, 3, 4, 5).toArray(new Integer[]{});
        System.out.println(array);

        Arrays.asList(array).stream().forEach(System.out::println);
    }
}
