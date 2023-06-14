package org.example;

import org.springframework.stereotype.Component;

@Component("lap")
public class Laptop implements Computer{

    @Override
    public void printType() {
        System.out.println("I am a laptop");
    }
}
