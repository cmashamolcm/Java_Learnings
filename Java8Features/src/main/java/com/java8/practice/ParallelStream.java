package com.java8.practice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.BaseStream;
import java.util.stream.Stream;

public class ParallelStream {
    public static void main(String[] args) {
        List list = Arrays.asList(1, 2, 3, 4, 5);
        list.parallelStream().forEach(p-> System.out.println(p));// order not guaranteed

        Stream.of(1, 2, 3, 4, 5).parallel().forEach(p->System.out.println(Thread.currentThread().getName()));
        Stream.of(1, 2, 3, 4, 5).parallel().sequential().forEach(p->System.out.println(Thread.currentThread().getName()));
    }
}
