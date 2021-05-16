package com.java11.features;

import java.util.function.IntConsumer;

public class VarInsideLambda {
    public static void main(String[] args) {
        IntConsumer lambda = (int a) -> {
            var b = a;
            System.out.println(b);
        };
    }
}
