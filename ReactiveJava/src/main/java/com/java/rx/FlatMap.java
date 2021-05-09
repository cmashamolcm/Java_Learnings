package com.java.rx;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class FlatMap {
    public static void main(String[] args) {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        //flatMap(observable);
        //flatMapWithBiFuncation(observable);
        //map(observable);
        mapVsFlatMap(observable);
    }

    private static void mapVsFlatMap(Observable<Integer> observable) {
        Observable<String> flatmap = observable.flatMap((data) -> {
            if (data == 1) {
                return Observable.just("O", "n", "e");
            } else if (data == 2) {
                return Observable.just("T", "w", "o");
            } else if (data == 3) {
                return Observable.just("Three");
            } else {
                return Observable.just("Unknown");
            }

        }, (actual, newObservableFromFlatMap)->{
            return actual + " : " + newObservableFromFlatMap;
        });
        flatmap.subscribe((data)-> System.out.println("Flatmap Result = " + data));

        //equivalent map function will be;
        Observable<Observable> map = observable.map((data) -> {
            if (data == 1) {
                return Observable.just("O", "n", "e");
            } else if (data == 2) {
                return Observable.just("T", "w", "o");
            } else if (data == 3) {
                return Observable.just("Three");
            } else {
                return Observable.just("Unknown");
            }

        });
        map.subscribe((data)-> {
            System.out.println("Map Result " + data);
            data.subscribe((internalData)->System.out.println("Inner Observable Result " + internalData));
        });

    }

    private static void flatMapWithBiFuncation(Observable<Integer> observable) {
        //.flatMap() have ObservableFlatMap which can hold Observable objects in it's stream. May be when we call, flatmap.subscribe(), it iterates over this stream of observable objects and applies .subscribe() to produce the output.
        Observable<String> flatmap = observable.flatMap((data) -> {
            if (data == 1) {
                return Observable.just("O", "n", "e");
            } else if (data == 2) {
                return Observable.just("T", "w", "o");
            } else if (data == 3) {
                return Observable.just("Three");
            } else {
                return Observable.just("Unknown");
            }

        }, (actual, newObservableFromFlatMap)->{
            return actual + " : " + newObservableFromFlatMap;
        });
        flatmap.subscribe((data)-> System.out.println("Flatmap Result after bi-function = " + data));
    }

    private static void flatMap(Observable<Integer> observable) {
        //.flatMap() have ObservableFlatMap which can hold Observable objects in it's stream. May be when we call, flatmap.subscribe(), it iterates over this stream of observable objects and applies .subscribe() to produce the output.
        Observable<String> flatmap = observable.flatMap((data) -> {
            if (data == 1) {
                return Observable.just("One");
            } else if (data == 2) {
                return Observable.just("Two");
            } else if (data == 3) {
                return Observable.just("Three");
            } else {
                return Observable.just("Unknown");
            }

        });
        flatmap.subscribe((data)-> System.out.println("Flatmap Result = " + data));
    }

    private static void map(Observable<Integer> observable) {
        //returns Observable objects. ie;stream of Observables.
        Observable<String> observableMap = observable.flatMap((data) -> {
            if (data == 1) {
                return Observable.just("One");
            } else if (data == 2) {
                return Observable.just("Two");
            } else if (data == 3) {
                return Observable.just("Three");
            } else {
                return Observable.just("Unknown");
            }

        });

        //To get same result as flat map: No need to return Observable.just() from internal in map.
        observableMap.subscribe((data)-> System.out.println("ObservableMap Result = " + data));

        Observable map = observable.map((data) -> {
            if (data == 1) {
                return "One";
            } else if (data == 2) {
                return "Two";
            } else if (data == 3) {
                return "Three";
            } else {
                return "Unknown";
            }
        });
        map.subscribe((data)-> System.out.println("Map Result = " + data));
    }
}
