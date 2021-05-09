package com.java.rx;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class Interval {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> interval = Observable.interval(2, TimeUnit.SECONDS);// runs in separate thread. So, if main thread is given some delay only, we will be able to see the output.
        interval.subscribe((p)-> System.out.println("s1: Item = " + p));
        Thread.sleep(4000);
        interval.subscribe((p)-> System.out.println("s2: Item = " + p));// interval gives a cold observable as for all subscriptions, entire data comes.
        Thread.sleep(4000);

        //To make it hot.
        ConnectableObservable<Long> hotInterval = interval.publish();
        hotInterval.subscribe((p)-> System.out.println("hot s1: Item = " + p));
        hotInterval.connect();//only if this is there, data flows.
        Thread.sleep(2000);
        hotInterval.subscribe((p)-> System.out.println("hot s2: Item = " + p));// interval gives a cold observable as for all subscriptions, entire data comes.
        Thread.sleep(5000);
    }
}
