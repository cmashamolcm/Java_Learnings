package org.example;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "my-bean-1")
@Scope(value = "prototype")
public class MyBean {
    int id;
    String name;

    public MyBean() {
        System.out.println("Created my bean");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    void show(){
        System.out.println("Hello from MyBean");
    }
}
