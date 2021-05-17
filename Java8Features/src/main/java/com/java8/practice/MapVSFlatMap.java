package com.java8.practice;

import java.util.Arrays;
import java.util.stream.Stream;

public class MapVSFlatMap {
    public static void main(String[] args) {
        Arrays.asList(1, 2, 3, 4, 5).stream().map(p->p.intValue()).forEach(System.out::println);
        Arrays.asList(1, 2, 3, 4, 5).stream().flatMap(p-> Stream.of(p.intValue())).forEach(System.out::println);
        Arrays.asList(Stream.of(1, 2, 3), Stream.of(10, 20, 30), Stream.of(100, 200, 300))
                .stream().flatMap(p->p).forEach(System.out::println);// stream is the response from flatmap.
    }
}
