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
                                            
 8. <b>Garbage Collection:</b>   
 
         * GC is performed by Garbage Collector Threads.
         * outOfMemoryError comes if heap gets full.
         * System.gc() cannot force garbage collection. But it can just request for it.
         * Static members, methods, constants are part of metaspace. Not heap
      1. Stages of GC:
      
         1. Mark - traverse object tree and mark allreachable objects as 'live'
         2. Sweep/ Delete - Remove objects not marked as live
         3. Compact - Rearrage objects to make continueous free space
         
      2. GC is called Generational GC since heap is devided as young and old gen space.
      
                                    heap
             ----------------------------------------------------------------------
                     Young Generation      |       old/ Tenured Generation
                                           |
                        Eden Space         |
              -----------------------------|
              Survivor 1|  Survivor 2      |
             ----------------------------------------------------------------------    
             
             Objects get created in Eden.
             When eden is full, its moved to surviors with young/minor GC and cleans unreachables.
             Survior1 swaps with survior2 and reverse. 2 surviors are there to save extra compaction overhead.
             After a certain number of swaps between survivors also, if the object is live, move it to old gen space.
             -XX:MaxTenuringThreshold decided how many swaps allowed between survivors.
             
      3. Selection of GC algorithms are based on responsiveness(less pause time preferred) or throughput requirements.
      4. GC Collectors:
            1. Serial GC:
                  In single thread. Stop the world and do GC.
            2. Concurrent Collector:
                  Runs in parallel with app threads.
                  It will not pause the world. It stops only in mark/ remark
                  Use when very short pause allowed(responsive).
                  Use if more memory to clean
                  More cores to work
                  Removed from JDK 14
            3. Parallel GC:
                  GC uses multiple threads and cores to do mark and sweep.
                  But it is stop the world. But quickly does it's job.
                  Use when :Less memory
                  Less cpu
                  Throughput is important
            4. G1 GC:
                  Heap is considered as fragmented regions
                  Parallel + Concurrency
                  Clean regions with more unreachable objects.
                  Select regions as old and young dynamically.
                  Default by Java 9
                  From 1.7 ownwards
            5. Z GC - Java 14
            6. Epsilon GC - Java 11
            7. -XX:useSerialGC - old and young area clean by 1T
            8. -XX:useParallelGC - young by nT, old by 1T
            9. -XX:useParNewGC - Young by nT
            10. -XX:useParallelOldGC - Young nT, old -nT
            11. -XX:useConcMarkSweepGC - new - parNewGC, old nT
            12. -XX:useG1GC - nT
      5. Object - protected void finalize() throws Throwable{} - 
         not recommended to use to free up space. Error chances are high if not used properly.
         This method gets called only once. Just before GC happens for an object.
      6. -XX:newRatio = 2 means 2/3 for old, 1 for young
      7. -XX:maxNewSize
      8. -Xmx: 256m
      9. -verbose:gc etc can capture gc details.
      10. JHAT - Java heap analizer tool
      11. 
