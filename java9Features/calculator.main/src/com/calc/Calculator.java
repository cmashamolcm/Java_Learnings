package com.calc;

import com.java.util.AddHelper;

public class Calculator {
    public static void main(String[] args) {
        AddHelper helper = new AddHelper();
        System.out.println(helper.add(10, 20));

        //error comes as the internal package from add.helper is not exported in module-info.java
        //Adder adderToBeNotUsed = new Adder();
    }
}
