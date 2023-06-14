package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Developer {
    int id;
    String name;

    @Autowired
    @Qualifier("lap")
    Computer laptop;

    @Autowired
    @Qualifier("desk")
    Computer desktop;

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

    public Computer getLaptop() {
        return laptop;
    }

    public void setLaptop(Computer laptop) {
        this.laptop = laptop;
    }

    public Computer getDesktop() {
        return desktop;
    }

    public void setDesktop(Computer desktop) {
        this.desktop = desktop;
    }
}
