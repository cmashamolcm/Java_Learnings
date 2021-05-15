package com.java9.calc;

import com.java.internal.Adder;
import com.java9.calc.util.AddHelper;

public class Calculator {
    public static void main(String[] args) {
        AddHelper helper = new AddHelper();
        System.out.println(helper.add(10, 20));

        //can use here.
        Adder adderToBeNotUsed = new Adder();
    }
}
