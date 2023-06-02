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
 