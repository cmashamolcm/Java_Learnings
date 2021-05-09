package com.java.rx;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;

public class SingleMayBeCompletable {
    public static void main(String[] args) {
        Single<Integer> single = Single.just(1);
        single.subscribe(p-> System.out.println(p), (e)-> System.out.println(e));
        single.subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("Single subscribed");
            }

            @Override
            public void onSuccess(@NonNull Integer integer) {
                System.out.println("Single success");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("Single error");
            }
        });

        Maybe<Integer> maybe = Maybe.just(1);
        maybe.subscribe(p-> System.out.println(p), (e)-> System.out.println(e), ()-> System.out.println("Done"));
        maybe.subscribe(new MaybeObserver<>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("Subscribed may be");
            }

            @Override
            public void onSuccess(@NonNull Integer integer) {
                System.out.println("Success may be");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("Error may be");
            }

            @Override
            public void onComplete() {
                System.out.println("Completed may be");
            }
        });

        Completable.fromSingle(single).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("Complete subscribed");
            }

            @Override
            public void onComplete() {
                System.out.println("Complete completed");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("Complete error");
            }
        });

    }
}
