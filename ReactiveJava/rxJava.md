##RxJava:
****
1. **What** is reactive programming? 
   Programming paradigm that helps to manage certain ordering for async events.
   Its considering data as streams and process it asynchronously hiding the complications of threads and concurrency.
   

2. **Why** is it needed? Usecases.
   * Async and Concurrency implementation, even with java.util.concurrent, Fork-Join, MapReduce is so complicated and risky to manage.
   * Push approach is better than intermittent checking/ polling
   * Effective resource utilization on scale up and down.
   * Best suited for cases where multiple service calls are involved in single API of miroservice. Can get rid of complexity of callback hells and easy to implement
   

2. **Features**?
   * Responsiveness
   * Resilience/ ease of recovery
   * Message driven
   * Scalable
3. **Advantages**:
   * Simple way to implement async processing 
   * Backpressure - can decide the rate of data push from a producer. Helps to avoid out of memory due to overflow of data to be processed in a system.
   * More of a push by producer based mechanism than pull by subscriber approach
4. What is **rxjava**?
    Reactive extension for Java.
5. Components:
   3 O's:
   * Observable -producer
   * Observer -consumer/ subscriber
   * Operator - any processing done on top of the data streams coming from observable to observer.
   
                     operator(eg: map)
         Observable  -----------------> Observer
                     push
   
6. reactive extension vs rxJava vs reactiveStreams vs reactor vs spring 5.0
   
   
   **reactive extension:**
   * Reactive Framework abstraction from which any reactive libraries in any language is build on top of.
   
   **reactive streams:** 
   * Part of JDK from 9. Basic things for reactive support in Java
   
   **rxJava:** 
   * Library from Netflix that implements reactive extension and used with java.
   * Latest 3.x. It supports Java8+, package restructured.
   
   **reactor:** 
   * Library from Pivotal. Base for spring reactive part
   
   **spring 5.0:** 
   * Reactive supported spring version. Build on top of reactor core.


7. Components Required:
   If we use rxJava 3.x, it need Java 8+ as it uses streams and Futures.
   
8.       Observables
         |
         |
         * -based on how it treats observers
         |   * |----hot------those will not wait for subscribers to start. Eg: concerts will be lost for those who come late. Short-tempered.
         |   * |----cold-----cool observables who can serve for all observers irrespective of when they start subscribing. Eg: hotel serving lunch for all whoever comes
         |   * |----connectable------cold observable converted into hot. When 2 people start watching netflix and one comes a bit late, looses some part, but he can always reply whenever needed
         |
         |or
         |  -based on how many times it informs observer
         |
         |  * |----Single-----informs the observer only once either On start or On error/cancel. Atleast once observer get notified. (min 1)
         |                    Eg: get notify by ticket distributor on show is there or cancelled.
         |
         |  * |----MayBe------informs the observer if success, failure/ error and complete/ finish of event. But its not mandatory to get notified. (min 0)
         |
         |
            * |----Completable----gets notified on finish. Eg: get notified on end of show. (min 1)

9. Create Observables:
   

      1. Observable<Integer> = Observable.just(...);//max. 10 attributes possible as 10 overloaded methods are there.
      
         Eg:   Observable<Integer> = Observable.just(1, 2, 3, 4,5).subscribe(System.out::println);
      

      2. Observable<Integer> = Observable.fromIterable(...);// any number of elements can be there as the source of stream is an iterable collection.
      
         Eg:   Observable.fromIterable(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40)).subscribe(System.out::println);

      3. Observable<Integer> = Observable.create(ObservableSubscribe funcation interface with void subscribe(ObservableEmitter which is a type of Emitter with onNext(T), onComplete(), onError(Throwable e) methods.));
         Internally,rx framework will create an Observable when calls create()method. This observable will hold implemenation of emitters. That's why when we call emitter.onNext(1), it invokes that created observable's emitter's method.
         Eg: Observable.create((emitter->{
                     emitter.onNext(1);
                     emitter.onComplete();//optional
               }))
               .subscribe((p)->System.out.println("Next/ Success: " + p), (p)->System.out.println("Error: " + p), ()->System.out.println("Completed"));// onNext consumer, onError consumer, onComplete action
         
10. Observers:
    Should be able to process onNext, onError and onComplete responses from the Observable.
    .subscribe() method is overloaded as, 
    
               .subscribe(consumer onNext);
               .subscribe(consumer onNext,consumer onError);//.accept()
               .subscribe(consumer onNext,consumer onError, action onComplete);//.accept(), .run()
               .subscribe(Observer interface with onNext(), onError(), onComplete() methods to override.)
