package com.java.rx;

import io.reactivex.rxjava3.core.Observable;

import java.util.List;
import java.util.concurrent.TimeUnit;
//Simply attaches one after the other.
public class Merge {
    public static void main(String[] args) throws InterruptedException {
        Observable observable1 = Observable.just(1, 2, 3, 4, 5);
        Observable observable2 = Observable.just(10, 20, 30, 40, 50);
        Observable.merge(observable1, observable2).subscribe((data)-> System.out.println("Data = " + data));

        Observable.merge(observable2, observable1).subscribe((data)-> System.out.println("Data reverse= " + data));

        Observable observable3 = Observable.just(100, 200, 300, 400, 500);
        Observable.mergeArray(observable3, observable2, observable1).subscribe((data)-> System.out.println("Data merge array= " + data));

        List<Observable<Integer>> list = List.of(observable1, observable2, observable3);//order matters
        Observable.merge(list).subscribe((data)-> System.out.println("Data merge list= " + data));

        observable1.mergeWith(observable3).mergeWith(observable2).subscribe((data)-> System.out.println("Data merge with one item at a time= " + data));

        Observable.interval(1, TimeUnit.SECONDS)
                .map(data->data + " from 1st interval")
                .mergeWith(Observable.interval(2, TimeUnit.SECONDS)
                        .map(data->data + " from 2nd interval"))
                .subscribe((data)-> System.out.println("Infinite loop merging= " + data));// order depends on thread processing.

        Thread.sleep(10000);

    }
}
