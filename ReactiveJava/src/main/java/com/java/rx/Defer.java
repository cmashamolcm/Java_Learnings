package com.java.rx;

import io.reactivex.rxjava3.core.Observable;

public class Defer {
    static int count = 10;
    public static void main(String[] args) {
        Observable<Integer> observable = Observable.range(0, count);
        observable.subscribe((p)-> System.out.println("s1: " + p));
        count = 20;// no effect for this change in s2.
        observable.subscribe((p)-> System.out.println("s2: " + p));

        count = 10;
        Observable<Integer> callableObservable = Observable.defer(()->Observable.range(0, count));
        callableObservable.subscribe((p)-> System.out.println("callable s1: " + p));
        count = 20;// no effect for this change in s2.
        callableObservable.subscribe((p)-> System.out.println("callable s2: " + p));

        Observable<Integer> customObservable = Observable.defer(()->Observable.create((emitter)->{
            emitter.onNext(count++);
            emitter.onNext(count++);
            emitter.onNext(count++);
        }));
        customObservable.subscribe((p)-> System.out.println("customObservable s1: " + p));
        count = 25;
        customObservable.subscribe((p)-> System.out.println("customObservable s2: " + p));
        System.out.println(count);
    }
}
