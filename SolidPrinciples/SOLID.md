##SOLID Principle:
****
1. S - Single Responsibility
   O - Open For Extension Closed For Modification
   L - Liskov Substitution
   I - Interface Segregation
   D - Dependency Inversion
2. Single Responsibility:
    * Only one responsibility in terms of functionality.
3. Open For Extension Closed For Modification:
    * A class should not be modified for adding more functionalities, but can  be extended for that.
3. Liskov Substitution:
    * If we use a super type, it should be replaceable with it's subtypes without behaviour change.
4. Interface Segregation:
    * Break down the interfaces into it's basic atomic level.
    * There should be methods in interface which is doing nothing in a few of it's implementations.
5. Dependency Inversion:
    * Depend on abstraction, not on concrete.
    * High level modules should not depend on low level modules very tightly.
    * Eg: If a module directly depends on a class in a sub-module, changing of that module will be difficult.
6. Why should we follow these principles?
    * Code maintainability
    * Readability
    * Loose coupling of modules
    * Easy bug fixing and easy to replacement of sub-modules.
    * Architecture, Design Patterns and Design Principles makes an application succeed.
    