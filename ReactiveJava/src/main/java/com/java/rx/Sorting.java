package com.java.rx;

import io.reactivex.rxjava3.core.Observable;

import java.util.Comparator;
import java.util.function.Function;

public class Sorting {
    public static void main(String[] args) {
        Observable<Integer> observable = Observable.range(1, 10).map(p->10/p);
        observable
                .sorted()
                .subscribe(System.out::println);

        //its creating a fresh sorted observable stream. not changing actual one.
        observable
                .subscribe(System.out::println);

        Observable.range(1, 10).sorted(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2.compareTo(o1);
                    }
                }
        ).subscribe(p->System.out.println("Reversed Data = " + p));
        //above is same as

        Observable.range(1, 10).sorted((a, b)->b.compareTo(a))
                .subscribe(p->System.out.println("Reversed by lambda Data = " + p));

        Observable.just(new Node(1, "one"), new Node(1, "tw"), new Node(3, "three"), new Node(4, "four"))
                .sorted(Comparator.comparing(Node::getData)
                        //.thenComparing(b->b.getName().length())// its checks for data and then checks for length of name. Thats why tw comes before one.
                        .thenComparing(b->b.getName().length(), Comparator.reverseOrder())//This will print one first and then two. It checks length() reverse order.
                )
        .subscribe(p->System.out.println("comparing: data then name = " + p.name));
    }

    private static class Node{
        int data;
        String name;

        public Node(int data, String name) {
            this.data = data;
            this.name = name;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
