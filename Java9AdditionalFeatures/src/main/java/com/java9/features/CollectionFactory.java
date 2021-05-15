package main.java.com.java9.features;

import java.util.List;
import java.util.Optional;

public class CollectionFactory {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        list.stream().forEach(p -> System.out.println(p));
        System.out.println("TAKE");
        list.stream().takeWhile(p->p<4).forEach(p -> System.out.println(p));
        System.out.println("DROP");
        list.stream().dropWhile(p->p<4).forEach(p -> System.out.println(p));
        System.out.println("OPTIONAL OF STREAM");
        Optional.of(1).stream().forEach(p -> System.out.println(p));
    }
}
