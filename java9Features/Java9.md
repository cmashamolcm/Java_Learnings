##Java 9 Features:
****
1. Major Features:
    1. Jigsaw project & modularity with rt.jar as well as in projects.
    2. Steam and collection enhancements. .of() for immutable collection creation.
    3. JSHELL - cmd for Java
    4. Private methods in interface
    5. Http2 support
    6. Multi release jars
    7. Improvement for try-with-resource. Can hold effectively final variables defined outside.
    8. G1 GC is defaulted.
2. JSHELL:
    * Interactive Console /REPL(Read Evaluate Print Loop) for validating and testing Java code snippets.
    * Command: jshell
    * For any command, /<commad>. Eg: /help
    * ; at the end is not mandatory
    * Just type code and enter will give result
    * Variable defined can be used in later statements
    * If we are performing some operation, it's result gets assigned to $1, $2etc format variable.
    * just type $1 enter will show the value in it
    * Can create classes also. To use items of class, classInstance . attribute or method(); can be used.
    * We can define variable for a class and define the class later. This is called, <b>forward reference</b>.
    * When we use any class in any default imported packages like lang, util etc, it will auto imported.  
    * Commands:
         *  /help
            Eg: /help shortcuts
         * /history -gives everything we types and entered.   
         * /list -lists out valid codes typed so far. A number will be there attached to it. 
         * /edit <number corresponding to the command in list> will open up a screen to edit.
           Edit and accept and exit. If we add a new variable there, which is not yet present, no error comes.
           But, while trying to rerun the edited method, prompt comes to define that variable.
           Once it is defined, it will execute fine.
         * /vars lists all variables used so far
         * /methods lists methods defined so far
         * /imports shows already imported packages. Others have to be imported.
         * /types for classes and interfaces
         * /save, /open <file name> to save and open saved contents of a jshell.
         * /drop $15 will drop variable no: 15
    
3. Modules:
    * In JDK9, there is jmods folder inside Java folder along with bin and lib folders.
    * rt.jar is split into multiple modules and kept in jmods.
    * rt.jar is no more there in lib folder of jdk.
    * Why we need modularity?
         * To reduce jar size
         * To control accessibility of classes. Eg: to ensure if some internal classes are not exposed to externals to use.
    * All packages inside a module will be private to it.
    * To make it exposed to other modules, keep a module-info.java file in src package. 
      Then add exports and requires into it.
    * exports : Indicates the packages from this module exposed for public use.
    * imports : indicates the packages which are mandatory for smooth functioning of this module.
    
    * <b>JLink</b> helps to build jars by adding only required dependencies. Even from jmods.
4. Collection Factory:
    * List.of(), Set.of() etc which will return immutable collections. If tries to modify, UnsupportedOperationException comes.
5. Stream Improvements:
    * takeWhile(), dropWhile(), Optional.of(E).stream()
6. Private Methods In Interface:
    * Defaults will be public. So, private methods helps to write more clean code with internal methods.
    * It won't be accessible from other places. Static methods within interface also cannot use private methods.
    * An instance cannot be created and from static context, without instance, private non-static methods cannot be called.
7. HTTP2 and Websocket Support Improvements
8. multi-release support Jars. Maintain class files for different versions of Jar and use it only if the JVM version is matching with the version of class file.
   Else it will remain silent.
9. Try-Catch with resource is improved from Java 7 to accept effectively final resource variables in it.
   Previously,only;
   try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
   }catch (Exception e){}
   was supported.
   But now;
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   try(br){
        }catch (Exception e){}
   is also possible.
   But;
   If;
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   br = new BufferedReader(new InputStreamReader(System.in));
   try(br){
   }catch (Exception e){}
   is not allowed.
   br got modified. So, not effectively final.
   
    