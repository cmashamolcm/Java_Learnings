### Java revised 2023
1. jshell:
   - from Java9
   - cli playground to experiment with Java
2. How does Java works?
```
.java -----javac compiler---compiles into--->.class(byte code)
                                                      |
                                                      |
                                                      |
                                                      v
      (JVM searchs for main as starting point.    ____|___    
      That is why we can compile without main.    |  |---JVM---|   
      But we cannot run)                          |----JRE------|   has libs to support JVM            
                                               |--------JDK-------| only for developers.  
                                               |        |
                                                        |converts to machine code (0s and 1s for os)
                                                        v
                                                        OS
                                                        |
                                                        v
                                                        Hardware

JDK contains JRE and JVM. To run app, we need only JVM and JDK.
JVM, JDK and JRE are platform dependent. But our Java program is not as for a program, its always running on JVM.

```
3. variables:
   - Storage for data
   - It has `type`, `name`
   - Primitive types (boolean, byte, int, float, double)
4. Datatypes:
   - Java is strongly typed.
   - Primitive
     - int(4byte) - can hold hex(0x7A) or oct(0b10 or 0B10)
     - long(8byte)
     - float(4byte0)
     - double(8byte)
     - boolean(<1byte)
     - byte(1byte)
     - char (2bytes unicode)
   - Reference type:
     - Class objects, arrays etc.
5. Type conversion:
    - We can assign small items to big. int can be hold in long. 
    - But if we try the reverse, there can be precision problem.
    - To convert big to small, we have to explicitly specify it. This is called **casting**
- Conversion:
```
    int x = 10;
    long y = x;// auto convertion
```  

- Casting:
```
    long x = 10;
    int y = (int)x; // here, explictly we have to specify. This is casting.
```
- When we do a down-casting, from int to byte etc (case of numbers); 
- (the number) mod (range of lower type) will be stored if the bigger number is beyond the range of smaller one.

-Type promotion:
Eg: When some operation on two numbers from same type results in bigger number which is beyond the range of that type,
    Java promotes it to next big one.
```
    byte x = 127;
    byte y = 127;
    int z = x*y; // here, Java itself promotes result to int as result exceeds range of byte.
```
6. Operators:
    - Arithmetic (+, -, *, /, %)
    - Relational (<, >, ==, 1=)
    - Logical (&&, ||, !)= short circuit as if first one is true, second one may or may not ignore. 
      &, |, ! or, and, not
    - Assignment operator: =
7. if(){}else{}, if(){}else if(){}, if(){}else if(){}else{}
8. Ternary operator: int x = a>b?10:20;// shortcut for if else.
9. switch Statement: cases, default break
    - We can use `String constants` in switch from `Java7` onwards. We cannot use `string s = "S"; then case s: `
    - If we use string literals, its ok. But variables used as case should be final.
    - String uses `equals` comparison.
    - `enum` is supported for switch cases form Java 5
    - If we pass null value to switch, NullPointerException comes.
    - **New Switch Expression:**
      - Got introduced in Java 12 as preview feature. But become part of Java17 which has LTS. 
      - It uses -> and can return some value that can be assigned directly.
     ```
        int result = switch(){
                      case 0->100;// replaced : with ->.
                      case 1, 2->200;// multiple cases can be clubbed with ,
                      default->300;// no break; required. ; is enough.
                     ;
     ```
      - But, there, we cannot have explicit return statement(eg: return after some more logic on each case. `yield` is the solution.)
    ```
        int result = switch(){
                      case 0->100;// replaced : with ->.
                      case 1, 2->{ 
                             System.out.println("Case for 1 and 2");
                             yield 200;// instead of return, yeild is used.
                            }// multiple cases can be clubbed with ,
                      default->300;// no break; required. ; is enough.
                     ;
        Note: we can just not return anything also from switch expression.
     ```
      - We have to covert all cases or should have default for switch expression. Else, compile time error comes.
      - May be since it is returning something, it's not good to avoid cases.
      - Java 17 introduced null as a cases, pattern matches(different data types we can give for case), guarded pattern(relational operation conditions as one case)
10. loops: re-iterates same activity. Below items helps to conditionally create loops.
    - while
    - do-while - runs atleast once.
    - for
    - for each :
    - `for(int x: array){//here x will have value from array one by one and we don't have to worry on array length.}`
    - When we do, `return;` from an inner loop, it exist from outer loops also if any.
    - When we do, `break;` from inner loop, it exists only inner loop.
    - When we do, `continue;` it skips just the execution that meets the condition.
11. Classes and Object:
    - Object is an instance with properties and behaviors (actions/ methods)
    - Classes are blueprints for object. Class defines the specifications for an object.
    - JVM creates objects based on class
12. Method overloading vs Overriding:
    - Overloading - method with same name and return type but with different params(type, no: of params)
    - Why return type is not accepted to make a difference between methods?
    - It is to avoid confusion for the consumers of the method on which variable to assign result to.
    - Override -  Parent class has one method and child changes it by redefining it. 
