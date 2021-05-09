package com.java.rx;

import io.reactivex.rxjava3.core.Observable;

public class ErrorHandling {
    private static int counter;

    public static void main(String[] args) throws InterruptedException {
        //onError();
        retry();
    }

    private static void retry() throws InterruptedException {
        Observable
                //.just(getData())//if we give getData() directly without callable, it will get invoked eager and error comes.
                .fromCallable(()->getData())
                .retry(3)// default max of long times
                .subscribe((p)-> System.out.println(p), (e)-> System.out.println(e.getMessage()));

        Observable.fromCallable(()->getData())//retry till predicate returns true
                .retry(error->{
                    System.out.println("Error came...retrying on predicate for counter :" + error.getMessage() + " \tcounter = " + counter);
                            return counter != 10;
                })
                .subscribe((p)-> System.out.println(p), (e)-> System.out.println(e.getMessage()));

        Observable.fromCallable(()->getData())//retry till predicate returns false
                .retryUntil(()->{
                    System.out.println("Error came...retrying until counter <: " + counter);
                    return counter >= 150;
                })
                .subscribe((p)-> System.out.println(p), (e)-> System.out.println(e.getMessage()));

        Thread.sleep(10000);
    }

    private static void onError() {
        //normal
        Observable.fromCallable(()->getData())
                .subscribe((p)-> System.out.println(p), (e)-> System.out.println(e.getMessage()));

        Observable.fromCallable(()->getData())
                .doOnError(e-> System.out.println("Did print on error: " + e.getMessage()))
                .subscribe((p)-> System.out.println(p), (e)-> System.out.println(e.getMessage()));

        Observable.fromCallable(()->getData())
                .onErrorReturn(error->{return Integer.MIN_VALUE;})//returns min on error
                .subscribe((p)-> System.out.println(p), (e)-> System.out.println(e.getMessage()));

        Observable.fromCallable(()->getData())
                .onErrorResumeNext(e-> {
                    System.out.println("Error came...so returning new Observable. 2000");// similar to switchIfEmpty
                    return Observable.just(2000);
                })
                .subscribe((p)-> System.out.println(p), (e)-> System.out.println(e.getMessage()));

        Observable.fromCallable(()->getData())
                .onErrorReturnItem(3000)
                .subscribe((p)-> System.out.println(p), (e)-> System.out.println(e.getMessage()));
    }

    private static Integer getData() {
        counter++;
        System.out.println("Get data called.");
        throw new ArithmeticException("Getting data: error occurred.");
    }
}
