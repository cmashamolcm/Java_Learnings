package com.java.rx;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class Delay {
    public static void main(String[] args) throws InterruptedException {
        Observable.just(1, 2, 3, 4).delay(3, TimeUnit.SECONDS)
                .subscribe((p)-> System.out.println(p));

        Observable.interval(3, TimeUnit.SECONDS)
                .subscribe((p)-> System.out.println(p));

        Thread.sleep(10000);
    }
}
