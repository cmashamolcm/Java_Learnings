package com.java.rx;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.ResourceObserver;

import java.util.concurrent.TimeUnit;

public class Dispose {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);
        Disposable disposable1 = observable.subscribe((p) -> System.out.println("s1: " + p));
        Thread.sleep(2000);
        disposable1.dispose();// subscription gets stopped here.
        Thread.sleep(2000);
        //But .subscribe(Observer) returns void. Then, how can we dispose the object?
        observable.subscribe(new Observer<Long>() {
            Disposable disposable2;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                this.disposable2 = d;
            }

            @Override
            public void onNext(@NonNull Long aLong) {
                System.out.println("Next in progress with value : " + aLong);
                if (aLong == 5) {
                    disposable2.dispose();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("Done");
            }
        });
        Thread.sleep(10000);// delay main thread, else, result of disposable2 will be missed. But terminates when 5 comes.

        //Is there anyway, I can externalize use of dispose() on Observer.
        //Can use ResourceObserver which implements Disposable as well.
        ResourceObserver<Long> resourceObserver = new ResourceObserver<Long>() {// onSubscribe() as its of no use as it returns disposable.
            @Override
            public void onNext(@NonNull Long aLong) {
                System.out.println("Resource Observable : " + aLong);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("Done resource observable");
            }
        };
        ResourceObserver<Long> disposable3 = observable.subscribeWith(resourceObserver);
        Thread.sleep(3000);
        disposable3.dispose();
        Thread.sleep(2000);
        //But disposing each subscriber is not so easy.
        //To handle it in one go.
        Disposable disposable4 = observable.subscribe((p) -> System.out.println("s4: " + p));
        Disposable disposable5 = observable.subscribe((p) -> System.out.println("s5: " + p));
        Disposable disposable6 = observable.subscribe((p) -> System.out.println("s6: " + p));
        CompositeDisposable wasteBin = new CompositeDisposable();
        wasteBin.add(disposable4);
        wasteBin.add(disposable5);
        wasteBin.add(disposable6);
        Thread.sleep(5000);
        wasteBin.dispose();// have to call dispose on each Disposable. Then, have to inform all observables from which they were subscribing that no more push is to be done.
        Thread.sleep(2000);
        //what if we want to terminate only few of the items/ subscriptions from composite disposable.
        Disposable disposable7 = observable.subscribe((p) -> System.out.println("s7: " + p));
        wasteBin.add(disposable5);
        Thread.sleep(5000);
        wasteBin.delete(disposable7);
        Thread.sleep(2000);
    }
}
