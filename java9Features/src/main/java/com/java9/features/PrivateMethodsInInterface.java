package com.java9.features;

public class PrivateMethodsInInterface {

}

interface PrivateMethodImpl{

    default void defaultMethod(){
        System.out.println("Calling myPrivateMethod");
        myPrivateMethod();
    }

    static void staticMethod(){
        System.out.println("Calling myPrivateMethod");
        //Not possible to use private interface method here as private method is not available in static context.
        //Instance of this interface cannot be created here also. Error comes.
        //new PrivateMethodImpl(){}.myPrivateMethod();
    }

    private void myPrivateMethod(){
        System.out.println("Inside private interfacemethod...");
    }
}