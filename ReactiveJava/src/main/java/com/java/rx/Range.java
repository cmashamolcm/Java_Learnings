package com.java.rx;

import io.reactivex.rxjava3.core.Observable;

public class Range {
    public static void main(String[] args) {
        Observable.range(5, 10).subscribe(System.out::println);
    }
}
