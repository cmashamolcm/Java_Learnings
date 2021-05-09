package com.java.rx;

import io.reactivex.rxjava3.core.Observable;

public class Contains {
    public static void main(String[] args) {
        Observable.just(1, 2, 3).contains(10).subscribe(p-> System.out.println(p));
    }
}
