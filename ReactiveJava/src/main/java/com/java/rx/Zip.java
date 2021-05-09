package com.java.rx;

import io.reactivex.rxjava3.core.Observable;

import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

//Process each element one by one.
public class Zip {
    public static void main(String[] args) throws InterruptedException {
        Observable<Integer> observable1 = Observable.just(1, 2);
        Observable<Integer> observable2 = Observable.just(10, 20, 30);
        Observable.zip(observable1, observable2, (a, b)->a+b).subscribe((data)-> System.out.println("Zipped add = " + data));

        Observable.zip(List.of(observable1, observable2), (array)-> Arrays.toString(array)).subscribe((data)-> System.out.println("Zipped add list = " + data));

        Observable.zipArray((array)-> Arrays.toString(array),true, 3, new Observable[]{observable1, observable2}).subscribe((data)-> System.out.println("Zipped array = " + data));

        observable1.zipWith(observable2, (a,b)->a*b).subscribe((data)-> System.out.println("Zip with = " + data));

        Observable.interval(1, TimeUnit.SECONDS).zipWith(Observable.just(1, 2, 3, 4, 5), (a,b)->a+b).subscribe((data)-> System.out.println("Infinite zip add = " + data));//0+1, 1+2, 2+3, 3+4, 4+5.

        Thread.sleep(10000);
    }
}
