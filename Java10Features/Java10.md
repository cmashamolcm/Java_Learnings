Java 10 Features:
****
1. LocalVariable Type Inference:
    * var
    * Only for local variables.
    * Mandatory to initialize.
    * Exact type only will get assigned.
    * Reassign with another type is not allowed.
    * Class variables or method parameters cannot be var as initialization is not mandatory for it and hence difficult to find type.
    * Cannot assign lambda expression into var.
      var a= (String str)->s.length(); will give error.
    * Not so good in terms of readability of code.
2. Collection: copyOf()
    * .of() of Java 9 as well as copyOf() returns immutable Collection.
    * Map also has copyOf() method. But Map is not inherited from Collection.
    * Name of immutable collection used: List12, ListN etc.
    * Map don't have .stream() method.
    * EntrySet, KeySet etc has .stream()
3. Collectors.unmodifiableMap():
    * To generate immutable collection from stream.
    * Eg: list.stream().collect(Collectors.toUnmodifiableMap(p->p, p->p*3, (oldKey, duplicateKey)->duplicateKey);
    * Here, last one is merger. Error comes if we are not specifying the merger and when duplcaute comes in stream.
    * Here in above case, duplicateKey will be replacing an existing key in map.
4. Optional.orElseThrow():
    * From 1.8, Optional.empty().orElseThrow(()->new NullPointerException();
    * Now, Optional.empty().orElseThrow(); is also possible.
    * It will throw NoSuchElementException.
5. JVM improvements.
6. ContainerAwareness -XX:-UseContainerSupport. Helps in docker etc.