13. Stack and Heap:
    - Stack holds local variables and values for method
    - Stack has entries for each variable with key as variable name and value as actual value or reference to heap.
    - Each method will have it's own stack. Gets cleared once execution is finished.
    - Heap holds object/ reference variables.
14. Arrays:
    - To have collection of data of same type.
    - But `drawback` is 
      - It has fixed size
      - We need to traverse if we have to apply some operation on every element.
15. Static:
    - `Static variables or functions` are those common to every instance of a class.
    - Static variable/ methods will be part of class and shared across instances
    - Static methods can be calls with `instance.` also. But that is same as calling with `ClassName.`
    - To start execution without even creating object, `main is static`.
    - `Static block`
      - Contains lines of code to be executed only once for a class irrespective of how many objects we create out of that class.
      - This block executes at the very first time when class is loaded. It can be first time when `Class.forName('class name'')` or first `new` or `uses sttaic varibale/ method of class for the very first time`
16. Encapsulation:
    - Grouping together. 
    - Keeping code and data coupled.
    - Eg: class holds props and methods
17. Abstraction:
    - Show only relevant details to outside world
    - Hide other details and restrict access through behavior.
    - With access specifiers like public, private, protected, default/package scope
18. this: points to current object
19. constructors: 
    - special methods having same name as class.
    - It gets executed on object creation with `new`.
    - It can be invoked by calling `super` from child classes also.
    - Default constructor vs parameterized constructor
    - By default, every object is inherited from `Object` class.
    - By default constructor calls `super()`.
    - If parent doesn't have default constructor, we have to explicitly define a constructor in child and call super() with param explicitly.
    - To call overloaded constructor, call with `this()` with or without parameters.
    - calling a `this()` or `super()` is not creating fresh objects everytime.
20. annonymous objects: `new A()`. Cannot reuse
21. reference objects: `A a = new A()`. This `a` is reference to object and reusable.
22. Inheritance: `is a`. 
    - Gets properties from parent class to child class
    - It helps to reuse properties and functions
    - Use `extends` to inherit from class `implements` from interface.
    - Java has single inheritance (one parent class)
    - Multilevel inheritance: C extends B, B extends A.
    - Multiple inheritance of `classes` is not there. To avoid ambiguity problem of same signature methods.
    - But can be done with interfaces. to avoid confusion we override the methods.
    - If common method signature is there in both, anyways, we have to override and problem is solved.
    - Interface has methods by default `public abstract`. Other type is `default` methods.
    - What happens when we inherit from 2 interfaces having same default method signature and need to access one of them inside the class?
    - Fields are not overridden. But will be available in children. Only visibility changes when same named field is added in child.
```
    We can give;
    class A{
        int x = 10;
    }
    
    class B extends A{
        String x = "hai";
    }
    
    is possible as this is hiding not overridig.
    Then;
    A a = new B();
    A.x;// this gives 10 itself as type is still A for object a.
    To get string x;
    B b = new B();
    b.x; //sould be called.
   
```
    - Private variables of parent will not be visible to child.
```
    interface A{default test(){}}
    interface B{default test(){}}
    class C implements A, B{// compiler asks to override default method if found multple times. Else no issue.
        void add(){
            super.test();//cannot be done as there is no method test in Object class which is `super` here..
            
            A.super.test();
            B.super.test();// can specifically go to exact super implementations.
        }
    }

```
23. Packages:
    - Folder to group .java files
    - `java.lang` is by default imported.
    - When we import 'a.*' means all files from package `a`. But not sub-packages.
    - To specify package;
    - `package com.java.mypackage;` on top of .java file.
    - Make sure we have com, java, mypackage folder structure.
    - To use these package files;
    - `import com.java.mypackage.*;` // if need all files
    - `import com.java.mypackage.ClassName;`// if specific class ClassName is only required.
24. Access modifiers:
    - public
    - protected
    - private - cannot be inherited
    - default/ package private
25. Polymorphism:
    - Behave differently with situation
    - 2 types:
        - runtime polymorphism or dynamic binding (overriding. Depends on which class instance gets assigned to variable, the method will differ. )
        - compile time polymorphism or static binding(overloading - decided ast compile time itself based on method signature.)
26. Final:
    - To make a variable constant. We cannot change it once assigned.
    - final variables are hidden from parent to child if redefined in child.
    - If we do `Parent p = new Parent();`, and `a` is a final variable. Then `p.a` will give value from parent.
    - If we do `Parent p = new Child();`, and `a` is a final variable in parent and child, still `p.a` will print that from parent. as p is given as `Parent p`.
    - If we did `Child p = new Child();`, then `p.a` will give value of a from child class.
    - Final method will not be overridable.
    - Final classes cannot be extended.
    - `private final` means same meaning individually too. So, one is enough.
    - `private only` not visible in subclass
    - `final only` is visible but cannot change in subclass or same class
