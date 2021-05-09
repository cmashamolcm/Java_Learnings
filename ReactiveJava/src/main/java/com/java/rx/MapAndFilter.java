package com.java.rx;

import io.reactivex.rxjava3.core.Observable;

public class MapAndFilter {
    public static void main(String[] args) {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        observable.subscribe((data)-> System.out.println("Final outcomes: " + data));

        observable.map(data->data * 100).subscribe((data)-> System.out.println("Final outcomes * 100: " + data));
        //evens
        observable.filter(data->data%2==0).subscribe((data)-> System.out.println("Final outcomes even numbers only: " + data));

        observable.filter(data->data%2==0).map(data->data * 100).subscribe((data)-> System.out.println("Final outcomes even * 100: " + data));


    }
}
