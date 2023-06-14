package org.example;

import javax.persistence.*;

@Entity
@Table(name = "devops")
public class DevOpsModel {

    String devName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + ":::::" +devName;
    }
}
