package com.java9.calc.util;

import com.java9.calc.internal.Adder;

public class AddHelper {
    public int add(int a, int b){
        Adder adder = new Adder();
        return adder.add(a, b);
    }
}
