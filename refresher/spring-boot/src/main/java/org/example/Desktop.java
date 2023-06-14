package org.example;

import org.springframework.stereotype.Component;

@Component("desk")
public class Desktop implements Computer{

    @Override
    public void printType() {
        System.out.println("I am a desktop");
    }
}
