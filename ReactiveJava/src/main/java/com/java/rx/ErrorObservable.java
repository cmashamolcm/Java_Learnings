package com.java.rx;

import io.reactivex.rxjava3.core.Observable;

public class ErrorObservable {
    public static void main(String[] args) {
        Observable<Exception> error = Observable.error(new Exception("Custom error 1"));
        error.subscribe(System.out::println, p->{
            System.out.println("Error : " + p.getMessage() + p.hashCode());
        });

        error.subscribe(System.out::println, p->{
            System.out.println("Error : " + p.getMessage() + p.hashCode());
        });

        //same hashcode for both cases above.

        Observable<Exception> callableError = Observable.error(()->new Exception("Custom error 2"));
        callableError.subscribe(System.out::println, p->{
            System.out.println("Error : " + p.getMessage() + p.hashCode());
        });

        callableError.subscribe(System.out::println, p->{
            System.out.println("Error : " + p.getMessage() + p.hashCode());
        });

    }
}
