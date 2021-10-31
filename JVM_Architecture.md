1. From Java 11, 
   simply with java <class name.java>, we can start JVM.
   
   java MainClass.java
   will work.

3. When we do
 java classname, below steps takes place
   
    
       File
         |
         Javac
         |
       byte code .class files                          byte code .class files from Java library
         |                                                        |
         |                                                        |
         v                                                        |
         class loader----------------------------<----------------|
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
      
9. String:

         1. Immutable
         2. java.lang.String
         3. String literals are stored in string pool. Previously it was part of method area. Now string constant pool is part of heap itself from 1.7 ownwards.
         4. String created with new arepart of heap itsef.   
         5. new String("Hello").intern(); will give String from pool.
         6. String is used extensively, so it make sense to cache it.
         7. Why is string immutable is 
             * to make string pool consistant
             * thread safe
             * cache the hashcodes.
         9. Can use Pattern.compile("abc") and Pattern.matcher(str) to find repeatitions of abc in str.
         10. .substring() has memeory leackage issue till 1.7 as it was keeping str within substring. 
         11. str was kept in value array and only offsets were changing. Now its solved.  
         12. StringBuilder is not thread safe wheras StringBuffer is thread safe.
         13. Mostly we use builder.
         14. Nowadays concatination with + internally uses builder itself. So, no overhead there.
         15. Never store passwords in string. Can use char array instead.
 10. Maps:
      
      1. HashTable:
         now extends Map.
         Thread safe with methods synchronized
         Its overhead can be good for safe writes but slow if multiple reads happens.
         No null keys or null values allowed
         Intial size = 11.
         
      2. HashMap:
         Not thread safe
         Orderof key is not guaranteed
         Good to use of reads are more
         Single null key allowed
         Need to synchronize methods if using map in concurrent environment
         Initial capacity = 16, LF = 0.75. 5 of buckets to be filled to resize and rehash
         
      3. LinkedHashMap:
         Iteration order equals insertion order
         It maintains a separate doubly linked list to connect entries in insertion order.
         Can use if order of insertion is to be preserved.
         Initial capacity = 16
         
      4. TreeMap:
         From SortedMap and NavigableMap.
         Either key have to be Comparable or pass explicit comparator to TreeMap else ClassCastException comes.
         Red-black tree implementation
         Null keys not allowed, but null values ok.
         
      5. IdentityHashMap:
         Uses == comparison.
         Best to use when key is Class class object or Strings
         Memory footprint is less as there is not Node or Entry created.
         Initial capacity = 21
      
      6. EnumMap:
         Key will be enum.
         Nullkeys not allowed.
      7. WeakHashMap:
         GC cleans up the keys unreachable from any other external strong references.
         Ie; if a key is only present in map and not used outside, it can get cleaned up.
         Best suited for caches and lookups.
         Wraps keys in java.lang.ref.WeakReference class.
         GC sweeps it any time.
         There is sfot reference which will be GC ed only if high demand for memory comes.
      8. Collections.synchronizedMap(map):
         Will give SynchronizedMap object which wraps actual map.
         Similar to HashTable, all methods will be synchronized
      9. java.util.cuncurrent.CurrentHashmap:
            * <b>Concurrency during retrieval.</b>
            * Extends ConcurrentMap, AbstractMap.
            * Reads will be concurrent. Not blocked even if writes are going on.
            * No sync block in get(), but has in put() after getting segment. 
            * Write needs lock, read will not require a lock. Write locks only segments, not the entire map.
            * Fail-safe -no ConcurrentModificationException
            * No null keys or nullvalues supported as .contains() is not effective as in case of other maps here as the values can change in between.
            * Best use when concurrent writes and there after later, concurrent reads are high.
            * Operations are not atomic or synchronized.
            * Even if we use concurrent hash map, there is inconsistencies in result if not carefully synchronized.
            * Concurrency Level = 16, Default Capacity = 16
            * More resenbles with HashTable. Even has contains() method equivalent to containsValue(). 
            * Other maps don't have that. Others has containsKey(), containsValue();
     10. ConcurrentSkipListMap:
            * Equivalent of TreeMap with concurrency.
            * O(logN) guranteed complexity, whereas ConcurrentHashMap not guaranteed that.
            * From ConcurrentNavigableMap and ConcurrentSortedMap.
     CopyOnWriteArrayList:
            * Iteration takes a snapshot and perform it.
            * Changes to actual list can be done in between the iteration.
            * It won't give error or it won't add that element to the current iteration snapshot.
            * Takes and stores a copy of array into snapshot array in Iterator and does reads.
            * Data inconcistency can be there as the actual inserts takes place to original array.
            * Atual add() happens by reentrant locking.
         
    Note: Concurrent modificationException comes if same or other threads tries to update the map while using it especially in iterator.
          ArrayList has default capacity of 10.
          Fail Fast vs Fail Safe:
          1. Fast: Throws ConcurrentModificationException and fails immediately. Eg: HashMap
          2. Safe: Allowes modification at the time of access also. No error...but can be unpredicatble. Eg: ConcurrentHashMap
          <b>Treeify Threshold:</b>
          The number of elements in a bucket after which the linked list connected to each bucket is converted to tree.Usually 8.
          <b>Untreeify<b> threshold is the element count in a bin for which tree has to be converted to list. Usually 6.
          
          Reentrant Lock:
            Same thread who aquired locked can proceed. Others has to wait till lock is released. Same as synchronized keyword. 
