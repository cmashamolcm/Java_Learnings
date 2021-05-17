##Java 8 Features: Came In 2014
1.  What is new?
    1. Functional Programming & Lambda
    2. Method Reference
    3. Collection API improvements
    4. Streams
    5. New Date and Time classes
    6. Static and default methods in interface
    7. Metaspace(part of native memory) replaced PermGen space.
2. OOP vs Functional Programming:
    * OOPs is based on objects and any line of code have to be attached to an object or class.
    * Functional programming is more focused on functions than classes

3. Lambda:
    * To enable functional programming in Java
    * Easy and short code
    * Parallellism support
4. **Evolution**:
   
    When we want to perform something different everytime, when we call a function, how will we do that?
    Eg: If I want to get reply from my greet() method differently like Gud Morning, Gud AfterNoon etc.
   One way is to do switch case based on some input.
   Other way is to do overloading
   Other way is to use inheritance. Make MorningGreeting, EveningGreeting classes from Greeting and give its own implementations.
   But it will be great if we can pass the way a method have to perform just as an argument.
   Eg: greeting(doEveningGreeting());
   There was no way in Java to pass a method as argument in a function.
   With the help of functional programming features in Java8, now we can do that.
   
    
Note: _In OOP,there is nothing that have existence outside a class. To make it possible to pass a function as argument of another method, one way is to pass instance that holds that function itself.
        That's why functional interfaces came so that we can pass it as lambda to a function as argument .

Note: Lambda makes it possible to make **"Function as value"**
        Eg: CustomFunction f = ()->{System.out.println("Hai")};
        It acts as implementation of interface itself.
~~~~
We can define LAMBDA EXPRESSIONS as representation of function as value. 
Functional interfaces can be directly represented by it.
~~~~
5. @FunctionalInterface allows an interface to have single method at compile time itself.
    If not given @FunctionalInterface and try to define one more method inside functional interface, compile time error comes in lambda as multiple abstract methods present in Interface.
6. Anonymous classes has .class files generated whereas lambda will not have it.
7. Functional interfaces will have only one abstract method.
8. Using abstract class as target type for a lambda expression is not possible as it gives compile time error.
   Reason: at the time of design itself, it was said: lambda are functions. Lambdas are not objects.
9. java.util.functions; have FunctionalInterfaces.   
10. Exception Handling In Lambda:
    * When we define Functional Interface, no need to define errors. But when we define implementations, can put try catch appropriately.
        
            Eg: interface A{
                public void test();
                }
                A = ()->{
                    try{
                        doSomething();
                    catch(){
                        //log e
                    }
                }
    * Or else wrap actual lambda inside another and original will not required to handle errors, but the outer one have to.
    * All variables used inside expression should be **final or effectively final** as similar to that in anonymous classes.
      The reason is to avoid concurrency issues in the future.
    * Closure is a function + its environment. It holds local variables used inside the funcation etc.
    * Lambda will also have it's own closure.
11. this reference from lambda:
    * From a static method, if we invoke lambda, inside that,this cannot be used.
    * If we define lambda within a method of an instance, this indicates that object inside lambda also.
    * Eg: class Lambda{
                void checkThis(){
                    FI fi = ()->{System.out.println(this)};//this prints address of Lambda object using which checkThis() was called.
                }
          }
          interface FI{
            void test();
            }
12. Method reference is to use as implementation of method instead of actually creating it while defining a lambda.
    Eg: FI fi = System.out::println;
    It's  short-hand representation for lambda implementation.
13. Collection Updates:
    * for each:
    * Normal loop is pull type. Explicit. For each also same.
    * list.forEach() does iteration push way. Implicitly.
    * forEach() can do parallel as compared to other loops. But it cannot manage checked exceptions, non-finals etc inside lambda used inside forEach.
14. Stream:
    * Sequence of elements that can be processed parallel or sequencial.
    * Stream will have a source
    * There will be terminal operations as well as intermediate operations on strems.
    * Only at the moment a terminal operation is added, stream starts flowing.
    * Eg: for terminal operations:
        * forEach
        * collect
        * first
    * Eg: for intermediate operations:
        * filter
15. Interface: Default and Static:
    * Abstract classes still has ability to hold a state as it can have private variables. 
    * Abstract classes can have constructor.
    * Default etc was intended to use in Java8 to modify collection apis without huge changes.
    * If interface A and B has same default method, child of AB have to override it. use A.super.methodName() to acccess it.
    * It cannot be accessed outside that child class with object of child class.
    * ab.A.super.test(); is not possible. but inside class AB, A.super.test(); is possible.
    * If a AB extends A implements B{}, both have same method, priority is to class method.
    * If class and interface has different scope for same method, AB have to override the method to make it corrected to public as interface has it public by default.
    * Static methods are kind of util.
    * Only way to call static methods is InterfaceName.method(). No way AB.method() which is child class of interface.
    * Not allowed method signature as of Object class as default methods in interface.
    * Why?
    * By design, it is like that. The reason can be. If we define a default method in an interface, when a class implements it, by default Object is extended. So, overriding automatically happens.
    * Then there is no point to do so.
16. Date:
    * Old one creates confusions like sql.Date or util.Date.
    * Old Date is not thread safe
    * Old one gives date and time if we ask for date which is also confusing
    * new one is in java.time;
    * LocalDate is immutable object.
17. Time:
    * LocalTime.of()
    * Immutable
18. LocalDateTime:
    * Contains both date and time.
    * LocalDate.now().atTime(LocalTime.now()); gives LocalDateTime
    * LocalTime.now().atDate(LocalDate.now()); gives LocalDateTime
19. ZonedDateTime:
    * Contains zone info.
    * ZonedDateTime.of(LocalDateTime, ZoneId);
20. Instant:
    * Machine-readable time in GMT.
    * LocalDateTime.now(ZoneId for GMT) will give time without Z.
    * Instant.now() gives date time with Z.
    
    Note: When we call .now() etc without specifying time zone, it takes system time zone.
    
    Note: When we call, LocalDate.parse(""); it expects yyyy-mm-dd format by default.

21. ###Parallel vs Sequential Streams:
****

1. Sequential works in single thread, single core. Parallel stream uses multiple cores.
2. Sequential gives ordered outcomes whereas parallel stream gives different order in each execution.
3. Two ways to create parallel streams.
   * Collection's .parallelStream()
   * BaseStream.parallel(); Eg: Stream.of(1).parallel();
4. There is BaseStream.sequential(); also.
   * Stream.of(1).sequential();
   * Stream.of(1).parallel().sort().sequential();
5. Parallel Stream Steps:
        
    1. Split data
    2. Apply operation
    3. Combine
6. When we;
   Stream.of(1, 2, 3, 4, 5).parallel().forEach(p->System.out.println(Thread.currentThread().getName()));
   It will print names of parallel threads. It will be of ForkJoinPool.commonPool-worker-1, 2, 0 etc.
7. Map vs FlatMap:
    * map() -> returns a stream of data. Stream<R>
    * flatMap() -> returns a stream but accepts also is a stream. It's like accept streams as input and process those streams internally and returns a single stream as outcome.
    * Stream of streams is input.Stream comes as output.

Note:
    * Intermediate operations and terminal operations.