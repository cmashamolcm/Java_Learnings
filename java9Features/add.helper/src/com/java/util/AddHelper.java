package com.java.util;

import com.java.internal.Adder;

public class AddHelper {
    public int add(int a, int b){
        Adder adder = new Adder();
        return adder.add(a, b);
    }
}
