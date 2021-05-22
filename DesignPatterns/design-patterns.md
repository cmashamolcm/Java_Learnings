##Design Patterns:
****

1. GoF:
    * Gangs of Four
    * 23 design patterns written by <b>4</b> authors.
    
2. Types
                        
                        Design Patterns
                              |
                              |
                -------------------------------------
                |             |                     |
        Creational          Behavioural         Structural
            |                    |                  |
        |-Singleton         |-Observer          |-Adaptor
        |-Factory           |-Template          |-Composite
        |-Abstract Factory  |-Chain of          |-Proxy
        |                   |Responsibility     |-Flyweight
        |-Builder           |-Strategy          |-Facade
        |-Prototype         |-Command           |-Bridge
        |                   |-Visitor           |-Decorator
        |                   |-Interpreter       |
        |
        
        Factory in Behavior = Strategic(property based)
        AbstractFactory In Behaviour = Command (We give factory of actual thing to execute)
        Prototype in Behaviour = template
        Prototype in Structural = Flyweight
        Observer = Adaptor. Both wraps.
        Composite = Chain of Responsibility
        Builder almost matches Chain of Responsibility
        

3. Creational Design Pattern:
    1. Singleton:
        * Only one instance per JVM
        * Will have a global method to get the instance.
        * Eg: java.lang.RunTime
    2. Factory:
        * One super class and it's multiple implementations are there.
        * Have to select class based on condition
        * Drawback is complex conditions  
        * Eg: Calender.getInstance();
   3. AbstractFactory:
        * Factory of factory will be there. We will give factory instance into factory of factory to get the object.
        * Eg: xml.TransformerFactory.newInstance();
   4. Builder:
        * When more attributes are there to initialize
        * There are optional attributes  
        * Eg: StringBuilder
    5. Prototype:
        * When a big object is there and we want to create another similar object with very few changes.
        * It will be helpful if we can copy existing object and change the new copy with updates.
        * Eg: When we want to create an object based on database table, it't not good to hit tables again and construct object.
        * That time, prototype can help.
        * We have a model/ prototype. Use that to create new objects.
4. StructuralDesign Patterns:
   
   * How will be the class structure be looking like. Uses composition and inheritance.
    1. Adaptor:
        * Best suited when we have to use an unrelated class in our code which have to masked and used.
        * Eg: We have map. If we want to use it as synchronized map, we used to call
        * Collections.synchronizedMap(our map);
        * It gives us an object of SynchronizedMap which will be internally using actual map we passed, but after wrapping it.
        * It's the same.
        * Adaptor helps to use an unrelated class alongside our own class.
    2. Composite:
        * If we have a tree like structure of an operation over a set of objects, we can use composite.
        * Eg: We have an operation to be performed on a base object.
        * There are set of objects in it with same operations to be performed; ie; children of sample interface.
        * Eg: There is a collection of cartons in which there can be another cartons and actual items.
        * Carton is base type, items are leaves.
        * To calculate the price of entire cart, have to iterate over one by one and add up.
    3. Proxy:
        * Hide one class from client to use it extensively, mostly due to security concerns.
        * Eg: There is actual ATM object. When a client tries to use the features, it needs to be exposed within another proxy and mask it.
        * If we have AtmData, when the client asks, it should give ProxyAtmData.
        * There can be situations, when we want to share some data if the client have privilege. In such cases, in proxy methods, authentication also can be done.
    4. Flyweight:
        * Design pattern used when number of objects to be created is huge.
        * Eg: pools of String, Thread pool etc.
        * We will create and keep n objects with it's intrinsic properties first. (name, size etc.)
        * We will refer and get available objects from this pool and add extrinsic properties like color, id etc and use it.
        * If there is no object of our demand, we will create one will intrinsic, put that in cache and use it.
        * Once done usage, return to pool.
    5. Facade:
        * This what an orchestrator type of system is doing.
        * Eg: We will have a single gateway to a set of complex subsystem.
        * API Gateway acts like a Facade in front of the micro-services to hide the complexity from client.
    6. Decorator/Wrapper:
        * Uses when inheritance/ extension is not a solution as there are already many and if we change interface, it is going to affect many implementations.
        * Eg: We can have Pizza. It can be of ThinCrust, DoubleCheese etc.
        * It can have different toppings as well.
        * Consider like Pizza library was already there and ThinCrust, DoubleCheese are extending it.
        * Since we cannot modify the lib, what we can do it to extend anew interface and add the new methods and use it as a wrapper which holds originals and some extra.
5. Behavioural Design Pattern:
    
    How the interactions between objects are happening in loosely coupled, flexible, extensible way.
   1. Template:
        * One abstract implementation will be there. 
        * If any change is there for any of it's implementations, it will be overridden and used.
   2. Chain Of Responsibility:
        * Each method pass control to another, then to another and so on to get result.
   3. Observer:
        * Producer-Consumer
        * Producer informs consumer of anything happens.
        * When state change of objects are relevant to be notified, this can be used.
   4. Strategic:
        * There will be multiple implementations.
        * Based on attributes/ params we pass, either of it is selected and used.
        * Eg: GC.
        * Condition based behavior. Factory did condition based creation whereas Strategic did condition based behaviour.
    5. Command:
        * Loose coupling with request-response objects.
        * There are same operation tobe performed on different systems.
        * Eg: fileRead() can be done in Linux or Windows.
        * We will trigger a command to execute and where to execute.
        * Kind of an extended version of abstract factory.
    6. Visitor:
        * To put some logic outside of class to get flexibility of changing it.
        * Eg: When we get price of items in special occasions, it have to respond different.
        * So, we have have getprice() in a separate class and use it. 