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
                                            
                                            
                                            
                                            
                                            
                                            
                                            
