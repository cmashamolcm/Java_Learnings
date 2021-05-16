package com.features.java13;

public class SwitchWithYield {
    public static void main(String[] args) {
        int a = 10;
        var b = switch (a){
            case 1-> {yield 10;}
            case 10-> {yield 100;}
            default -> a;
        };
        System.out.println(b);
    }
}
