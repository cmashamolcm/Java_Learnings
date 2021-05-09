package com.java.rx;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class Concat {
    public static void main(String[] args) throws InterruptedException {
        Observable observable1 = Observable.interval(1, TimeUnit.MILLISECONDS)
                .map(p->"Observable-1: " + p)
                .take(5);// if it goes infinity, onComplete() is not invokes. So, in concat, observable2 will never get chance.
        Observable observable2 = Observable.interval(1, TimeUnit.SECONDS)
                .map(p->"Observable-2: " + p);

        //concat - order preserved
        //Observable.concat(observable1, observable2); is there
        observable1
                .concatWith(observable2)
                .subscribe((data)-> System.out.println("Concat result = " + data));

        //merge - order not preserved.
        observable1
                .mergeWith(observable2)
                .subscribe((data)-> System.out.println("Merge result = " + data));

        contactMap();

        Thread.sleep(10000);
    }

    private static void contactMap() {
        Observable observable1 = Observable.interval(1, TimeUnit.MILLISECONDS)
                .map(p->"Observable-1: " + p)
                .take(5);// if it goes infinity, onComplete() is not invokes. So, in concat, observable2 will never get chance.
        Observable observable2 = Observable.interval(1, TimeUnit.SECONDS)
                .map(p->"Observable-2: " + p);

        //order preserved compared to flatmap.
        //there is no static method for concatMap, flatmap, map.
        observable1.concatMap(data->Observable.just("ConcatMapped : " + data))
                .subscribe((data)-> System.out.println("ConcatMap result = " + data));

        //order not mandatory to preserved.
        observable1.flatMap(data->Observable.just("ConcatMapped : " + data))
                .subscribe((data)-> System.out.println("Flatmap result = " + data));

    }
}
