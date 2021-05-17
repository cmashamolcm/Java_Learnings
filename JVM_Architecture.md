1. From Java 11, 
   simply with java <class name.java>, we can start JVM.

3. When we do
 java classname, below steps takes place
 File
      |
      Javac
      |
    byte code .class files                          byte code .class files from Java library
      |                                                       |
      |                                                       |
      v                                                       |
    class loader----------------------------<-----------------|
      |
    execution engine
      |
      |(JVM interact with OS via native method calls)
  Operating system
4. ### Parts of JVM:
                * Class Loader SubSystem
                * Runtime data area (Java stack, heap, PC register, method area, native method stack)
                * Execution Engine
                * Native Method Interface (JNI)
                * Native library to interact with OS
            
5. <b>Class Loading Subsystem:</b> 


         Load----------------------------------->--------------------------Link----------------------------------------------------->----------------Initialize-----
           *Bootstrap class loader(rt.jar)         |    Verify - validate byte code if it's as per Java Class Spec                   |       Static block execution
           *Extention class loader (jre/lib/ext)   |    Prepare - Allocate default values for static/ class variables                |       Assign intialized     
                                                   |                                                                                 |       values to static                                                          |                                                                                 |       variables
            *class path, -cp .class files          |    Resolve - Resolving all references                                           |       
                                                   |            - Chance for class def not found exception is there.                 |
                                                   |            - If a class was there at compile time and got deleted at runtime    |
                                                   |            - ClassNotFound comes when .forName() or                             |
                                                   |              loadClass() tries to find class                                    |
                                                   |       All the 3 linking stages can be parallel                                  |
                                            

6. <b>Runtime Data Area</b>   
         1. Meta Space:
            Part of native memory now.
            Earlier it was called method area or permgen space before Java 8.
            Stores Class class objects, ie; class information
            Static variables, constants etc.
         2. Heap:
            Objects/ instances reside here.   -Xms, -Xmx min and max limits to set.
         3. PC Register:
            Program Counter - points to next instruction to be executed.
         4. Java Stacks:
            Stack frames for method execution per thread. T1 will have one set of stack frames, T2 will have another set of stack frames etc.   
            -Xss
            StackOverFlowError
         5. Native Method Stack    
 7. <b>Execution Engine</b>  
         1. JIT Compiler - optimize machine code
         2. HotSpot Profiler - helps to get staticstics to optimize machine code by JIT Compiler
         3. GC - Clean up heap
         4. Interpreter - converts line by line byte code to machine code
         Execution engine interacts with native .dll, .so files etc with the help of JNI(Java Native Interfaces)                                          
                                            
                                            
                                            
                                            