11. Cold----------to-------->hot Observable
        
        Cold observables can serve any number of subscribers at any point of time.
        But hot observables can serve to those subscribers who have registered before calling connect() method.
        Eg:
        Observable<Integer> cold = Observable.just(1, 2, 3, 4, 5);
        ConnectableObserver<Integer> hot = cold.publish();
        hot.subscribe((p)->{
            System.out.println("Hot Subscriber 1: " + p);
        });
        hot.connect();
        hot.subscribe((p)->{
            System.out.println("Hot Subscriber 2: " + p);
        });
        here, only Subscriber 1 will get data. Subscriber 2 started after connect() will not get data.
12. Error Handling:
    
        * Observable.error(Throwable)
            * It returns same exception object to all subscribers on error.
        * Observable.error(()->Throwable)-----callble with the help of Supplier interface (.get()).
            * Returns  new error object to each the subscription.

13. Empty vs Never Observables:
    *   **Empty**: 
        Returns no items. Just calls onComplete(). Good to return instead of null. Just like Optional.
        
    *   **Never**:
        Not returning anything or not notifying anything, not even on complete messages it can print. Just to use for testing.
14. Observable.**range**():
    *   Similar to IntStream we can say.
    *   Observable.range(5, 10).subscribe();// prints from 5 to 14. Ie; 10 elements from 5. Including 5.
    
15. Observable.**defer**(): Manage state changes.
        It accepts an observable and makes it into flexible for change with Supplier.
    *   When we want to change something is an observable on the go, eg: change the count in range(),
        
        int range = 10;
        
        Observable o = Observable.range(5, range);
        
        o.subscribe();
        
        range = 101;
        
        o.subscribe();// even here, we will get only 10 elements as the observer is already created.
        
        But if we want to change it for second subscription,
        use defer().
        
            private int count = 10;
            Observable<Integer> callableObservable = Observable.defer(()->Observable.range(0, count));
            callableObservable.subscribe((p)-> System.out.println("callable s1: " + p));
            count = 20;// s2 prints from 0 to 19.
            callableObservable.subscribe((p)-> System.out.println("callable s2: " + p));

16. Callable Observable:
    
    It will be capable of to load lazily.
    In usual cases, the stream is prepared in advance and starts flowing when subscribe() is called.
    Here, even stream is created lazily on subscribe().
    
    defer() makes the existing observable a bit lazy by Supplier to support state change.
    fromCallable() creates observable itself as lazy.
    onError() works well in callable observables whereas in normal observables, an exception is just thrown out. onError() is not handling it proper if not defined.

17. Observable.interval():
    Emits from 0 to n in each interval of time.
    Returns Long.
    Observable.interval(1, TimeUnit.SECONDS);// prints from 0 to n in each 1 sec.
    For all subscribers, it will give from 0 to 1. So,it's cold.
    To make it hot, publish it as connectable.
18. Single,Maybe, Completable:
        
        Single:
            * onSubscribe();
            * onSuccess();
            * onError();
        Maybe:
            * onSubscribe();
            * onSuccess();
            * onError();
            * onComplete();
        Completable:
            * onSubscribe();
            * onError();
            * onComplete();
    
 19. Managing subscriptions:      

    subscribe() returns Disposable object.
    disposable.dispose(); can stop subscription.
    CompositeDisposable can hold as many disposables as possible and its dispose() can trigger disposing of all disposables.
    compositeDisposable.delete(disposable); can make the specific disposable unsubscribed.

