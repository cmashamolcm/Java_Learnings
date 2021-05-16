package com.features.java15;

public class Records {
    public static void main(String[] args) {
        Person person = new Person(1, "P1");
        System.out.println(person.id());

    }
}

record Person(int id, String name){

}