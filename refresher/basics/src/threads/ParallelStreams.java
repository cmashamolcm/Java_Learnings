package threads;

import java.util.*;

public class ParallelStreams {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 20, 8 ,9, 10));
        //list.parallelStream().forEach(System.out::println);

        //list.parallelStream().forEachOrdered(System.out::println);// slow as order matters.
        //list.parallelStream().sorted().forEach(System.out::println);// slow due to sorting as it affects all splits from source.

        HashSet<Integer> set = new HashSet<>(list);
        //set.parallelStream().forEach(System.out::println);// source is not ordered. So, splits also. Hence output also. But faster due to less complications on aggregation.
        Spliterator split = set.spliterator();
        while(split.tryAdvance((p-> System.out.println(p)))){
        }

    }
}