27. javap -c <ClassName>
    - To describe a class. 
    - Shows fields, methods, machine level kind of translation of code as well.
28. equals, toString, hashCode:
    - methods from Object class
    - toString() gives `className@hashCodeOfMemoryAddressOfOurObject`
    - ***When two objects are equal, hashcode should also be same***
    - ***But when twp objects has same hashcode, it is not mandatory that they are equal.***
    - In inheritance, equals() contract should not get violated. An instance of parent and child are not equal as class are different.
    - So, better to check ob.getClass() for equality check.
29. Wrappers:
    - Primitive types makes Java not fully(100%) OOO. But 99.9%
    - Wrappers are object equivalent of primitives.
    - `Boxing`: Wrap a primitive to its wrapper
    - `Autoboxing`: 
    - Automatically perform the boxing or conversion of primitive to wrapper.
    - Eg: Integer i = 20;
    - `Auto-unboxing`:
    - Convert wrapper to primitive automatically.
    - Eg: int i = new Integer(20);
30. Abstract keyword:
    - Abstract methods are to be implemented in child classes.
    - Abstract methods should be only in interface or `abstract` class.
    - It is not mandatory that an abstract class need to have abstract methods. But reverse is mandatory.
    - We cannot instantiate abstract class directly. Need to extend it and implement abstract method and then can instantiate as object of that class.
    - If child also is abstract, no need to override abstract methods. Those inheriting child can do.
    - We can create instances of concrete classes.
31. Inner classes:
    - `Only inner classes can be static. Outer classes can be final, public, abstract. But not static.`
    - When we create anonymous classes, it will create .class files as `MyClass$1.class`, `MyClass$2.class`
    - When we define classes inside a class, .class files will be as `Outer$Inner.class`
    - We can define anonymous inner class for abstract class.
32. Interface:
    - For generalization.
    - They are abstraction for classes
    - By default, methods of interface are `public abstract`.
    - It has `default` methods and `final static` variables from `Java8`
    - Why variables of interface are 'final and static'?
    - Interface doesn't have memory. So, to allocate variables, static is the way.
    - If we allow changing variable, it can create confusions where we use subclasses.
    - Also, there is default methods which are common to add subclasses but can override.
    - If not overridden and used fro interface and if that is using these variables, 
    - it can give different results if variables are not final if we do reassign of variable and execute method after that.
    - We can have same name variable in child classes.
    - To access that of interface, we need to use Interface.<variable>
    - Interfaces can extend multiple interfaces.
    - Classes can implement multiple interfaces.
```
Normal Interface
Functional Interface: Single Abstract Method(SAM) interface
Marker Interface: No methods. Eg: serializable.
                  To inform compiler/ JVM some conditions, this is used. Mostly, for checkings.
                  Eg: Check for Cloneable to throw CloneNotSupportException while clone() an object.
                  Nowadays it is not so much in use and annoations are taking the place.
                  
  Why we need marker instead of normal interfaces?
  Consider that we need to delete all objects from database if type is Shape.
  Next, we need to delete all with type Person.
  Inead od writing more if else if, better is to have a marker Deletable and extend it by Person and Shape.
  So, that we can just check if Deletable and remove it.
  This helps to simplify the code.
  Markers are helpful for the implementation side to check. Not in users of them.              

```
33. Enums:
    - Special type to hold constant type of value.
    - Each enum extends `java.lang.Enum` class which provides special methods. 
    - It can hold more data along with constant value
    - This is actually, `named constants`
    - Enum has ordinal. Just like array index for each enum constant.
    - We can compare enums with ==
    - We cannot extend enum to interface or class
    - But we can enum can implement interface. Cannot extend class
34. Annotations:
    - Its metadata to compiler. Sometimes, to it is metadata to runtime.
    - Eg: @Override. To compiler. This helps to inform children if parent method signature changes.
    - Why we need it? To make compiler/ runtime aware of details about our class, methods etc.
35. Functional Interfaces:
    - Those interfaces with only one method to implement. Can have additional default methods.
    - Can use `@FunctionalInterface` annotation so that error can be found at compile time itself if one more abstract method get added.
    - Instead of anonymous classes, we can create `lambda expressions`. 
36. Lambda Expressions:
    - Expression with `->`
    - This can be used to create implementation of functional interface.
    - It helps to reduce code
    - It `works only with functional interfaces` to implement the single method of it.
    - It makes sure not $1 .class files are created.
    - But main .class file can be bulky as it will have more details to transform lambda.
37. Exceptions:
    - Compile time, runtime, logical errors.
    - Exceptions are mostly, runtime errors.
    - Catch with `try-catch` block
    - Runtime exception/ unchecked exceptions are thrown by JRE and at runtime.
    - Try catch helps to handle exceptions gracefully. Else, jvm backtracks the method stack to find any handler. 
    - If nothing is founds, it passes to default exception handler which is part of JVM.
    - Then terminates the program abnormally. 
    - Compile time/ checked exceptions are known and must handle by try catch.
    - We can add multiple catch blocks. Make sure child->parent is the order of catching exception.
    - This will help catch correct exception in each block. Child exception can be caught by catch block with Parent exception too.
    - Hierarchy:
