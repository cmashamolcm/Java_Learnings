package com.features.java14;

public class NullPointer {
    public static void main(String[] args) {
        String str = null;
        str.isBlank();//Cannot invoke "String.isBlank()" because "str" is null
    }
}
