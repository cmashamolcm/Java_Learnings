package com.java.rx;


import io.reactivex.rxjava3.core.Observable;

import java.util.List;

public class BasicObservable {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4,5, 6, 7, 8, 9,10).subscribe(System.out::println);

        Observable.fromIterable(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40)).subscribe(System.out::println);

        Observable.create(emitter->{
            emitter.onNext(1000);
            emitter.onNext(2000);
            emitter.onNext(3000);
            //1-emitter.onError(null);
            //indicates completion
            emitter.onComplete();

        })
                .subscribe(System.out::println, (p)-> System.out.println("Error: " + p), ()->{
                    System.out.println("Completed");// prints Completed when emitter.onComplete(); happens.
                });
        //1-.subscribe(System.out::println,(p)->{// will print error when emitter.onError(null); happens
        //    System.out.println("Error: ");
        //});
        //.subscribe();//no further action can be taken.
        //.subscribe(System.out::println);//can do something on subscribe
    }
}
