package com.java.rx;

import io.reactivex.rxjava3.core.Observable;
//Lazy stream creation
public class CallableObservable {
    public static void main(String[] args) throws Exception {
        // here, even without subscribe() call, "Creating stream beforehand." will get printed in console.
        Observable<Integer> normal = Observable.just(getData());

        //here, only on subscribe, printing of "Creating stream beforehand." happens.
        Observable<Integer> callable = Observable.fromCallable(()->{
            System.out.println("Creating stream beforehand.2");
            return 10000;
        });
        //callable.subscribe();

        //If getData() was throwing an error, still onError() will not work...but simply exception happens.
        //In lazy callable, onError() does its job.
        //normal = Observable.just(getErrorData());//----console logs full stack trace

        //whereas
        callable = Observable.fromCallable(()->{
            System.out.println("Creating stream beforehand.2");
            throw new Exception("Error on stream creation lazy 2");
        });
        //console logs only proper message. "Lazy error caught Error on stream creation lazy 2"
        callable.subscribe(System.out::println, (error)-> System.out.println("Lazy error caught" + error.getLocalizedMessage()));
    }

    private static int getErrorData() throws Exception {
        throw new Exception("Normal got error on stream creation.");
    }

    private static int getData() {
        System.out.println("Creating stream beforehand.1");
        return 10000;
    }
}
