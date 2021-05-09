package com.java.rx;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class BasicObserver {
    public static void main(String[] args) {
        Observable.create(new MyObservableEmitter())
                .subscribe(new MyObserver());
    }

    private static class MyObservableEmitter implements ObservableOnSubscribe<Integer>{

        @Override
        public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
            emitter.onNext(1);
            emitter.onComplete();
        }
    }

    private static class MyObserver implements Observer<Integer>{

        @Override
        public void onSubscribe(@NonNull Disposable d) {

        }

        @Override
        public void onNext(@NonNull Integer integer) {
            System.out.println("Next: " + integer);
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {
            System.out.println("Completed");
        }
    }
}
