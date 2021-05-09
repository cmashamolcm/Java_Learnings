package com.java.rx;

import io.reactivex.rxjava3.core.Observable;

public class EmptyAndNever {
    public static void main(String[] args) {
        Observable<Integer> empty = Observable.empty();
        empty.subscribe(System.out::println, System.out::println, ()-> System.out.println("Completed"));

        Observable<Integer> never = Observable.never();
        never.subscribe(System.out::println, System.out::println, ()-> System.out.println("Completed"));
    }
}
