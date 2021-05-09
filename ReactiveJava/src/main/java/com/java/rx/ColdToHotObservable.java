package com.java.rx;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

public class ColdToHotObservable {
    public static void main(String[] args) throws InterruptedException {
        Observable<Integer> cold = Observable.just(1, 2, 3, 4, 5);
        createColdObservables(cold);
        createHotObservables(cold);//from cold to hot. ie; connectable observables

}
    //just .subscribe() is not enough here. Make it ConnectableObservable and try.
    private static void createHotObservables(Observable<Integer> cold) throws InterruptedException {
        /*Observable*/ ConnectableObservable<Integer> hot = cold.publish();
        hot.subscribe((p)->{
            System.out.println("Hot Subscriber 1: " + p);
        });

        //Thread.sleep(3000);
        hot.connect();// emission starts only when connect() is called. All subscribers so far will get the data.

        hot.subscribe((p)->{
            System.out.println("Hot Subscriber 2: " + p);
        });
    }

    private static void createColdObservables(Observable<Integer> cold) throws InterruptedException {
        cold.subscribe((p)->{
            System.out.println("Cold Subscriber 1: " + p);
        });
        Thread.sleep(3000);
        cold.subscribe((p)->{
            System.out.println("Cold Subscriber 2: " + p);
        });
    }
    //cold gives data to both subscribers.
    }
