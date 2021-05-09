package com.java.rx;

import io.reactivex.rxjava3.core.Observable;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class OtherOperators {
    public static void main(String[] args) throws InterruptedException {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //take(observable);

        //skip(observable);

        //distinct(observable);

        //defaultAndSwitchOnEmpty(observable);

        repeatAndScan(observable);

        Thread.sleep(5000);


    }

    private static void repeatAndScan(Observable<Integer> observable) {
        observable.repeat(2).subscribe((data) -> System.out.println("Repeat: " + data));

        observable.scan((accumulator, next)->accumulator*next).subscribe((data) -> System.out.println("Accumulator: " + data));

        observable.scan(100, (accumulator, next)->accumulator*next).subscribe((data) -> System.out.println("Accumulator with starting value: " + data));

    }

    private static void defaultAndSwitchOnEmpty(Observable<Integer> observable) {
        observable.filter(data->data>2000)
                .defaultIfEmpty(Integer.MAX_VALUE)// if no data > 2000, prints max value
                .subscribe((data) -> System.out.println("Empty: " + data));

        observable.filter(data->data>2000)
                .switchIfEmpty(Observable.just(2000, 2001, 2002))// if no data > 2000, prints values from other observable. 2000, 2001, 2002
                .subscribe((data) -> System.out.println("Switch: " + data));
    }

    private static void distinct(Observable<Integer> observable) {
        observable.map(data->data%4).distinct()
            .subscribe((data) -> System.out.println("Distinct: " + data));

        Observable.just(new Node(10), new Node(10), new Node(20)).distinct(Node::getData)
                .subscribe((data) -> System.out.println("Distinct by method: " + data.getData()));// 10, 20

        Observable.just(1, 2, 2, 3, 1, 2, 2, 4, 4, 5).distinctUntilChanged()
                .subscribe((data) -> System.out.println("Distinct until changed: " + data));//checks with previous, if same, skips, else takes.

        Observable.just(new Node(10), new Node(10), new Node(20), new Node(30), new Node(20), new Node(10)).distinctUntilChanged(Node::getData)
                .subscribe((data) -> System.out.println("Distinct by method: " + data.getData()));// 10, 20, 30, 20, 10

    }

    private static void skip(Observable<Integer> observable) {
        observable.skip(2).subscribe((data) -> System.out.println("Skip: " + data));
        observable.skipWhile(data->data/3 != 2).subscribe((data) -> System.out.println("Skip while: " + data));
    }

    private static void take(Observable<Integer> observable) {
        observable
                .take(5)//takes only 5 emissions
                .subscribe((data) -> System.out.println("Take: " + data));
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(1, TimeUnit.SECONDS)// takes only emissions in first 1 sec.
                .subscribe((data)-> System.out.println("Intervals: " + data));

        observable
                .takeWhile(data->data/3 != 2)// terminates the loop immediately after it gets a data which disobeys the condition.
                .subscribe((data)-> System.out.println("takeWhile: " + data));//stops when 6 comes. 6%3 gives 2

    }

    static class Node{
        int data;

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }
    }
}