```
                    Object
                       |
                    Throwable
                    |       |
              Error        Exception
         |       |                  |--------------------
     IOError ThreadDeadh            |                   |
                                    |                   |
Errors ar stop the world            |    RuntimeException (unchecked) - |-Arichmetic
VMErrors                            |                                   |-ArrayIndexOutOfBound
LinkageErrors                       |                                   |-NullPointer
(Eg: NoClassDefNotFound )           |-ClassNotFound
                                    |-SQLException
                                    |-IOException (all are checked.)
                                    They will be known and have to handle compulsorily.
                                    
```  

- Exception rules:
    * If parent method has checked exception, child method can have same exception or subtype of it or no exception or runtime exception.
    * If child throws checked exception, parent should also throw same or parent of that checked exception.
    * If child throws unchecked exception, parent method may or may not throw it.
    * These all things helps when we say Parent p = new Child();
    * When an exception is thrown, with above rule, all checked exceptions are known and unchecked are ok.
- There are try-catch-finally. `Finally` executes even if exception comes or not.
- When to choose runtime exception or exception?
- `Mostly for aspect based exception handling in spring etc, use RuntimeException so that we do not want to bother about exception everytime.`
- `Throw runtime exception if user can recover from the problem. Eg: /0 can be fixed by adding a valid number.`
- `Throw compile time exception where developers can handle the error.`
- `throw` to throw an exception
- `throws` to indicate that what all exceptions are thrown by a function
- When an exception happens, if we catch it, remaining flow after finally or catch will not get impacted unless we are rethrowing the exception to change flow.
- If we are nto handling exception, remaining part of code will not execute as JVM default handler terminates the thread and goes back in call stack.
- Avoid catching Throwable or Error as it is vast and masks the real problems.
- try and finally: try throws and finally will execute even if exception comes. But there exception is not handled.
- try with resource:
```
try(autoclosable resource){// here, this resource will be closed if try succeeds or throws exception.
// same effect as doing it in finally block.
}catch(){
}

```
- Even if we return from try block, finally gets called.
- Only way to make finally not invoked is `System.exit(0);`
  38. Collection Framework:(12 ownwards)(Generics came in 1.5)
      - group of elements with no size to specify before hand 
      - Datastructures have difference on how to traverse, store, delete etc.
      - Depending on the needs of performance, we can decide which one to use.
      - From `java.util` package
      - Base class is `Collection` and that comes from `java.lang.Iterable`
      - Works with reference type instead of primitives. To use primitives, we can use wrappers.
      - The reason is, collection is playing with `Object`.
      - Generics helps us to decide `the type at compile time itself`.
      - Diamonds of generics came form 1.7
      - List: (ArrayList), LinkedList - preserves insert order), Vector(similar to ArrayList. But synchronized):
          - internally array
          - This is similar to array but without fixed length.
      - Set: (HashSet, LinkedHashSet, TreeSet(sorted))
          - Collection of unique values and no duplicates
      - Queue: (FIFO) (PriorityQueue, Dequeue)
      - Map: (HashMap, TreeMap, LinkedHashMap):
          - Key value pair
          - Not implementing 'Collection'. But from `java.util.Map`
          - Key is collection of set and value is list of values.
          - Keys are not duplicated.
          - HashTable is synchronized
      - Details:
      - ArrayList:
        - It derives from 'RandomAccess' and hence index based access is possible.
        - (index based. Default capacity 10.
        - Increase by 50% of previous on each 
        - ArrayList internally calls `ArraySupport.newLength()` to find next length for new array within integer limit.
        - If more than integer limit comes, it throws OutOfMemory. Even if the size is within the limit, if JVM heap is full, still OutOfMemory can come.
        - On remove(), size is reduced by 1 and shifts elements to left.)
        - We can set initial capacity
        - There is `no concept of loadFactor` for lists as the backing array just holds one element per index.
        - But `loadFactor is there in Maps` because, there, one index points n elements with same hash for key.
        - Fail-fast iterator
        - Use `Collections.synchronizedCollection(list)` to make it thread safe. but will be slow due to synchronized methods.
        - null ok
        - Order preserved. But we can add in given index with a right shift. (1, 2, 3), add(1, 200)->1, 200, 3, 4
      - CopyOnWriteArrayList:
        - thread-safe variant of arraylist
        - fail-safe
        - Better when `reads are more and updates are less`. All writes are on snapshots. So, more memory use.
        - Take snapshot. Update it and rewrite back to original backed array inside `synchronized block` on add, remove, set.
        - Reads are not in synchronized block or snapshot. So, ok.
        - Iterator method remove() is unsupported. May be since it is direct update possible. So, no need of this method.
        - null ok
        - Order preserved
      ```
      - add(element) : O(1)
      - add(index, element): O(n)
      - get(index): O(1)
      - remove(): O(n). Iterates to find element
      - indexOf(), caonatins(): O(n)
      - null value ok  

      ```
      - LinkedList:
        - Dynamic and creates and links objects (nodes)
        - Underlying is double linked list as node has previous and next
        - Dynamic size.
        - O(1) insert() and delete() from ends
        - Performance overhead on access individual objects. Traversal/ get(index) O(n)
        - takes more memory to store the pointers to next, previous elements
        - null ok
        - Order preserved
        - Vector:
          - Legacy data structure
          - But now fully compatible to collections.
          - Synchronized list (similar to arraylist but synchronized)
          - Slow
          - Fail-fast iterator
          - Thread-safe
          - Capacity doubled on demand. Not 50% like arraylist
          - null ok
        - HashSet:
          - Backed by HashMap with default capacity 16 and loadFactor 0.75
          - It has loadFactor
          - Each add() comes as key to hashmap and value will be constant `new Object()`.
          - Not order preserved
          - No duplicates
          - Not synchronized
          - Fail-fast
          - null ok
        - LinkedHashSet:
          - Order of insertion preserved
          - Backed by LinkedHashMap
          - Fail-fast
          - null ok
        - TreeSet:
          - Sorted by default
          - Backed by TreeMap.
          - Derived from SortedSet
          - Not preserving insertion order
          - Elements should be Comparable or pass Comparator on create.
          - Red-Black tree. So, O(logN) for all operations
          - Fail-fast
          - Use Collections.SynchronizedSortedSet to make it thread-safe.
          - null not supported. (help sorting)
        - HashTable:
          - Synchronized map. Thread-safe
          - Each operation is synchronized
          - So, slow
          - Fail-safe
          - Null key or value not supported.
        - HashMap:
          - Key value pair
          - Relies on equals() and hashCode() contract
          - Fail-fast
          - Not synchronized
          - Order not preserved
          - Fast O(1) insert/ retrieve
          - Duplicate values ok. No duplicate keys.
          - Has capacity 16 and load factor 0.75 by default.
          - Capacity increase by 2^n and hence doubles everytime than previous value on reaching threshold
          - No shrink on remove() as most of the time, when removes, there will be something to add.
          - Better idea is to reinitialize the map when size() become 0 manually to make large space garbage collected.
          - null ok as key and value
      ```
      HashMap Internals:
          1. Based on hashing
          2. If two objects are equal, it should have same hashcode.
          3. public int hashCode()
          4. public boolean equals(object) 
          5. HashMap has Node[] array with size n =16(capacity) initially.
          5. PUT(key, value)
              * key.hashCode%(n-1) or some logic so that we can get index between 0 to capacity where n is the no: of buckets
              * resize() happens in beginning of put call if required. (THRESHOLD met). This will make sure tree convered to List if no: nodes in a bucket is < UNTREEIFY_THRESHOD(6)
              * If bucket has no nodes, add this new node. (Node(key, value, hashcode, pointer to next node))
              * If bucket has an entry, check if hashcode is same, if so, check if key is equal. If so, replace value in that node
              * If bucket has more entries than the TREEIFY_THRESHOD(8 by default), convert list of nodes in bucket to red-black tree and add new entry also into it.
              * Hash collision: Found a bucket with already Node
              * Rehashing: On meeting threshold capacity, no: of buckets changed and rearrages nodes by hashing to align each key-value to suitable buckets. 
          6. GET(key):
              * Find hash of key
              * Indentify the bucket
              * Always check first node if has same hashcode and same key
              * If fist node does not match and node is TreeNode, find node from Red-Black tree (logN)
              * If normal linked list of nodes im bucket, travese and get value by comparing hashcode, key with equals.
          7. REMOVE(key):
              * This will not shrik the map bucket size. But can rearrange the node tree to linked list if untreeify threshold is met.
              * No resize/ re-hashing on remove.
              

      ```
        - LinkedHashMap:
          - Same as hashmap. But an additional feature to keep the insertion order is there.
          - Not synchronized
          - Fail-fast
          - Wrap with Collection.synchronizedMap() to make it thread safe for methods. But not iterator.
          - We can decide with order parameter to constructor with load factor and capacity if insertion order(order=false. Default) or access order(order=true) is to be preserved.
          - For access order, if we updated a value to key, then order will be changed to give that as last one.
          - Underlying is HashMap. But has head and tail to create dequeue of  LinkedHashMap.Entry which has key, value, hash, after and before. 
          - LinkedHashMap.Entry is subclass of Map.Node + after and before pointers.
          - This controls the order.
          - After every update or access or remove, this dequeue will get updated to maintain the order.
          - But there is an additional method afterNodeUpdate(), afterNodeAccess(), afterNodeRemove() which will be 
          - Null key and values ok
        - TreeMap:
          - Ordered map
          - Key should be comparable or treemap is created with comparator for key.
          - Not synchronized. Use Collections.synchronizedSortedMap().
          - Fail-fast
          - Internally Red-Black tree
          - O(logN)
          - No null key. But null values ok
          - Performance is better. But insertion may be costly(logN) compared to HashMap due to the internal sorting.
        -ConcurrentHashMap:
          - java.util.concurrent
          - From 1.5 onwards
          - Thread-safe with locking. Not synchronized. But with segment locking or bucket locking.
          - Fail-safe as iteration works on snapshots
          - Best for concurrent thread access
          - Initial capacity 16, load factor 0.75f
          - `No lock for reads. But applies lock/ synchronization on each bucket on write.
          - 16 updates possible at a time with basic bucket count. Any number of reads are also fine.
          - Reads will return the value only if write happened before for that key.
          - Best if lot of writes and reads are there from a map
          - If we use Atomic values, adds up to thread safety.
          - We can add to constructor, concurrency level. If that is less that initial capacity, initial capacity is reset to concurrency level so that only that much buckets can be created and used.
          - **Major change in maps happened with TREEIFY etc in Java8. ConcurrentHashMap got rewritten to avoid lock variables and use intrinsic lock (synchronize over bucket) instead. **
          - No null key. No null value.
      ```
          Why we need Collection.synchronizedCollection() and Collection.synchronizedSet()?
          Collection.synchronizedCollection() will have common methods of collection only.
          If any set specific methods we need to synchronize, better to go with Collection.synchronizedSet()

      ```
        - Collections class has many utility methods for collections like sort().
        - We can sort collections with type `java.lang.Comparable`. or explicitly we have to specify a comparator(`java.util.Comparator`).
        - Else error comes as `ClassCastException`.
        - Comparable has `comparaTo(obj2)`. Comparator has `compare(obj1, obj2)`;
        - sort is using `mergeSort` internally with `O(NlogN)`.
39. Fall-fast vs Fail-safe Iterators:
    - Fail-fast:
    - Throws ConcurrentModificationException if we change underlying collection while iterating with iterator.hasNext().
    - Eg: ArrayList and HashMap iterators are fail-fast.
    - This ensures that if some change happens while iterator is in progress, throw Exception as underlying collection is changed.
    - This helps to fail if multiple threads tries to change same collection.
    - Even if single thread, it throws error if change happens to collection.
    - Works based on modeCount set at the time of `iterator()` call and then checking each time on `itr.next()`.
    - add() or remove() if iterator can be used to alter and no error comes then.
    - Fail-safe:
    - No exception on change to underlying collection.
    - It takes a snapshot at time of iterator() call and works on that.
    - Changes to original may or may not be visible to iterator.
    - This compromises consistency but ensures that even if multiple threads alters collection, iterator is not impacted.
    - Why these fail-safe and fail-fast are required?
      - To handle situations when multiple threads alter the collection.
      - Fail-fast fails
      - Fail-safe ignores changes
      - Fail-fast throws lot of exceptions on chnage
      - Fail-safe takes snapshot and hence lot of memory and cleanup. Also compromises data consistency.
      - Use fail-fast if exception are ok
      - Use fail-safe if not so much worried about consistency of data.
      - Iterator has only 'remove()'. That removed the values form previous next() call. So, it will not miss any data.
      - Error comes for upcoming next() call after change.
      - Note that `wrapping with Collections.synchronizedCollection() will not protect iterator methods. It will remain still fail-fast. So, we have to add synchronized for the iteration block explicitly.`
40. How is this `new ArrayList<>(another collection) or new LinkedList<>(any collection)` works?
    `Every collection has .toArray() method. So, whichever be the collection type, we get an array and then iterate and create/ add to the relevent collection.`
41. Stream API:
    - Apply manipulation on top of a flow of data
    - Reduces code.
    - Uses functional interfaces
    - Steam.forEach and List.forEach are different. List.forEach depends on iterator. Stream is not.
    - Stream activates only when a terminal operation is met. till then, there is no need to create stream from data itself.
    - `groupBy` helps to construct map with key and list of values from stream
    - groupBy(key mapper = what is key, value mapper = datatype to which values are to be added (TreeSet), downstream = collectors to apply logics to build value collection. Eg: another groupBy or Collectors.maxBy);
    - groupByConcurrent returns ConcurrentHashMap or its subtypes
    - `toMap(keyMapper, valueMapper)` helps to build a map with single values. It fails if duplicates in list and no merge function given. 
    - `map vs flatMap()`:
    - Both returns stream
    - But flatMap accepts a mapper that must return a `Stream`
    - flatMap applies transformation on each element of incoming stream and generates a stream from each of them.
    - Then combines these all streams to produce one single stream one level down.
    - Eg: Use map to get stream of List of orders from product.
    - Eg: Use flatMap to get stream of orders from product each has list of orders.
    - `Parallel Streams:`
      - Underlying is `fork-join thread pool (Java 7)`
      - fork-join is very light weight as it does not have own stacks like thread.
      - Fork-Join-Pool is on top of ExecutorService and each thread will have dedicated circular dequeue of tasks.
      - Once the dequeue is empty, it consumes from dequeues of other threads. (`work stealing`)
      - On stealing work, it is from queue tail. Regular work pull is done from head.
      - The worker threads uses queue head and those allocating tasks uses tail. So, no wait complexity for threads.
      - With this concept, fight for resource between thread is reduced and hence is faster.
      - Fork - splits the task. Join combines the result.
      - `Use parallel stream when our data source can be evenly splitted.`
      - Using spliterators
      - Keeps the order as same as that of source. If source is order respected (linkedlist), result from parallel stream also will be.
      - If source is unordered, result will also be unordered.
      - But we can control final result order by sort, etc.
      - In such cases, parallel stream will be operation heavy.
      - Parallel stream with sort() or forEachOrdered() will be slower than without sort() or forEach().
      - https://www.youtube.com/watch?v=0906Imq_z8g
      - https://www.baeldung.com/java-fork-join
      - `Locks`:
      - Why cannot we use just synchronized blocks and no locks?
      - `synchronized is on top of an object. But we cannot provide a fairness condition on which threads can give priority. Reentrant locks are same as intrinsic locks with synchronized() but can provide fairness, tryLock etc.`
      - `This adds up to more flexibility.`
      - `Synchronized blocks are intrinsic locks.`
42. Threads:
    - Split tasks into small chunks and run in parallel
    - Threads are light weight processes
    - It helps to utilize the cpu cores effectively and finish task faster
    - OS has thread schedulers which takes each job as cpu cores are available.
    - We just have to submit our tasks to scheduler.
    - Thread vs process: Threads are within a process and they share memory, code etc. So, light weight and easy to switch between as compared to processes
    - 2 ways to create thread
      - extend Thread class. This internally extends Runnable.
      - implement Runnable(`@FunctionalInterface`) interface. Use it if our Thread class need to extend other classes and interfaces. If used Thread, no more extension of class.
    - `thread.start()` calls run() method. It adds job to scheduler. Not immediately starting run method.
    - There are thread priorities we can set. But it just suggests thread scheduler to take it first. But its discretion of scheduler to select it.
    - Default priority = `normal(5)`. max=10. min=1
    - We can make a thread daemon by `setDaemon(true)`. So, JVM will not wait until this thread finishes to terminate application.
    - To make a sleep wait holding lock, `Thread.sleep(ms)`. This throws `InterruptedExcepton`.
    - States:
      ```
      new (Thread t1 = new Thread())
            |
            |
      -------- Runnable (t1.start(). Assigned to scheduler)---<------^-----------------<-^BLOCKING STATE(waiting for lock. In sleep, lock is already there.)
      |        |                                                     |if sleep           | thread.notify()
      |        |                                     sleep/wait      |                   |if was waiting
      |     Running (scheduler took it from queue)---------------->-Waiting---------------
      |        |
      |        |
      |        |thread.stop(). Never use it.
      |--------|-----------------Dead
      Can be due once execution finished
        
      ```
    - `t1.join()`: If we call this in main thread, main has to wait at this line until t1 finishes it's job.
    - `Thread.yield()`: static method. Informs the scheduler that current thread is fine to wait and if you want, please take the processor to allocate to other threads.
    - But decision is fully with discretion of scheduler.
    - Wait vs Notify:
      - wait() is part of `Object`
      - notify()
    - Synchronization:
      - Manage multiple threads when they share resources/ critical region so that at a time one thread alter it.
      ```
        Assume common shynchornized resource as a locked room shared by multiple threads(persons).
        The room is guarded by a security called JVM. This security holds the lock key to the room.
        When a person want to enter the room, security gives the lock to that person if no body else taken the key.
        The person with key can enter the room and use it.
        When he comes back, security graps the lock back.
        When a person comes at the time another person using the room, security asks him to wait until the current user gives him back the lock.
        So, security asks the other person to wait. 
        When multiple people are coming for the same room, security JVM checks their priority. (Eg: booking time based priority)
        When current user in room comes back and handover lock, security gives it to most eligible next person.
      
        Any object can be used as a lock.
        We can use `this` as lock to avoid creating one more object as lock.
        Then, all synchronized methods locked with `this` will be blocked.
      
        When we add synchronized keyword on a non-static method, the lock is `this`.
        If two non-static methods are synchronized in the same class, 
        thread t1 enters method1. Then lock is taken. So, if t1 need to access method2, still it has to wait.
        This is like `we gave lock of entire hotel to every thread. Here, unsynchornized methods will be still accessible.`
      
        If the lock is this keyword in synchronized block in one methods and 2 other methods are synchronized non-static,
        then, if one thread enters any of them/ block, others have to wait.
      
        When we have synchronized static methods, lock is on `class object`.
        Threads should wait in all other synchronized static methods of same class till one thread releases it.
        Non-synchronized methods are always open.
      
        Same thread having lock can use any synchronized block locked with same lock object.
      
        Wait and Nofify:
        When someone enters the room, due to manay reasons, they have to wait inside the room.
        Eg: The main intesion of the person entered the room was to start a tv inside it. 
        But if power supply is not there, he has to wait till someone fixes and notifies him about if electrition completed his duty.
        In real case, the person informs security to call electrition and he will wait outside. Security hands over lock to electrition and fixes the problem.
        Once the issue is fixed, electrition gives the key back to security.
        When there is no electricity, the persons waits and security asks the electrition to enter the room and fix the issue.
        Once electrition gives key back to security, he can ask previous person or anyone waiting to use the room by handing over lock.
      
        This is same as thread t1 entered the synchronized(lock) block and has to wait . 
        This wait() call on lock object, makes t1 wait and release the lock. Then , t1 can resume its task only after wait time is over or any other thread aquired lock (here, electrition) nofities him to resume.
        Usually, the other threads handover the lock to security (JVM) and if notify(), one of the persons waiting will get the lock. If the person went earlier is lucky enough, he will get it.
        Security can inform all waiting people also. This is `notifyAll()` and the crowed flights for the lock and wheoever wins gets the lock.
        We can set prorities to each thread to let security (JVM) give him precendence. But it is strictly the discretion of security on to whom to handover the lock.
        
        Summary:
        synchronized(lock) and lock can be any object. It can be this, class or any object which is not NULL.
        Inside synchronized block, if the thread need to wait, call lock.wait().(fails if caller thread not owns the lock. That is why wait() is called on lock inside synchronized(lock) so that we can be sure that the current thread owns `lock`.)
        To inform waiting threads to bid for the room, notify() or notifyAll() is used.
      
        Note: sleep(ms) or wait(ms) does not guarentee that thread restarts in given ms.
        Within this minimum time, thread wakes up and waited one goes to BLOCKING state(fight for lock) and then to runnable. Sleeped one has lock. So, it goes straight to Runnable state.
        
      ```
      - `volatile`:
      - Usually each thread running on each core will have a cache in core to keep a copy of the shared resource from RAM
      - so that it will be faster to access from cache than go to RAM everytime.
      - This cache will be frequently updated as well with respect to RAM. But consistency make get compromised.
      - To avoid that volatile helps.
      - If we mark a variable as volatile, instead of keeping a copy in cache, all threads will read and write it from RAM.
      - This makes sure that the shared resource is consistent to all threads.
      - `Deadlock`:
      - t1 got lock l1 and need lock l2. So, it is waiting for l2.
      - t2 got lock l2 and it need lock l1 as well. So, waiting for l1.
      - t1 and t2 are mutually locked and cannot proceed for infinite time.
      - Lock order l1, l2 and l2, l1 created issue here.
      - Refer `DeadLock.java`
      - `Wait` vs `Sleep`:
      - wait releases lock and thread goes to runnable state. Other threads can fight for lock by that time.
      - wait is from java.lang.Object
      - wait throws IllegalMonitorStateException if we try to call wait on an object which is not a lock used in relevant synchronized block.
      - sleep does not release lock. Once time is over, it comes back and resumes the work.
      - sleep is from java.lang.Thread
      - Makes current thread sleep for given time.
      - Can interrupt with t.interrupt() and it throws InterruptedException.
      - `Notify` vs `NofifyAll`:
      - notify() informs any one of the waiting threads for lock. That thread goes to blocking state.
      - notifyAll() informs all threads and they all fight and one succeeds. All threads comes to blocking state and once succeeds.
      - BLOCKING STATE: a thread is waiting to get lock.
      - https://www.youtube.com/watch?v=WldMTtUWqTg
43. var:
    - From `Java 10`
    - Local variable with type inference.
    - We can declare variables `inside method` with var. Not in class etc. 
    - Reason is, local varaibles has life inside a method and type will b eknown anyways. So, we can infer there.
    - So, var is also ok.
    - Once inferred, we cannot assign `other types`. Same type ok.
    - We cannot declare. But should be initialized. `var a; not ok`
    - `var a =10; is ok`
    - `var a = 10; a = "hai"; not ok` 
44. Java 17:
    - Sealed classes:
        - If a class is sealed, we have to specify permits. (which all classes can inherit from it)
        - Child classes must be `sealed, non-seeled or final`
        - non-sealed: anyone can inherit from this child
        - final: no one can inherit from this child
        - sealed: restricted inheritance 
    - Record:
      - To reduce data class(models) size. 
      - Came in `Java 17`
      - By default canonical constructor with all parameters we gave will be there.
      - If another constructor is needed, can add explicitly. But should call canonical one.
      - Refer `Record.java` in `basics`
      - equals, toString are overridden. Static variables can be explicitly added. But cannot add non-static.
      - To add no-static variables, update the canonical constructor.
      - This will be `immutable`. Not setter. Getters with same name of parameters
      - Best suitable for immutable models used to send data from REST endpoint etc.