20. Operators:
    
    Returns a new Observable once apply operations on data stream.
    1. Map:
       
        * Observable.just(1, 2, 3, 4, 5).map(p->p*2).subscribe(q->System.out.println(q);//prints multiply by 2 for the input stream.
        
    2. Filter:
       
        * Observable.just(1, 2, 3, 4, 5).filter(p->p%2==0).subscribe(q->System.out.println(q);//prints even from input.
        
        Note: Here .filter(),.map() returns another Observable itself.
            ie; actually they are acting as observers as well also observables at the same time.
       
    2. Take:
        * Observable.just(10, 20, 30, 40, 50).take(3).subscribe(q->System.out.println(q);//take only n emissions ei; 10, 20, 30.
        * Observable.interval(1, TimeUnit.MILLISECONDS).take(1, TimeUnit.SECONDS).subscribe(q->System.out.println(q);// accept emissions for 1st 1 sec only.
    3. TakeWhile:
        * Observable.just(10, 20, 30, 40, 50).takeWhile(data->data%2!=0).subscribe(q->System.out.println(q);// only prints 10. For 20, condition becomes false. So, terminates.
        * Note: takewhile vs filter:
                * Filter applies to each data in stream
                * Takewhile stops at the data where the condition become false.
    4. Skip:
        * Reverse of take(). It skips the given no: of emissions.
        * Observable.just(10, 20, 30, 40, 50).skip(3).subscribe(q->System.out.println(q);//take only after 3 emissions ei; 40, 50.
    5. SkipWhile:
        * Skip till given condition meets.
        * Observable.just(10, 20, 30, 40, 50).skipWhile(data->data%2!=0).subscribe(q->System.out.println(q);// prints 20, 30, 40, 50
    6. Distinct, DistinctUntilChange:
        * Observable.just(10, 20, 50, 20, 50).distinct().subscribe(q->System.out.println(q);// prints 10, 20, 50.
          Only unique items
        * Observable.just(10, 20, 20, 40, 20).distinctUntilChanged().subscribe(q->System.out.println(q);  //10, 20, 40, 20
          Checks element to its previous item. if different, prints, else skips
    7. DefaultIfEmpty, SwitchIfEmpty:
        * DefaultIfEmpty: Return a default value of we get empty stream.
        * SwitchIfEmpty: If one observable is giving empty value, switch to another observable itself.
    8. Repeat and Scan:
        * Repeat: To repeat the stream n times. default repeats max value of long.
          
          Observable.just(10, 20, 30).repeat(2).subscribe(q->System.out.println(q);// prints 10, 20, 30, 10, 20, 30
         
        * Scan: Accumulates the values in a stream based on given operation.
          
            * Observable.just(10, 20, 30, 40, 50).scan((accumulator, next)->accumulator*next).subscribe(q->System.out.println(q);// prints 10, 10*20, result of(10* 20)*30, result of(result of(10* 20)*30)* 40...etc
            * Observable.just(10, 20, 30, 40, 50).scan(10, (accumulator, next)->accumulator*next).subscribe(q->System.out.println(q);// prints 10*10, (10*10)*20, ((10*10) *20)*30...etc.
            * ie; for scan with initial value,  its actually accumulator which 1st data have to operate. If there is not initial value, accumulator will be the first data itself and prints it. Then does accumulator operate with second element.
    9. Sort:
        * Not to use with interval() etc. with infinite stream.
        * .sort(); does ascending order
        * .sort(Comparator);
          Eg: Observable.just(10, 20, 30).sort((a, b)->Integer.compareTo(a, b)).subscribe();// expects a and b of Comparable type.
          
          Eg: Observable.just(10, 20, 30).sort(Comparator.comparing(a)->Integer.intValue()).subscribe();// comparator key will be int value of Integer.
    10. Delay vs Interval:
        
        Delay helps to start emission late whereas interval helps to put delay in between emissions.
        Observable.just(10, 20, 30).delay(10, TimeUnit.SECONDS).subscribe();
        Starts emission after 10sec only.
        But .interval() will produce only Long type stream.
    11. Contains:
        Returns a Single with Boolean.
        Observable.just(10, 20, 30).contains(10).subscribe();//true
    12. Error Handling:
        * doOnError((e)->no return);// gets invoked onError() of subscribe().
        * doOnErrorReturn((e)->return 2000);   //onNext()gives 2000
        * doOnErrorReturnItem(3000);//onNext() gives 3000
        * doOnErrorResumeNext(e->Observable.just(10));//onNext() gives 10.
        * retry(3); //retry 3 time on error
        * retry((error)->{return counter<10;});//retry till counter reaches 10. Ie; retry till condition is true.
        * retryUntil(()->{return counter<10;});//retry till counter reaches 10. Ie; retry untill condition becomes false.
        
        Note: retry() happens onError. repeat() happens onComplete().
        
    13. DoOn Operators: 
        
        This is exclusively for Observable side. There are methods like onSuccess(), onSubscribe() etc for Observer interface. But that's exclusively for observers. 
        * To allow Observable/ producer do something, when something happens.
        * doOnSubscribe():
            * Do something in producer side when some Observer starts subscription.
            * Observable.just(1, 2, 3).doOnSubscribe((disposable)->System.out.println("Subscribed").subscribe();
        * doOnNext():
            * Observable.just(1, 2, 3).doOnNext((data)->System.out.println("Next" + data*2).subscribe();// Here prints 2*data...but actual subscriber will get actual data. Not the modified one.
        * doOnComplete():
            * Observable.just(1, 2, 3).doOnComplete(()->System.out.println("Completed").subscribe();
        
    14. doFinally() vs doOnDispose():
        * doFinally(()->{System.out.println("Finally");});// This part will get executed if flow goes to onComplete() or onError(). Anyway, this will be getting printed.
        * doOnDispose(()->{System.out.println("Disposed the subscription");});// This part get executed when disposable.dispose() is called explicitly.
        * if we give observable.onSubscribe(dispose->dispose.dispose()
          .doOnDispose(()->System.out.println("disposed."")).subscribe();// here. since chaining order mappers, dispose() will not get invoked as its after onSubscribe().
          
    15. Merge:
        * Observable.**merge**(Observable.just(1), Observable.just(1, 2, 3)).subscribe();// 1, 1, 2, 3
        * Observable.merge(Observable.just(1, 2, 3), Observable.just(1)).subscribe();// 1, 2, 3, 1
        * Merge can have 4 items passed or one Iterable.
    
        * Observable.**mergeArray**(Observable.just(1, 2, 3), Observable.just(1), Observable.just(10), Observable.just(100), Observable.just(1)).subscribe();// 1, 2, 3, 1, 10, 100, 1
        * List<Observable<Integer>> list = List.of(Observable.just(1, 2, 3), Observable.just(1), Observable.just(10), Observable.just(100), Observable.just(1));
          Observable.**merge**(list).subscribe();//1, 2, 3, 1, 10, 100, 1
        * Observable.just(1).**mergeWith**(Observable.just(10, 20, 30)).subscribe();// 1, 10, 20, 30. mergeWIth can merge at a time one Observable to another.
        * **Infinite Observable merging:**
        * Observable.interval(1, TimeUnit.SECONDS).map(data->data + "First"").mergeWith(Observable.interval(2, TimeUnit.SECONDS).map(data->data + "Second")).subscribe();//0First, 0Second, 1First, 2First, 1Second...etc.
        * Make sure to add delay in main thread to view the result for infinite observables.
        * Also, type from mergeWith()'s observables should be same.
    
    16. Zip:
        * Uses the values from Observables and manipulates it.
        * Merge just combines back to back..but zip takes each item from each observable and process it as per given action.
        * Observable.zip(Observable.just(1, 2), Observable.just(2, 3, 4), (a, b)->a+b).subscribe();//1+2, 2+2 will be the result. Since there is no more value in 1st observable, it ends.
        * Observable.just(1, 2).zip(Observable.just(1, 2, 3), (a, b)->a+b).subscribe();//2, 4
        * For infinite loops also, it can be done.
        * Observable.interval(1, TimeUnit.SECONDS).zipWith(Observable.just(1, 2, 3, 4, 5), (a,b)->a+b).subscribe((data)-> System.out.println("Infinite zip add = " + data));//0+1, 1+2, 2+3, 3+4, 4+5.
    17. FlatMap:
        * Convert elements of an Observable into another stream of Observables.
        * May be .flatMap() have ObservableFlatMap which can hold Observable objects in it's stream. May be when we call, flatmap.subscribe(), it iterates over this stream of observable objects and applies .subscribe() on each of those observables to produce the output.
        * observable.flatMap(function, bi-function);//Function creates stream of Observables from given input stream. 
        * when flatmap.subscribe() is called, it calls .subscribe() of each observable internally. Then bi-function applies on each of the observables created. It will be having actual data from input observable as well as the result of .next() of each of the observable of ObservableFlatMap.
    18. Map vs FlatMap:
        * Map accepts function accepting and returning anything.
        * Flatmap accepts a function which accepts anything but returns Observables of that time.
        * FlatMap can be considered as a special map which will produce stream of Observable type. It implicitly triggers the subscribe() of each of that observable from tream when we call flatmap.subscribe();
        * It applies the bi-function inside that internal subscribe() call.
        * Map returns anything. FLatMap returns Observable. Advantage of flatMap is, its observables can be running in different threads for fast parallelism
    19. Concat vs Merge:
        * Combines Observables one after the other. Almost similar to merge. But difference is that,
        * Concat appends next observable only once the previous observable in done with onComplete().
        * Eg: Observable.interval(1, TimeUnit.SECONDS).take(3).concat(Observable.interval(10, TimeUnit.MILLISECONDS)).subscribe();
        * //Here, 0, 1, 2 comes first, then only starts 0, 1, 2,....of second one.   
        * //But in case of **merge order is not preserved**. It takes whichever comes the way.  
        * 
    20. concatMap() vs flatMap():
        * concatMap() keeps order safe.
        * flatMap() not preserving order strictly based on the data stream items order.
        * Both returns Observable for each stream element based on function, but use concatMap() if order is important.
    21. 
