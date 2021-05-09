package com.java.rx;

import io.reactivex.rxjava3.core.Observable;

public class DoOn {
    public static void main(String[] args) {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        observable
                .doOnSubscribe((disposable)-> System.out.println("Observable says: Started subscription."))
                .doOnNext((data)-> System.out.println("Observable: I have send data" + data))
                .doOnComplete(()-> System.out.println("Observable: I am done with my side."))
                .subscribe((p)-> System.out.println("Observer: Data reached observer : " + p),
                (error)-> System.out.println("Observer: Error reached observer: " + error),
                ()-> System.out.println("Observer: Done in observer side."));

        observable.doFinally(()->{
            System.out.println("Finally block");
        }).doOnSubscribe((disposable)->{throw new Exception("Error");})// Finally block comes first before printing error block comment.
                .subscribe((p)-> System.out.println("Next"), (e)-> System.out.println("Error"), ()-> System.out.println("Completed"));
    //Finally block get executed first and then goes to default exception handler if explicit catch is not there. If catch block is there, it goes to catch and then finally.
    //In above case..only finally is there...so finally works..then goes to error block.

    observable.doOnDispose(()->System.out.println("Dispose block"))
            .doOnSubscribe(disposable-> {// if this way its not given, once subscription is done, framework itself dispose the subscription. That time .dispose() will not be called and hence no call to .doOnDispose() happens.
                System.out.println("Disposing on subscribe");
                disposable.dispose();
            })
            .subscribe((p)-> System.out.println("Next"), (e)-> System.out.println("Error"), ()-> System.out.println("Completed"));

        observable
                .doOnSubscribe(disposable-> {// if this way its not given, once subscription is done, framework itself dispose the subscription. That time .dispose() will not be called and hence no call to .doOnDispose() happens.
                    System.out.println("Disposing on subscribe.");
                    disposable.dispose();
                })
                .doOnDispose(()->System.out.println("Dispose block order is after onSubscribe()"))// call will not get happen as onSubscribe already disposed it and chain of operations mappers here.
                .subscribe((p)-> System.out.println("Next"), (e)-> System.out.println("Error"), ()-> System.out.println("Completed"));
}
}
